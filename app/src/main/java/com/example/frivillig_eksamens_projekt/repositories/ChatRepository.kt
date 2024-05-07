package com.example.frivillig_eksamens_projekt.repositories

import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await
import java.util.UUID

class ChatRepository {
    val db = Firebase.firestore

    suspend fun sendMessage(senderId: String, receiverId: String, message: String) {
        val messageId = UUID.randomUUID().toString() // Generer et unikt ID til beskeden

        val messageData = hashMapOf(
            "senderId" to senderId,
            "receiverId" to receiverId,
            "message" to message,
            "timestamp" to System.currentTimeMillis()
        )

        db.collection("Chats")
            .document()
            .collection("messages")
            .document(messageId)
            .set(messageData)
            .await()
    }
}