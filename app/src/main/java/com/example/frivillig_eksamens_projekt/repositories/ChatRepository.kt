package com.example.frivillig_eksamens_projekt.repositories

import com.example.frivillig_eksamens_projekt.DTO.ChatRoom
import com.example.frivillig_eksamens_projekt.Models.Message
import com.example.frivillig_eksamens_projekt.Models.Organization
import com.google.firebase.Firebase
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

// Repository til håndtering af chatdata i Firebase Firestore-databasen.
// Det indeholder funktioner til at sende beskeder til databasen og lytte efter nye beskeder.

class ChatRepository {
    val db = Firebase.firestore


    ///--- ORGANISATIONER ---  (Chat)///

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




    ///---BESKEDER---///
    // Function to send messages to database. (addChatScreen)
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
                documentRef.update("messages", FieldValue.arrayUnion(mapOf("content" to messageText, "timestamp" to timestamp)),
                    "time", timestamp)
                    .addOnSuccessListener { onSuccess() }
                    .addOnFailureListener(onFailure)
            }
        }.addOnFailureListener(onFailure)
    }



    // Function to get messages on screen to chat (addChatScreen)
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





    //--- HENTE BESKEDER ---//

    // Function to get a list of messages (ConversationList)
    suspend fun getMessagesByUserId(userId: String): List<Message> {
        return db.collection("Chat")
            .whereEqualTo("userid", userId)
            .orderBy("timestamp", Query.Direction.DESCENDING)
            .get()
            .await()
            .toObjects(Message::class.java)
    }
}










