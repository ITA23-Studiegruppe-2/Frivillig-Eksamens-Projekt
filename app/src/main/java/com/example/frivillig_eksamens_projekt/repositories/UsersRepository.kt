package com.example.frivillig_eksamens_projekt.repositories

import androidx.privacysandbox.ads.adservices.adid.AdId
import com.example.frivillig_eksamens_projekt.DTO.User
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await

class UsersRepository() {
    val db = Firebase.firestore

    suspend fun getUser(userId: String): User? = db.collection("Users")
        .document(userId)
        .get()
        .await()
        .toObject(User:: class.java)


}