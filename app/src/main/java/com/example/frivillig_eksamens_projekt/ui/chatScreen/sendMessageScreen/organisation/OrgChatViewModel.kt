package com.example.frivillig_eksamens_projekt.ui.chatScreen.sendMessageScreen.organisation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.frivillig_eksamens_projekt.Models.Message
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class OrgChatViewModel: ViewModel(){
    val orgChatRepository = OrgChatRepository()
    val messages = MutableStateFlow<List<Message>>(emptyList())

    // Get current user ID from FirebaseAuth
    val currentUserId: String = FirebaseAuth.getInstance().currentUser?.uid ?: ""



    private fun createChatroomWithUsers(activityId: String, userIds: List<String>, orgId: String) {
        if (userIds.isEmpty()) {
            Log.w("createChatroomWithUsers", "User IDs list is empty, cannot create/update chatroom")
            return
        }
        val timestamp = System.currentTimeMillis()
        orgChatRepository.createOrUpdateChatroomWithUsers(activityId, userIds, orgId, timestamp,
            onSuccess = {
                println("Chatroom created/updated successfully.")
                listenToMessages(activityId)
            },
            onFailure = { e -> println("Failed to create/update chatroom: ${e.message}") }
        )
    }

    fun initializeChatForApprovedUsers(activityId: String, orgId: String) {
        orgChatRepository.fetchApprovedUserIds(activityId,
            onSuccess = { userIds ->
                // Once user IDs are fetched, create or update the chatroom
                if (userIds.isNotEmpty()) {
                    createChatroomWithUsers(activityId, userIds, orgId)
                }
            },
            onFailure = { e ->
                // Log or handle errors when fetching user IDs
                println("Error fetching approved users: ${e.message}")
            }
        )
    }




    // Send a group message
    fun sendGroupMessage(activityId: String, messageText: String, orgId: String) {
        if (messageText.isNotBlank()) {
            val timestamp = System.currentTimeMillis()
            orgChatRepository.sendGroupMessage(activityId, messageText, currentUserId, orgId, timestamp,
                onSuccess = {
                    println("Message sent successfully to group.")
                   },
                onFailure = { e ->
                    println("Failed to send message to group: ${e.message}")
                }
            )
        }
    }

    // Listen to messages in realtime
    private fun listenToMessages(activityId: String) {
        viewModelScope.launch {
            orgChatRepository.getMessages(activityId).collect { newMessages ->
                messages.value = newMessages
            }
        }
    }


}