package com.example.frivillig_eksamens_projekt.Models

import com.google.firebase.firestore.DocumentId

/**
 *
 * @author Rasmus Planteig
 *
 */
data class UserId(
    @DocumentId val documentId: String,
    val userUId: String
)