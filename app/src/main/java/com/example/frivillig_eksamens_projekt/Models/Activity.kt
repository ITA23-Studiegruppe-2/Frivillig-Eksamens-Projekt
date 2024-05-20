package com.example.frivillig_eksamens_projekt.Models

import com.google.firebase.firestore.DocumentId

data class Activity(
    val title: String = "",
    val organization: String = "",
    val date: String = "",
    val timeStamp: String = "",
    @DocumentId var documentId: String? = null,
    val listOfUsersApplied: MutableList<String> = mutableListOf(),
    val description: String = "",
    val location: String = "",
    val organisationId: String = ""
)
