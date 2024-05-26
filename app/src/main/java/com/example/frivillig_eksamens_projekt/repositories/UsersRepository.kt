package com.example.frivillig_eksamens_projekt.repositories

import com.example.frivillig_eksamens_projekt.Models.User
import com.example.frivillig_eksamens_projekt.Models.UserId
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await

class UsersRepository() {
    private val db = Firebase.firestore

    val currentUser = Firebase.auth.currentUser?.uid

     suspend fun getUser(): User? = currentUser?.let {
         db.collection("Users")
         .document(it)
         .get()
         .await()
         .toObject(User::class.java)
     }




     fun addUserToDatabase(user: User, userUID: String, onSuccess: () -> Unit, onFail: (String) -> Unit) {
        db.collection("Users")
            .document(userUID)
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

    fun addActivityIdToUserSubCollection(activityId: String, userId: String) {
        val activityIdObject = hashMapOf(
            "activityId" to activityId
        )
        db.collection("Users")
            .document(userId)
            .collection("MyActivities")
            .document(activityId)
            .set(activityIdObject)
    }

    fun removeActivityIdFromUserSubCollection(activityId: String,userId: String) {
        db.collection("Users")
            .document(userId)
            .collection("MyActivities")
            .document(activityId)
            .delete()
    }
}

