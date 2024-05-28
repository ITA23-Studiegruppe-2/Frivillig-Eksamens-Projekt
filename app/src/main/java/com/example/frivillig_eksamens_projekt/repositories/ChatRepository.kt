package com.example.frivillig_eksamens_projekt.repositories

import android.util.Log
import com.example.frivillig_eksamens_projekt.DTO.ChatRoom
import com.example.frivillig_eksamens_projekt.Models.Conversation
import com.example.frivillig_eksamens_projekt.Models.Message
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await


class ChatRepository {
    val db = Firebase.firestore




    //------------------ GET MESSAGES ---(ConversationList)//
    // Fetches chat rooms for a specified user ID and maps each chat room to a
    // Conversation object, including fetching the sender's name
    suspend fun getConversationsByUserId(userId: String): List<Conversation> {

        Log.d("ChatRepository", "Fetching conversations for user ID: $userId")
        // Fetch chat rooms for the specified user
        val chatRooms = fetchChatRoomsByUserIdOrOrgId(userId)
        Log.d("FetchConversations", "Number of chat rooms for user $userId: ${chatRooms.size}")

        // Map each chat room to a Conversation object, including fetching the organization name
        return chatRooms.mapNotNull { chatRoom ->
            val lastMessage =
                chatRoom.messages.maxByOrNull { it.timestamp } // Find the latest message based on timestamp
            chatRoom.documentId?.let { conversationId ->  // Ensure the chat room ID is not null
                lastMessage?.let { message ->
                    val senderName = fetchNameById(message.senderId)
                    // Create a new Conversation object with all required fields
                    Conversation(
                        organizationName = chatRoom.organizationName,
                        lastMessage = message.copy(senderName = senderName),
                        conversationId = chatRoom.documentId ?: ""
                    )
                }
            }
        }
    }


    // retrieves the name of a user or an organization based on the provided ID.
    // It first tries to fetch the user from the Users collection, and if the user does not exist,
    // it then tries to fetch the organization from the Organizations collection
    suspend fun fetchNameById(userId: String): String {
        // Try to fetch the user first
        val userSnapshot = db.collection("Users")
            .document(userId)
            .get()
            .await()

        if (userSnapshot.exists()) {
            return userSnapshot.getString("name") ?: "Unknown User"
        }

        // If user does not exist, try to fetch the organization
        val orgSnapshot = db.collection("Organizations")
            .document(userId)
            .get()
            .await()

        if (orgSnapshot.exists()) {
            return orgSnapshot.getString("name") ?: "Unknown Organization"
        }

        return "Unknown User"
    }





    // Fetches chat room for a specified user ID or organization ID.
    // It first tries to fetch chat rooms where the userIds array contains the specified ID,
    // and if none are found, it then tries to fetch chat rooms where the orgId matches the specified ID
    suspend fun fetchChatRoomsByUserIdOrOrgId(userIds: String): List<ChatRoom> {
        val userQuerySnapshot = db.collection("Chat")
            .whereArrayContains("userIds", userIds)
            .orderBy("time", Query.Direction.DESCENDING)
            .get()
            .await()

        if (userQuerySnapshot.isEmpty) {
            // If no match on userId, try orgId
            val orgQuerySnapshot = db.collection("Chat")
                .whereEqualTo("orgId", userIds)
                .orderBy("time", Query.Direction.DESCENDING)
                .get()
                .await()

            return orgQuerySnapshot.toObjects(ChatRoom::class.java)
        }

        return userQuerySnapshot.toObjects(ChatRoom::class.java)
    }




    // ------------ RESUME CHAT --------------------//
    // sets up a real-time listener for messages in a specified conversation.
    // It listens for updates to the messages collection
    fun getMessagesForConversation(conversationId: String): Flow<List<Message>> = callbackFlow {
        val query = FirebaseFirestore.getInstance()
            .collection("Chat")
            .document(conversationId)
            .collection("messages")
            .orderBy("timestamp", Query.Direction.ASCENDING)

        val listener = query.addSnapshotListener { snapshot, exception ->
            if (exception != null) {
                close(exception)
                return@addSnapshotListener
            }
            if (snapshot != null) {
                val messages = snapshot.documents.mapNotNull { it.toObject(Message::class.java) }
                trySend(messages)
            }
        }
        awaitClose { listener.remove() }
    }
}













