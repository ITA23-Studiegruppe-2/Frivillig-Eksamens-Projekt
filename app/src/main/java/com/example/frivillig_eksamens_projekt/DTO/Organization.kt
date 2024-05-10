package com.example.frivillig_eksamens_projekt.DTO

import com.google.firebase.firestore.DocumentId

data class Organization(
    @DocumentId var documentId: String? = null,
    val cvrNumber: Int = 0,
    val email: String = "",
    val name: String = "",
    val password: String = ""
)