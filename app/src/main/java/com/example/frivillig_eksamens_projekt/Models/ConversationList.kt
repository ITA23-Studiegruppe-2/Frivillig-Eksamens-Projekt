package com.example.frivillig_eksamens_projekt.Models

/**
 *
 * @author Lucas Jacobsen
 *
 */
data class Conversation(
    val organizationName: String = "",
    val lastMessage: Message = Message(),
    val conversationId: String = ""
)
