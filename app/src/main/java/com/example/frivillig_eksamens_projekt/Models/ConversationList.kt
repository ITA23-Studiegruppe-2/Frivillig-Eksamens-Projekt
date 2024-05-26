package com.example.frivillig_eksamens_projekt.Models

data class Conversation(
    val organizationName: String = "",
    val lastMessage: Message = Message(),
    val conversationId: String = ""
)
