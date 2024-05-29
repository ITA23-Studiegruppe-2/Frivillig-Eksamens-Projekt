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
    var messageText by mutableStateOf("")
    val secondaryColor = Color(0xFF364830)
    val backgroundColor = Color(0xFFC8D5B9)

    // Get current user ID from FirebaseAuth
    val currentUserId: String = FirebaseAuth.getInstance().currentUser?.uid ?: ""



    // Fetches the organization ID based on conversationId and sets up the chat
    init {
        viewModelScope.launch {
            orgId = orgChatRepository.fetchOrganizationsId(conversationId)
            initializeChat(conversationId, activityId)
        }
    }

    // sets up the chat by loading the messages, listening for real-time updates,
    // and setting up the chat for approved users
    fun initializeChat(conversationId: String, activityId: String) {
        loadMessages(conversationId)
        listenToMessages(conversationId)
        initializeChatForApprovedUsers(activityId, orgId)
    }



    // Loads the messages for a given conversation from ChatRepository
    // and updates the messages state with the loaded messages
    fun loadMessages(conversationId: String) {
        viewModelScope.launch {
            chatRepository.getMessagesForConversation(conversationId).collect { newMessages ->
                messages.value = newMessages
            }
        }
    }

    //-------------------------- SHOW MESSAGES AND ADD NEW MESSAGES TO CHAT -------------------//
    // Listens to messages in real-time for a given conversation from OrgChatRepository
    // and updates the messages state with the new messages
    fun listenToMessages(conversationId: String) {
        viewModelScope.launch {
            orgChatRepository.getMessages(conversationId).collect { newMessages ->
                messages.value = newMessages
            }
        }
    }

    // Creates or updates a chatroom with a list of user ID's and the organization's ID.
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

    // Fetches approved user IDs for a given activity and
    // creates or updates the chatroom with these users
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



    // Sends a group message to a given activity's chatroom.
    // It fetches the sender's name and sends the message

    fun sendGroupMessage(activityId: String, messageText: String, orgId: String) {
        if (messageText.isNotBlank()) {
            val timestamp = System.currentTimeMillis()
            viewModelScope.launch {
                try {
                    val senderName =
                        orgChatRepository.fetchFullNameById(currentUserId) // Fetch the sender's full name
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

