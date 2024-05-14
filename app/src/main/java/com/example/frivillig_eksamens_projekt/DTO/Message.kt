package com.example.frivillig_eksamens_projekt.DTO

import com.google.firebase.firestore.DocumentId

data class Message(
    @DocumentId var documentId: String? = null,
    val message: String = "",
    val orgid: String = "",
    val userid: String = ""
)
