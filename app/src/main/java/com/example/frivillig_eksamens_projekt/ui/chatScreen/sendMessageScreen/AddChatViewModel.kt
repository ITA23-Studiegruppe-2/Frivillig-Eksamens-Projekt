package com.example.frivillig_eksamens_projekt.ui.chatScreen.sendMessageScreen


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


// ViewModel for managing chat interactions in the AddChatScreen.
class AddChatViewModel : ViewModel() {
    var backgroundColor by mutableStateOf(Color(0xFFC8D5B9))
    val chatRepository = ChatRepository()
    val messages = MutableStateFlow<List<Message>>(emptyList())


    // Current userID from firebase and selected OrganizationID to display chats from
    var currentUserId = mutableStateOf(FirebaseAuth.getInstance().currentUser?.uid)
    var currentOrgId = mutableStateOf<String?>(null)

    init {
        // // Initialize and load messages if user and organization IDs are present
        currentUserId.value?.let { userId ->
            currentOrgId.value?.let { orgId ->
                loadMessages(userId, orgId)
            }
        }
    }

//--------------------------------------------////


    //----- WRITE MESSAGES -----//

    // Function to send message. (addChatScreen)
    fun addMessage(messageText: String) {
        println("Attempting to send message with orgId: ${currentOrgId.value}")
        val orgId = currentOrgId.value ?: return
        val userId = currentUserId.value ?: return

        if (messageText.isNotBlank()) {
            val timestamp = System.currentTimeMillis()
            chatRepository.addOrUpdateChat(userId, orgId, messageText, timestamp,
                onSuccess = {
                    println("Chat updated or created successfully.")
                },
                onFailure = { e ->
                    println("Error updating or creating chat: ${e.message}")
                }
            )
        }
    }


    //  (AddChatScreen)
    // Loads messages for a specific chat based on user ID and org ID.
    fun loadMessages(userId: String, orgId: String) {
        viewModelScope.launch {
            chatRepository.getMessages(userId, orgId).collect { loadedMessages ->
                messages.value = loadedMessages
            }
        }
    }


    // To resume a chat
    // Initializes chat for an existing conversation using its ID.
    fun initialize(conversationId: String) {
        loadMessages(conversationId)
    }


    // Load messages based on conversationId
    private fun loadMessages(conversationId: String) {
        viewModelScope.launch {
            chatRepository.getMessagesForConversation(conversationId).collect { loadedMessages ->
                messages.value = loadedMessages
            }
        }
    }


    // Initializes chat with a specific organization ID and loads relevant organization details.
    fun initializeWithOrgId(orgId: String) {
        println("Initializing with orgId: $orgId")
        currentOrgId.value = orgId
        viewModelScope.launch {
            try {
                val organization = chatRepository.getOrgId(orgId)
                if (organization != null) {
                    println("Organization loaded: ${organization.name}")

                } else {
                    println("Organization not found")
                }
            } catch (e: Exception) {
                println("Failed to load organization: ${e.message}")
            }
        }
    }
}


