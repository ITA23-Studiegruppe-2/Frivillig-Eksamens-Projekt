package com.example.frivillig_eksamens_projekt.Models

import com.google.firebase.firestore.DocumentId

data class Organization(
    @DocumentId var orgID: String? = null,
    val cvrNumber: String = "",
    val email: String = "",
    val name: String = "",
)