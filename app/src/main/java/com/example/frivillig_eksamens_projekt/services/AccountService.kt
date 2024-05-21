package com.example.frivillig_eksamens_projekt.services

import com.example.frivillig_eksamens_projekt.Models.User
import com.example.frivillig_eksamens_projekt.repositories.OrganisationRepository
import com.example.frivillig_eksamens_projekt.repositories.UsersRepository
import com.google.firebase.Firebase
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.auth.auth

class AccountService {
     fun registerUserAuth(email: String, password: String, onSuccess: () -> Unit, onFail: (String) -> Unit) {
        Firebase.auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                // Make the User a user-class in the Auth Storage - Used to identify between orgs and users when loggin in
                val currentUser = Firebase.auth.currentUser

                val profileUpdate = UserProfileChangeRequest.Builder()
                    .setDisplayName("user")
                    .build()

                currentUser?.updateProfile(profileUpdate)


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
                val errorMessage = when (exception) {
                    is FirebaseAuthException -> when(exception.errorCode) {
                        "ERROR_EMAIL_ALREADY_IN_USE" -> "Denne email er allerede i brug"
                        "ERROR_WEAK_PASSWORD" -> "Brug venligst en stærkere adgangkode! Den skal mindst være 8 karaktere lang, indehold minimun et tal og et stort bogstav"
                        else -> "Ukendt fejl, prøv venligst igen senere!"
                    }
                    is FirebaseNetworkException -> "Netværksfejl, kontroller venligst om du har internet adgang"
                    else -> "Ukendt fejl, prøv venligst igen senere!"

                }
                // Provide the errorMessages to the Composable/ViewModel and let the user know whats wrong
                onFail(errorMessage)
            }
    }

    fun login(email: String, password: String, onUserSuccess: () -> Unit, onFailure: (String) -> Unit, onOrgSuccess: () -> Unit) {
        Firebase.auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                // Find out what type of account it is
                val currentUser = Firebase.auth.currentUser

                // Get the user data
                if(currentUser?.displayName == "user") {
                    // The account is a user - send them to user homeScreen
                    onUserSuccess()
                } else {
                    // The account is a org - send them to org homeScreen.
                    onOrgSuccess()
                }


            }
            .addOnFailureListener { exception ->

                // Get the errorCode and transform it to a readable text for the users.
                val errorMessage = when(exception) {
                    is FirebaseAuthInvalidCredentialsException -> when(exception.errorCode) {
                        "ERROR_INVALID_EMAIL" -> "Indtast venligst en gyldig e-mailadresse."
                        "ERROR_INVALID_CREDENTIAL" -> "Forkert adgangskode og/eller e-mail adresse"

                        else -> "Ukendt fejl, prøv venligst igen senere!"
                    }
                    is FirebaseNetworkException -> "Netværksfejl, kontroller venligst om du har internet adgang"
                    else -> "Ukendt fejl, prøv venligst igen senere!"

                }
                onFailure(errorMessage)
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

    /* ---------------------------ORGANISATION PART -------------------------*/

    // MAYBE MAKE ERROR HANDLING A PRIVATE FUNCTION
    private val organisationRepository: OrganisationRepository = OrganisationRepository()

    fun registerOrganisationAuth(email: String, password: String, onSuccess: () -> Unit, onFail: (String) -> Unit, cvrNumber: String, name: String) {
        Firebase.auth.createUserWithEmailAndPassword(email,password)
            .addOnSuccessListener {

                // Update the organisations displayName aka what type of account it is
                val currentUser = Firebase.auth.currentUser
                val profileUpdate = UserProfileChangeRequest.Builder()
                    .setDisplayName("org")
                    .build()
                currentUser?.updateProfile(profileUpdate)

                // If we get good request back
                // Handle the addition to the database here.
                print(it.user?.uid)
                    it.user?.let { it ->
                        organisationRepository.addOrgToDatabase(
                            orgUID = it.uid,
                            onSuccess = onSuccess,
                            onFail = onFail,
                            cvrNumber = cvrNumber,
                            name = name,
                            email = email
                        )

                    }
            }
            .addOnFailureListener {
                // Handle failure for AUTH TODO
                onFail("Failed")

            }
    }
}