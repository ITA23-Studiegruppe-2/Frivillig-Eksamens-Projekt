package com.example.frivillig_eksamens_projekt.repositories

import android.util.Log
import com.example.frivillig_eksamens_projekt.DTO.ChatRoom
import com.example.frivillig_eksamens_projekt.Models.Conversation
import com.example.frivillig_eksamens_projekt.Models.Message
import com.example.frivillig_eksamens_projekt.Models.Organization
import com.google.firebase.Firebase
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await


class ChatRepository {
    val db = Firebase.firestore


    ///--- ORGANISATIONER ---(Chat)///

    // Get organisationens
    // Hent liste af organisationer
    suspend fun getOrganizations(): List<Organization> {
        val result =
            db.collection("Organizations").get().await().toObjects(Organization::class.java)
        println("Organizations fetched: $result")
        return result
    }

    //Search for Organisationens (Chat)
    suspend fun searchOrganizations(name: String): List<Organization> =
        db.collection("Organizations")
            .whereEqualTo("name", name)
            .get()
            .await()
            .toObjects(Organization::class.java)





    //------------------------------------------------------------------------------//
    ///---SEND MESSAGES---(addChatScreen)///
    // Function to send messages to database.
    fun addOrUpdateChat(
        userId: String,
        orgId: String,
        messageText: String,
        timestamp: Long,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        val chatCollection = db.collection("Chat")
        val query = chatCollection
            .whereEqualTo("userid", userId)
            .whereEqualTo("orgid", orgId)
            .limit(1)

        query.get().addOnSuccessListener { documents ->
            if (documents.isEmpty) {
                val newChat = mapOf(
                    "userid" to userId,
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


    // Function to get messages on screen to chat in realtime
    fun getMessages(userId: String, orgId: String): Flow<List<Message>> = callbackFlow {
        val query = db.collection("Chat")
            .whereEqualTo("userid", userId)
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
    //--- GET MESSAGES ---(ConversationList)//

    // Function to fetch chat rooms for a user
    // Fetch conversations by user ID
    suspend fun getConversationsByUserId(userId: String): List<Conversation> {
        // Fetch chat rooms for the specified user
        val chatRooms = fetchChatRooms(userId)

        // Map each chat room to a Conversation object, including fetching the organization name
        return chatRooms.mapNotNull { chatRoom ->
            val lastMessage =
                chatRoom.messages.maxByOrNull { it.timestamp } // Find the latest message based on timestamp
            chatRoom.documentId?.let { conversationId ->  // Ensure the chat room ID is not null
                lastMessage?.let { message ->
                    val orgName =
                        fetchOrganizationName(chatRoom.orgid)  // Fetch the organization name asynchronously
                    Conversation(
                        orgName,
                        message,
                        conversationId
                    )  // Create a new Conversation object with all required fields
                }
            }
        }
    }

    // Function to fetch chat rooms for a user
    suspend fun fetchChatRooms(userId: String): List<ChatRoom> {
        return db.collection("Chat")
            .whereEqualTo("userid", userId)
            .orderBy("time", Query.Direction.DESCENDING)
            .get()
            .await()
            .toObjects(ChatRoom::class.java)
    }

    // Function to fetch the organization name by orgId
    suspend fun fetchOrganizationName(orgId: String): String {
        // Log the orgId that is being fetched
        Log.d("FetchOrg", "Fetching name for orgId: $orgId")

        // Attempt to fetch the organization document from Firestore
        val orgDocument = db.collection("Organizations")
            .document(orgId)
            .get()
            .await()

        // Convert the document to Organization object
        val organization = orgDocument.toObject(Organization::class.java)

        // Log the fetched organization name, or log "null" if not found
        Log.d("FetchOrg", "Fetched Organization: ${organization?.name ?: "null"}")

        // Return the organization name if available, otherwise return "Unknown Organization"
        return organization?.name ?: "Unknown Organization"
    }




    // Function to get messages for the chosen conversation
    suspend fun getMessagesForConversation(conversationId: String): Flow<List<Message>> = flow {
        val snapshot = FirebaseFirestore.getInstance()
            .collection("Chat")
            .whereEqualTo("conversationId", conversationId)
            .orderBy("timestamp", Query.Direction.ASCENDING)
            .get()
            .await()

        // Convert a document to a message object
        val messages = snapshot.documents.map { document ->
            document.toObject(Message::class.java)
        }
    }




    // Get OrgId for the chosen organisation on Chatscreen
    suspend fun getOrgId(orgId: String): Organization? {
        val snapshot = db.collection("Organizations")
            .whereEqualTo("orgID", orgId)
            .get()
            .await()

        return snapshot.documents.firstOrNull()?.toObject(Organization::class.java)
    }
}










