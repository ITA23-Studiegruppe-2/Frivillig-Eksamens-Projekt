package com.example.frivillig_eksamens_projekt.Models

import com.google.firebase.firestore.DocumentId

data class Message(
    var senderName: String = "",
    val content: String = "",
    val senderId: String = "",
    val timestamp: Long = 0L,
    val userIds: String = "",
    val orgId: String = "",
    val isOwn: Boolean = true,
    @DocumentId var documentId: String? = null
)
