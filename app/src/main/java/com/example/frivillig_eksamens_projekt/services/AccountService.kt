package com.example.frivillig_eksamens_projekt.services

import com.example.frivillig_eksamens_projekt.DTO.User
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore

class AccountService {
     fun registerUserAuth(email: String, password: String, onSuccess: () -> Unit, onFail: (String) -> Unit) {
        Firebase.auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                //Give it the function of what to do
                onSuccess()

                // See Documentation for how the storing of users in the database works - in our docs
                println("Success")

            }
            .addOnFailureListener { exception ->

                // Get the reason for the failure and pass it to the onFail callback
                // - If password - Have password failure turn up. - Should be handled with regex in the viewmodel - todo
                // Find all error codes and make a when? - todo


                // List of Error codes
                /*
                auth/email-already-in-use - todo
                auth/network-request-failed - todo
                auth/weak-password - todo

                Handle else - on unknown errors - todo
                 */
                var errorMessage = when (exception) {
                    is FirebaseAuthException -> when(exception.errorCode) {
                        "ERROR_EMAIL_ALREADY_IN_USE" -> "Email is already in use"
                        "ERROR_NETWORK_REQUEST_FAILED" -> "Check internet"
                        "ERROR_WEAK_PASSWORD" -> "Weak password"
                        else -> {
                            "An unknown error has occurred, please try again later!"
                        }
                    }
                    else -> {
                        "An unknown error has occurred"
                    }
                }
                println(exception)
                // Provide the errorMessages to the Composable/ViewModel and let the user know whats wrong
                onFail(errorMessage)
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