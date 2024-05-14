package com.example.frivillig_eksamens_projekt.repositories

import com.example.frivillig_eksamens_projekt.DTO.Message
import com.example.frivillig_eksamens_projekt.DTO.Organization
import com.google.ai.client.generativeai.Chat
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await

// Repository til håndtering af chatdata i Firebase Firestore-databasen.
// Det indeholder funktioner til at sende beskeder til databasen og lytte efter nye beskeder.

class ChatRepository {
    val db = Firebase.firestore

    ///--- ORGANISATIONER ---///
    suspend fun getOrganizations(): MutableList<Organization> =
        db.collection("Organizations")
            .get()
            .await()
            .toObjects(Organization::class.java)


    // Søge efter organisationer i databasen
    suspend fun searchOrganizations(name: String): MutableList<Organization> =
        db.collection("Organizations")
            .whereEqualTo("name", name)
            .get()
            .await()
            .toObjects(Organization::class.java)



    ///---BESKEDER---///

    // Funktion til at sende en besked til databasen.
    suspend fun sendMessage(userId: String, message: Message) {
        db.collection("Chat")
            .document(userId)
            .collection("messages")
            .add(message)
            .await()
    }




    // Henter beskeder der matcher på userid
        suspend fun findChat(userId: String): MutableList<Chat> =
        db.collection("Chat")
            .whereEqualTo("userid", userId)
            .get()
            .await()
            .toObjects(Chat::class.java)
    }

