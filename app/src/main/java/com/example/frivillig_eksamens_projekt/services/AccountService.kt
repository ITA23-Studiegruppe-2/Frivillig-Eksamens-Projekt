package com.example.frivillig_eksamens_projekt.services

import com.example.frivillig_eksamens_projekt.DTO.User
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore

class AccountService {
     fun registerUserAuth(email: String, password: String, onSuccess: () -> Unit, onFail: () -> Unit) {
        Firebase.auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                //Give it the function of what to do
                onSuccess()

                // See Documentation for how the storing of users in the database works - in our docs
                println("Success")

            }
            .addOnFailureListener { exception ->
                // Get the reason for the failure and pass it to the onFail callback
                // - If password - Have password failure turn up.
                val errorMessage = when (exception) {
                    is FirebaseAuthException -> exception.message
                    else -> "Authentication failed"
                }
                println(errorMessage)
                onFail()
            }
    }

    fun login(email: String, password: String, onSuccess: () -> Unit, onFailure: () -> Unit) {
        Firebase.auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                //Give it the function of what to do
                onSuccess()
            }
            .addOnFailureListener {
                onFailure()
            }

    }

    // Store userInformation in the database

    // I dont think that database should be initialized in here - Maybe add a repository to this? (Seems weird )
    private val db = Firebase.firestore

      fun createUserDB(
         fullName: String,
         phoneNumber: String,
         zipCode: String,
         birthDate: String,
         gender: String,
         onSuccess: () -> Unit,
         onFail: () -> Unit
     ) {
        // Get the Auth uID
        val userUID = Firebase.auth.currentUser?.uid
         println("User UID INSIDE OF DB FUNCTION $userUID")

        val userToAddToDatabase: User = User(
            fullName = fullName,
            phoneNumber = phoneNumber,
            zipCode = zipCode,
            birthDate = birthDate,
            gender = gender,
            userUID = userUID
        )

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
                    onFail()
                }
        } else {
            println("No userUID Found?")
            // Handle errors? if the Authentication went wrong - but that should have been handled at a sooner state.
        }




    }
}