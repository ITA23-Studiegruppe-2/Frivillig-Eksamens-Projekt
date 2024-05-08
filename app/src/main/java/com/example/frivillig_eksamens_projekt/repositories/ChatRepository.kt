package com.example.frivillig_eksamens_projekt.repositories

import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await
import java.util.UUID


// Repository til håndtering af chatdata i Firebase Firestore-databasen.
// Det indeholder funktioner til at sende beskeder til databasen og lytte efter nye beskeder.

class ChatRepository {
    private val db = Firebase.firestore

    // Funktion til at sende en besked til databasen.
    // Der generes et unikt ID til beskeden
    suspend fun sendMessage(userId: String, message: String) {
        val messageId = UUID.randomUUID().toString() // Generer et ID til beskeden

        // Opretter et map af med beskeddata
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

    // Funktion til at lytte efter nye beskeder fra databasen
    fun listenForMessages() {
        //ToDo Implementer lyttefunktionalitet her
    }
}