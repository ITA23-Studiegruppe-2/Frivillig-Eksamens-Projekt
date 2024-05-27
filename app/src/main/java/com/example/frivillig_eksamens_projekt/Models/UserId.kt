package com.example.frivillig_eksamens_projekt.Models

import com.google.firebase.firestore.DocumentId

data class UserId(
    @DocumentId val documentId: String,
    val userUId: String
)