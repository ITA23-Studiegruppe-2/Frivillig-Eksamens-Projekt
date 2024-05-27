package com.example.frivillig_eksamens_projekt.Models

import com.google.firebase.firestore.DocumentId

data class Notification(
    @DocumentId val documentId: String? = null,
    val title: String = "",
    val message: String = ""
)