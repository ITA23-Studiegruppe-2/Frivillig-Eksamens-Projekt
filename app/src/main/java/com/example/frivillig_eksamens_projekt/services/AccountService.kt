package com.example.frivillig_eksamens_projekt.services

import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.auth

class AccountService {
    fun authenticate(email: String, password: String, onSuccess: () -> Unit, onFail: () -> Unit) {
        Firebase.auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener { authResult ->
                // Get the Auth uID
                val userUID = authResult.user?.uid

                //Give it the function of what to do
                onSuccess()
                createUserDB(userUID)
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
    private fun createUserDB(uID: String?) {


    }
}