package com.example.frivillig_eksamens_projekt.ui.chatScreen.sendMessageScreen.organisation

import  android.util.Log
import com.example.frivillig_eksamens_projekt.DTO.ChatRoom
import com.example.frivillig_eksamens_projekt.Models.Activity
import com.example.frivillig_eksamens_projekt.Models.Message
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
/**
 *
 * @author Lucas Jacobsen
 *
 */
class OrgChatRepository {
    val db = Firebase.firestore


    // ----------------------- FETCH APPROVED USERS -------------------------//
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


    //--------------- FETCH ORGANISATION'S NAME ----------------------------//
    //retrieves the name of an organization based on the provided organizationName document ID
    // from the Activites collection
    suspend fun fetchNameOfOrganization(organizationName: String): String {
        val activityDocument = db.collection("Activites")
            .document(organizationName)
            .get()
            .await()
        // Convert the document to Activity object
        val activity = activityDocument.toObject(Activity::class.java)

        // Log the fetched organization name, or log "null" if not found
        val organizationName = activity?.organization ?: "Unknown Organization"
        Log.d("FetchOrg", "Fetched Organization: $organizationName")

        // Return the organization name if available, otherwise return "Unknown Organization"
        return organizationName
    }



    //-------------------- FETCH FULLNAME OF USERS -------------------//
    // Function to fetch the full name of users or organization name.
    suspend fun fetchFullNameById(id: String): String {
        // Try to fetch the user first
        val userSnapshot = db.collection("Users")
            .document(id)
            .get()
            .await()

        if (userSnapshot.exists()) {
            return userSnapshot.getString("fullName") ?: "Unknown User"
        }

        // If user does not exist, try to fetch the organization
        val orgSnapshot = db.collection("Organizations")
            .document(id)
            .get()
            .await()

        if (orgSnapshot.exists()) {
            return orgSnapshot.getString("name") ?: "Unknown Organization"
        }

        return "Unknown User"
    }


    //--------------------- FETCH ORGANIZATIONS ID ------------------//
    // retrieves the organization ID for a specified activity from the Activites collection
    suspend fun fetchOrganizationsId(activityId: String): String {
        val activityDocument = db.collection("Activites")
            .document(activityId)
            .get()
            .await()
        return activityDocument.getString("organisationId") ?: "unknownOrgId"
    }







    // -------------------- CREATE OR UPDATE CHATROOM ---------------------//
    // Function to create or update a chatroom with userIds and organization ID.
    // It also sets the organization name and the timestamp
    suspend fun createOrUpdateChatroomWithUsers(
        activityId: String,
        userIds: List<String>,
        orgId: String,
        timestamp: Long
    ): Boolean {
        val chatRoomRef = db.collection("Chat").document(activityId)
        val document = chatRoomRef.get().await()
        val organizationName = fetchNameOfOrganization(activityId)

        return if (document.exists()) {
            // Update the existing chatroom with new user IDs and timestamp
            chatRoomRef
                .update(
                    "userIds",
                    FieldValue.arrayUnion(*userIds.toTypedArray()),
                    "time",
                    timestamp,
                    "organizationName",
                    organizationName
                )
                .await()
            Log.d("CreateOrUpdateChatroom", "Chat room updated successfully")
            true
        } else {
            // Create a new chatroom with user IDs
            val newChatRoomData = mapOf(
                "messages" to emptyList<Map<String, Any>>(),
                "time" to timestamp,
                "userIds" to userIds,
                "orgId" to orgId,
                "organizationName" to organizationName
            )
            chatRoomRef.set(newChatRoomData).await()
            Log.d("CreateOrUpdateChatroom", "New chat room created successfully")
            true
        }
    }

    // ----------------------- SEND GROUP MESSAGE -------------------------- //
    //  adds the new message to the existing messages array in the
    //  chatroom document or creates a new chatroom
    //  document with the message if it does not already exist
    suspend fun sendGroupMessage(
        activityId: String,
        messageText: String,
        senderId: String,
        senderName: String,
        orgId: String,
        timestamp: Long
    ): Boolean {
        val chatRoomRef = db.collection("Chat").document(activityId)
        val document = chatRoomRef
            .get()
            .await()
        val organizationName = fetchNameOfOrganization(activityId)

        return if (document.exists()) {
            // Add the new message to the existing messages array in the chatroom document
            Log.d("SendMessage", "Chat room exists for activityId: $activityId")
            val newMessage = mapOf(
                "content" to messageText,
                "timestamp" to timestamp,
                "senderId" to senderId,
                "senderName" to senderName,
                "orgId" to orgId

            )
            chatRoomRef
                .update(
                    "messages",
                    FieldValue.arrayUnion(newMessage),
                    "time",
                    timestamp,
                    "organizationName",
                    organizationName
                )
                .await()
            true
        } else {
            // Create a new chatroom document with the message
            val newChatRoomData = mapOf(
                "messages" to listOf(
                    mapOf(
                        "content" to messageText,
                        "timestamp" to timestamp,
                        "senderId" to senderId,
                        "senderName" to senderName,
                        "orgId" to orgId

                    )
                ),
                "time" to timestamp,
                "userIds" to emptyList<String>(),
                "orgId" to orgId,
                "organizationName" to organizationName
            )
            chatRoomRef.set(newChatRoomData).await()
            true
        }
    }

    // --------------------- GET MESSAGES ON SCREEN -----------------//
    // sets up a real-time listener for a specified activity's chatroom to listen for updates to messages
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
            // Return empty list if there isn't any messages
            val messages = snapshot?.toObject(ChatRoom::class.java)?.messages.orEmpty()
            trySend(messages).isSuccess

        }
        // Ensure the listener is removed when the flow is closed
        awaitClose { listener.remove() }
    }
}