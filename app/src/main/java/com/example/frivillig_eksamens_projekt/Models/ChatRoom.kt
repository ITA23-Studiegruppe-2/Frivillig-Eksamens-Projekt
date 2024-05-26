package com.example.frivillig_eksamens_projekt.DTO

import com.example.frivillig_eksamens_projekt.Models.Message
import com.google.firebase.firestore.DocumentId


//Message med et object med message = String, og id for user

data class ChatRoom(
    @DocumentId var documentId: String? = null,
    val messages: List<Message> = emptyList(), //Ændrer til array empty
    val userIds: List<String> = emptyList(),
    val orgId: String = "",
    val time: Long = 0L,
    var organizationName: String = "",
    val id: String = ""

)