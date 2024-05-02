package com.example.frivillig_eksamens_projekt.services

import com.example.frivillig_eksamens_projekt.DTO.User
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore

class AccountService {
    suspend fun registerUser(email: String, password: String, onSuccess: () -> Unit, onFail: () -> Unit) {
        Firebase.auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                //Give it the function of what to do

                // See Documentation for how the storing of users in the database works - in our docs

                val userUID = Firebase.auth.uid
            }
            .addOnFailureListener { exception ->
                // Get the reason for the failure and pass it to the onFail callback
                val errorMessage = when (exception) {
                    is FirebaseAuthException -> exception.message
                    else -> "Authentication failed"
                }
                println(errorMessage)
            }
    }

    fun login(email: String, password: String, onSuccess: () -> Unit, onFailure: () -> Unit) {
        Firebase.auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                //Give it the function of what to do
                onSuccess()
            }

    }

    // Store userInformation in the database

    // I dont think that database should be initialized in here - Maybe add a repository to this? (Seems weird )
    val db = Firebase.firestore


     suspend fun createUserDB(
         fullName: String,
         phoneNumber: String,
         zipCode: String,
         birthDate: String,
         gender: String,
         onSuccess: () -> Unit
     ) {
        // Get the Auth uID
        val userUID = Firebase.auth.currentUser?.uid
         println("User UID INSIDE OF DB FUNCTION ${userUID}")

        val userToAddToDatabase: User = User(
            fullName = fullName,
            phoneNumber = phoneNumber,
            zipCode = zipCode,
            birthDate = birthDate,
            gender = gender,
            userUID = userUID
        )

         /*

        // NOTE - WHY DOES IT HAVE TO BE ?
        if (userUID != null) {
            db.collection("Users").document(userUID)
                .set(userToAddToDatabase)
                .addOnSuccessListener {
                    // Send the user to home page - Successful registration
                    onSuccess()

                }
                .addOnFailureListener {
                    // Handle what errors we get
                    // maybe if one of the fields are missing
                }
        } else {
            // Handle errors? if the Authentication went wrong - but that should have been handled at a sooner state.
        }


          */
    }
}