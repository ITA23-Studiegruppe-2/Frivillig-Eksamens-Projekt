package com.example.frivillig_eksamens_projekt.ui.chatScreen.sendMessageScreen.organisation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.frivillig_eksamens_projekt.Models.Message
import com.example.frivillig_eksamens_projekt.repositories.ChatRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class OrgChatViewModel(
    conversationId: String,
    activityId: String
) : ViewModel() {

    val orgChatRepository = OrgChatRepository()
    val chatRepository: ChatRepository = ChatRepository()
    val messages = MutableStateFlow<List<Message>>(emptyList())
    var orgId by mutableStateOf("")
    val secondaryColor = Color(0xFF364830)
    val backgroundColor = Color(0xFFC8D5B9)

    // Get current user ID from FirebaseAuth
    val currentUserId: String = FirebaseAuth.getInstance().currentUser?.uid ?: ""



    init {
        viewModelScope.launch {
            orgId = orgChatRepository.fetchOrganizationsId(conversationId)
            initializeChat(conversationId, activityId)
        }
    }

    // Listen for changes in conversationId, and start again
    fun initializeChat(conversationId: String, activityId: String) {
        loadMessages(conversationId)
        listenToMessages(conversationId)
        initializeChatForApprovedUsers(activityId, orgId)
    }



    //------------------RESUME CONVERSATION-----------------//
    fun loadMessages(conversationId: String) {
        viewModelScope.launch {
            chatRepository.getMessagesForConversation(conversationId).collect { newMessages ->
                messages.value = newMessages
            }
        }
    }

    //-------------------------- SHOW MESSAGES AND ADD NEW MESSAGES TO CHAT -------------------//
    // Listen to messages in real-time
    fun listenToMessages(conversationId: String) {
        viewModelScope.launch {
            orgChatRepository.getMessages(conversationId).collect { newMessages ->
                messages.value = newMessages
            }
        }
    }

    //----------------------- CREATE CHATROOM ---------------------//
    fun createChatroomWithUsers(activityId: String, userIds: List<String>, orgId: String) {
        if (userIds.isEmpty()) {
            println("User IDs list is empty, cannot create/update chatroom")
            return
        }
        val timestamp = System.currentTimeMillis()
        viewModelScope.launch {
            try {
                val result = orgChatRepository.createOrUpdateChatroomWithUsers(
                    activityId,
                    userIds,
                    orgId,
                    timestamp
                )
                if (result) {
                    println("Chatroom created/updated successfully.")
                    listenToMessages(activityId)
                } else {
                    println("Failed to create/update chatroom.")
                }
            } catch (e: Exception) {
                println("Failed to create/update chatroom: ${e.message}")
            }
        }
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

    //--------------------- SEND GROUP MESSAGE ----------------------//
    // Send a group message
    fun sendGroupMessage(activityId: String, messageText: String, orgId: String) {
        if (messageText.isNotBlank()) {
            val timestamp = System.currentTimeMillis()
            viewModelScope.launch {
                try {
                    val senderName =
                        orgChatRepository.fetchFullNameOfUsers(currentUserId) // Fetch the sender's full name
                    val result = orgChatRepository.sendGroupMessage(
                        activityId,
                        messageText,
                        currentUserId,
                        senderName,
                        orgId,
                        timestamp
                    )
                    if (result) {
                        println("Message sent successfully to group.")
                    } else {
                        println("Failed to send message to group.")
                    }
                } catch (e: Exception) {
                    println("Failed to send message to group: ${e.message}")
                }
            }
        }
    }
}
