package com.example.frivillig_eksamens_projekt.repositories

import com.example.frivillig_eksamens_projekt.Models.Organization
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await
import java.util.UUID

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
    // Der genereres et unikt ID til beskeden
    suspend fun sendMessage(userId: String, message: String) {
        val messageId = UUID.randomUUID().toString() // Generer et ID til beskeden

        // Opretter et map med beskeddata
        val messageData = hashMapOf(
            "userId" to userId,
            "message" to message,
            "timestamp" to System.currentTimeMillis()
        )

        // Send beskeddata til Firestore-databasen
        db.collection("Chats")
            .document()
            .collection("messages")
            .document(messageId)
            .set(messageData)
            .await()
    }
}
