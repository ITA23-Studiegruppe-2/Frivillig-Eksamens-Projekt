package com.example.frivillig_eksamens_projekt.ui.chatScreen


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


// Håndterer logikken bag visningen af beskeder og søgning i chatten.
class ChatViewModel : ViewModel() {
    var backgroundColor by mutableStateOf(Color(0xFFC8D5B9))
    val chatRepository = ChatRepository()
/*
    var currentUserId = mutableStateOf(FirebaseAuth.getInstance().currentUser?.uid)

 */
    val messages = MutableStateFlow<List<Message>>(emptyList())

/// ----- TEST FOR OM DET VIRKER -----///
    var currentUserId = mutableStateOf<String?>(null)
    var currentOrgId = mutableStateOf<String?>(null)

    init {
        // Simuleret login for test
        simulateLogin("kpKfZQTfKlmOc0f3KcJH", "UFxkscDTPAVilT4X9RTf")
    }

    private fun simulateLogin(userId: String, orgId: String) {
        this.currentUserId.value = userId
        this.currentOrgId.value = orgId
        loadMessages(userId, orgId)
    }



    //----- WRITE MESSAGES -----//

    /*
    init {
        currentUserId.value?.let { userId ->
            loadMessages(userId, orgId)
        }
    }

     */

    // Function to send message. (addChatScreen)
    fun addMessage(messageText: String) {
        val orgId = currentOrgId.value ?: return
        currentUserId.value?.let { userId ->
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
    }

    // Function to get message-update on the screen (addChatScreen) //
   /*
    fun loadMessages(userId: String) {
        viewModelScope.launch {
            chatRepository.getMessages(userId, "specificOrgId").collect { loadedMessages ->
                messages.value = loadedMessages
            }
        }
    }

    */

    fun loadMessages(userId: String, orgId: String) {
        viewModelScope.launch {
            chatRepository.getMessages(userId, orgId).collect { loadedMessages ->
                messages.value = loadedMessages
            }
        }
    }





    //------- HENTE BESKEDER -------   (ConversationList)//

    // Mutable state til at holde styr på de aktuelle beskeder
    var userMessages by mutableStateOf<List<Message>>(emptyList())

    // State for brugerens ID
    var userId by mutableStateOf<String?>(null)

    init { // Henter oplysninger fra den aktive bruger
        FirebaseAuth.getInstance().addAuthStateListener { firebaseAuth ->
            userId = firebaseAuth.currentUser?.uid
            // Opdater beskeder når brugerens ID ændres
            userId?.let { fetchMessages(it) }
        }
    }
    fun fetchMessages(userId: String) {
        viewModelScope.launch {

            userMessages = chatRepository.getMessagesByUserId(userId)
        }
    }
}

