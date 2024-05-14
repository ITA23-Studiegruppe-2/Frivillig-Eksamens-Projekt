package com.example.frivillig_eksamens_projekt.repositories

import androidx.privacysandbox.ads.adservices.adid.AdId
import com.example.frivillig_eksamens_projekt.DTO.User
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await

class UsersRepository() {
    private val db = Firebase.firestore

    suspend fun getUser(userId: String): User? = db.collection("Users")
        .document(userId)
        .get()
        .await()
        .toObject(User::class.java)


     fun addUserToDatabase(user: User, userUID: String, onSuccess: () -> Unit, onFail: (String) -> Unit) {
        db.collection("Users").document(userUID)
            .set(user)
            .addOnSuccessListener {
                // Send the user to home page - Successful registration
                onSuccess()

            }
            .addOnFailureListener {
                // Handle what errors we get
                // maybe if one of the fields are missing
                onFail("There was an error trying to reach the database!")
                // We should handle the deletion of the user stored in Authentication - TODO
            }

    }
}