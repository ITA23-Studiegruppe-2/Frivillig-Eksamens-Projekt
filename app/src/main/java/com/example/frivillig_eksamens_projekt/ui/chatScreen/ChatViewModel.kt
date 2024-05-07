package com.example.frivillig_eksamens_projekt.ui.chatScreen

/*
class ChatViewModel : ViewModel() {
    private val repository = ChatRepository() // Antagelse om, at der er en repository-klasse for at håndtere Firestore-interaktioner

    init {
        listenForMessages()
    }

    // Tilstand for beskeder
    private val _messages = MutableStateFlow<List<Message>>(emptyList())
    val messages: StateFlow<List<Message>> = _messages

    // Tilstand for søgequery
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    // Funktion til at sende en besked
    fun sendMessage(orgId: String, userId: String, message: String) {
        viewModelScope.launch {
            repository.sendMessage(orgId, userId, message)
        }
    }

    // Funktion til at lytte efter nye beskeder
    fun listenForMessages() {
        viewModelScope.launch {
            repository.listenForMessages { messages ->
                _messages.value = messages
            }
        }
    }

    // Funktion til at opdatere søgequery
    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }
}


 */
