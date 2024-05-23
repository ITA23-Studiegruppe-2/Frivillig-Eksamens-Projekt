package com.example.frivillig_eksamens_projekt.ui.chatScreen.sendMessageScreen.organisation

import android.util.Log
import com.example.frivillig_eksamens_projekt.DTO.ChatRoom
import com.example.frivillig_eksamens_projekt.Models.Message
import com.google.firebase.Firebase
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class OrgChatRepository {
    val db = Firebase.firestore

    // ----------------------- FESTCH APPROVED USERS -------------------------//
    // Fetch approved user IDs from the 'userApproved' subcollection for a specified activity
    fun fetchApprovedUserIds(
        activityId: String,
        onSuccess: (List<String>) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        val activityRef = db.collection("Activites").document(activityId)
        activityRef.collection("userApproved")
            .get()
            .addOnSuccessListener { querySnapshot ->
                if (!querySnapshot.isEmpty) {
                    val userIds = querySnapshot.documents.mapNotNull { it.getString("userUID") }
                    onSuccess(userIds)
                } else {
                    onSuccess(emptyList()) // Return an empty list if there are no approved users
                }
            }
            .addOnFailureListener { exception ->
                onFailure(exception)
            }
    }

    // -------------------- CREATE OR UPDATE CHATROOM ---------------------//
    // Function to create or update a chatroom with userIds
    fun createOrUpdateChatroomWithUsers(
        activityId: String,
        userIds: List<String>,
        orgId: String,
        timestamp: Long,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        val chatRoomRef = db.collection("Chat").document(activityId)
        chatRoomRef.get().addOnSuccessListener { document ->
            if (document.exists()) {
                // Update the existing chatroom with new user IDs and timestamp
                chatRoomRef
                    .update(
                        "userIds",
                        FieldValue.arrayUnion(*userIds.toTypedArray()),
                        "time",
                        timestamp
                    )
                    .addOnSuccessListener {
                        Log.d("CreateOrUpdateChatroom", "Chat room updated successfully")
                        onSuccess()
                    }
                    .addOnFailureListener { exception ->
                        Log.e(
                            "CreateOrUpdateChatroom",
                            "Failed to update chat room: ${exception.message}"
                        )
                        onFailure(exception)
                    }
            } else {
                // Create a new chatroom with user IDs
                val newChatRoomData = mapOf(
                    "messages" to emptyList<Map<String, Any>>(),
                    "time" to timestamp,
                    "userIds" to userIds,
                    "orgId" to orgId
                )
                chatRoomRef.set(newChatRoomData)
                    .addOnSuccessListener {
                        Log.d("CreateOrUpdateChatroom", "New chat room created successfully")
                        onSuccess()
                    }
                    .addOnFailureListener { exception ->
                        Log.e(
                            "CreateOrUpdateChatroom", "Failed to create chat room: ${exception.message}"
                        )
                        onFailure(exception)
                    }
            }
        }
    }

    // ----------------------- SEND GROUP MESSAGE -------------------------- //
    // Send a group message to a specific activity's chatroom
    fun sendGroupMessage(
        activityId: String,
        messageText: String,
        senderId: String,
        orgId: String,
        timestamp: Long,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        val chatRoomRef = db.collection("Chat").document(activityId)
        chatRoomRef.get().addOnSuccessListener { document ->
            if (document.exists()) {
                // Add the new message to the existing messages array in the chatroom document
                Log.d("SendMessage", "Chat room exists for activityId: $activityId")
                val newMessage = mapOf(
                    "content" to messageText,
                    "timestamp" to timestamp,
                    "senderId" to senderId,
                    "orgId" to orgId
                )
                chatRoomRef
                    .update("messages", FieldValue.arrayUnion(newMessage), "time", timestamp)
                    .addOnSuccessListener { onSuccess() }
                    .addOnFailureListener(onFailure)
            } else {
                // Create a new chatroom document with the message
                val newChatRoomData = mapOf(
                    "messages" to listOf(
                        mapOf(
                            "content" to messageText,
                            "timestamp" to timestamp,
                            "senderId" to senderId,
                            "orgId" to orgId
                        )
                    ),
                    "time" to timestamp,
                    "userIds" to emptyList<String>(),
                    "orgId" to orgId
                )
                chatRoomRef.set(newChatRoomData)
                    .addOnSuccessListener { onSuccess() }
                    .addOnFailureListener(onFailure)
            }
        }.addOnFailureListener(onFailure)
    }


    // --------------------- GET MESSAGES ON SCREEN -----------------//
    // Function to get messages on screen to chat in real time
    fun getMessages(activityId: String): Flow<List<Message>> = callbackFlow {
        val query = db.collection("Chat")
            .document(activityId)

        // Add a snapshot listener to listen for real-time updates to the document
        val listener = query.addSnapshotListener { snapshot, exception ->
            // If there is an exception, close the flow and return
            if (exception != null) {
                close(exception)
                return@addSnapshotListener
            }
            // If the snapshot exists and is not null, process the messages
            if (snapshot != null && snapshot.exists()) {
                // Convert the snapshot to a ChatRoom object and get the list of messages
                val messages = snapshot.toObject(ChatRoom::class.java)?.messages ?: listOf()
                // Send the messages list through the flow
                trySend(messages)
            } else {
                // If the snapshot is null or doesn't exist, send an empty list
                trySend(listOf())
            }
        }
        // Ensure the listener is removed when the flow is closed
        awaitClose { listener.remove() }
    }
}