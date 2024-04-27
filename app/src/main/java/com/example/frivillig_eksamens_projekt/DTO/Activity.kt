package com.example.frivillig_eksamens_projekt.DTO

import com.google.firebase.firestore.DocumentId

data class Activity(
    val title: String = "",
    val organization: String = "",
    val date: String = "",
    val timeStamp: String = "",
    @DocumentId var documentId: String? = null
)
