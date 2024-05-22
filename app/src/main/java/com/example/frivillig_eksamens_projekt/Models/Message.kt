package com.example.frivillig_eksamens_projekt.Models

data class Message(
    val content: String = "",
    val senderId: String = "",
    val timestamp: Long = 0L,
    val userIds: String = "",
    val orgId: String = "",
    val isOwn: Boolean = true
)
