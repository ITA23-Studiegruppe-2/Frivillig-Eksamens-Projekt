package com.example.frivillig_eksamens_projekt.ui.chatScreen

import android.os.Message
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.frivillig_eksamens_projekt.repositories.ChatRepository
import kotlinx.coroutines.launch

// Håndterer logikken bag visningen af beskeder og søgning i chatten.
class ChatViewModel(private val chatRepository: ChatRepository) : ViewModel() {

    // Tilstand for beskeder
    val messages: State<List<Message>> = mutableStateOf(emptyList())

    // Tilstand for søgequery
    val searchQuery: State<String> = mutableStateOf("")


   // Funktion til at sende en besked.
   // Den sender en besked til chatrepository og håndterer asynkron opgave med coroutines.
    fun sendMessage(userId: String, message: String) {
        viewModelScope.launch {
            chatRepository.sendMessage(userId, message)
        }
    }

    // Funktion til at opdatere søgequery
    fun updateSearchQuery(query: String) {
        (searchQuery as? MutableState)?.value = query
    }
}
