package com.example.frivillig_eksamens_projekt.Models

data class Conversation(
    val orgName: String,
    val lastMessage: Message,
    val conversationId: String
)
