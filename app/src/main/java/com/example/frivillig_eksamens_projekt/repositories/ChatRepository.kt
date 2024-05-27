package com.example.frivillig_eksamens_projekt.repositories

import android.util.Log
import com.example.frivillig_eksamens_projekt.DTO.ChatRoom
import com.example.frivillig_eksamens_projekt.Models.Conversation
import com.example.frivillig_eksamens_projekt.Models.Message
import com.example.frivillig_eksamens_projekt.Models.Organization
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

/*
    ///--- ORGANISATIONER ---(Chat)///

    // Get a list of organisations
    suspend fun getOrganizations(): List<Organization> {
        val result =
            db.collection("Organizations").get().await().toObjects(Organization::class.java)
        println("Organizations fetched: $result")
        return result
    }

    //Search for Organisations (Chat)
    suspend fun searchOrganizations(name: String): List<Organization> =
        db.collection("Organizations")
            .whereEqualTo("name", name)
            .get()
            .await()
            .toObjects(Organization::class.java)





    //------------------------------------------------------------------------------//
    ///-----------------SEND MESSAGES---(addChatScreen)///
    // Function to send messages to database.
    fun addOrUpdateChat(
        userIds: String,
        orgId: String,
        messageText: String,
        timestamp: Long,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        val chatCollection = db.collection("Chat")
        val query = chatCollection
            .whereEqualTo("useIds", userIds)
            .whereEqualTo("orgid", orgId)
            .limit(1)

        query.get().addOnSuccessListener { documents ->
            if (documents.isEmpty) {
                val newChat = mapOf(
                    "userIds" to userIds,
                    "orgid" to orgId,
                    "messages" to listOf(mapOf("content" to messageText, "timestamp" to timestamp)),
                    "time" to timestamp  // keep a 'time' for the last message
                )
                chatCollection.add(newChat)
                    .addOnSuccessListener { onSuccess() }
                    .addOnFailureListener(onFailure)
            } else {
                val documentRef = documents.documents.first().reference
                documentRef.update(
                    "messages",
                    FieldValue.arrayUnion(
                        mapOf(
                            "content" to messageText,
                            "timestamp" to timestamp
                        )
                    ),
                    "time",
                    timestamp
                )
                    .addOnSuccessListener { onSuccess() }
                    .addOnFailureListener(onFailure)
            }
        }.addOnFailureListener(onFailure)
    }

 */


    // ------------------ GET MESSAGE ON SCREEN ---------------//
    // Function to get messages on screen to chat in realtime
    fun getMessages(userIds: String, orgId: String): Flow<List<Message>> = callbackFlow {
        val query = db.collection("Chat")
            .whereEqualTo("userIds", userIds)
            .whereEqualTo("orgid", orgId)
            .orderBy("time", Query.Direction.ASCENDING)
            .limit(1)

        val listener = query.addSnapshotListener { snapshot, exception ->
            if (exception != null) {
                close(exception)
                return@addSnapshotListener
            }
            if (!snapshot?.isEmpty!!) {
                val document = snapshot.documents.first()
                val messages = document.toObject(ChatRoom::class.java)?.messages ?: listOf()
                trySend(messages)
            } else {
                trySend(listOf())
            }
        }
        awaitClose { listener.remove() }
    }


    //------------------------------------------------------------------------------//
    //------------------ GET MESSAGES ---(ConversationList)//
    // Function to fetch chat rooms for a user
    // Fetch conversations by user ID


    // Function to fetch chat rooms for a user
    suspend fun fetchChatRooms(userIds: String): List<ChatRoom> {
        val querySnapshot = db.collection("Chat")
            .whereArrayContains("userIds", userIds)
            .orderBy("time", Query.Direction.DESCENDING)
            .get()
            .await()


        Log.d("FetchChatRooms", "Number of chat rooms fetched: ${querySnapshot.size()}")
        return querySnapshot.toObjects(ChatRoom::class.java)
    }



    // Function to fetch chat rooms for an organization
    suspend fun fetchChatRoomsByOrgId(orgId: String): List<ChatRoom> {
        val querySnapshot = db.collection("Chat")
            .whereEqualTo("orgId", orgId)
            .orderBy("time", Query.Direction.DESCENDING)
            .get()
            .await()

        // Log antallet af fundne dokumenter
        Log.d("FetchChatRooms", "Number of chat rooms fetched: ${querySnapshot.size()}")
        return querySnapshot.toObjects(ChatRoom::class.java)
    }




    suspend fun getConversationsByUserId(userId: String): List<Conversation> {
        Log.d("ChatRepository", "Fetching conversations for user ID: $userId")
        // Fetch chat rooms for the specified user
        val chatRooms = fetchChatRooms(userId)
        Log.d("FetchConversations", "Number of chat rooms for user $userId: ${chatRooms.size}")

        // Map each chat room to a Conversation object, including fetching the organization name
        return chatRooms.mapNotNull { chatRoom ->
            val lastMessage = chatRoom.messages.maxByOrNull { it.timestamp }

            chatRoom.documentId?.let { conversationId -> // Ensure the chat room ID is not null
                // Create a new Conversation object with all required fields
                Conversation(
                    organizationName = chatRoom.organizationName,
                    lastMessage = lastMessage ?: Message("","", "",0),
                    conversationId = chatRoom.documentId!!
                )
            }
        }
    }


    // Function to fetch chat room for an organisation
    suspend fun getConversationsByOrgId(orgId: String): List<Conversation> {
        Log.d("ChatRepository", "Fetching conversations for org ID: $orgId")
        // Fetch chat rooms for the specified organization
        val chatRooms = fetchChatRoomsByOrgId(orgId)
        Log.d("FetchConversations", "Number of chat rooms for org $orgId: ${chatRooms.size}")

        // Map each chat room to a Conversation object, including fetching the organization name
        return chatRooms.mapNotNull { chatRoom ->
            val lastMessage = chatRoom.messages.maxByOrNull { it.timestamp }

            chatRoom.documentId?.let { conversationId -> // Ensure the chat room ID is not null
                // Create a new Conversation object with all required fields
                Conversation(
                    organizationName = chatRoom.organizationName,
                    lastMessage = lastMessage ?: Message("","", "",0),
                    conversationId = chatRoom.documentId!!
                )
            }
        }
    }




    // ------------ RESUME CHAT --------------------//
    // Function to get messages for the chosen conversation in real-time
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





    // Get OrgId for the chosen organisation on ChatScreen
    suspend fun getOrgId(orgId: String): Organization? {
        val snapshot = db.collection("Organizations")
            .whereEqualTo("orgID", orgId)
            .get()
            .await()

        return snapshot.documents.firstOrNull()?.toObject(Organization::class.java)
    }
}








