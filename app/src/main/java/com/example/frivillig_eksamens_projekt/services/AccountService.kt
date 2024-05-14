package com.example.frivillig_eksamens_projekt.services

import com.example.frivillig_eksamens_projekt.DTO.User
import com.example.frivillig_eksamens_projekt.repositories.UsersRepository
import com.google.firebase.Firebase
import com.google.firebase.FirebaseError
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore

class AccountService {
     fun registerUserAuth(email: String, password: String, onSuccess: () -> Unit, onFail: (String) -> Unit) {
        Firebase.auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                //Give it the function of what to do
                onSuccess()
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
                        "FIRAuthErrorCodeNetworkError" -> "Check your internet"
                        else -> {
                            "An unknown error has occurred, please try again later!"
                        }
                    }
                    else -> {
                        "An unknown error has occurred"
                    }
                }
                // Provide the errorMessages to the Composable/ViewModel and let the user know whats wrong
                onFail(errorMessage)
            }
    }

    fun login(email: String, password: String, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        Firebase.auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                //Give it the function of what to do
                onSuccess()
                println("we success")
            }
            .addOnFailureListener { exception ->
                var errorMessage = when(exception) {
                    is FirebaseAuthInvalidCredentialsException -> when(exception.errorCode) {
                        "auth/user-not-found" -> "User not found"
                        "ERROR_INVALID_EMAIL" -> "Invalid email"
                        "ERROR_WRONG_PASSWORD" -> "Wrong password"

                        else -> "Unknown error"
                    }
                    else -> "Unknown error"

                }
                onFailure(errorMessage)
                println("We didnt success")
                // Handle the errors

            }

    }

    // Store userInformation in the database

   private val usersRepository: UsersRepository = UsersRepository()

       fun createUserDB(
         fullName: String,
         phoneNumber: String,
         zipCode: String,
         birthDate: String,
         gender: String,
         onSuccess: () -> Unit,
         onFail: (String) -> Unit
     ) {
        // Get the Auth uID
        val userUID = Firebase.auth.currentUser?.uid

           // Create the user object that we want to send
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
            usersRepository.addUserToDatabase(
                user = userToAddToDatabase,
                userUID = userUID,
                onSuccess = onSuccess,
                onFail = onFail
            )
        } else {
            onFail("An unknown error has occurred, please try again later") // TODO
            // Handle errors? if the Authentication went wrong - but that should have been handled at a sooner state.
        }




    }
}