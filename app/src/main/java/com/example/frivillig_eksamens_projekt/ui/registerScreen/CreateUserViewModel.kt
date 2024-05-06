package com.example.frivillig_eksamens_projekt.ui.registerScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.example.frivillig_eksamens_projekt.services.AccountService

class CreateUserViewModel: ViewModel() {

    // Account services - Firebase
    val accountService = AccountService()

    var backgroundColor by mutableStateOf(Color(0xFFC8D5B9))
        private set

    // variables
    var fullName by mutableStateOf("")
    var email by mutableStateOf("")
    var phoneNumber by mutableStateOf("")
    var zipCode by mutableStateOf("")
    var birthDate by mutableStateOf("")
    var password by mutableStateOf("")
    var gender by mutableStateOf("")
    var password2 by mutableStateOf("")

    fun registerUserAuthentication(onSuccess: () -> Unit, onFail: () -> Unit) {
        //Check to see if the fields are empty
        var listOfInputs: List<String> = listOf(fullName,email,password,password2)
        if (listOfInputs.all { value -> !value.isNullOrBlank() }) {
            //Check to see if the two passwords are the same
            if (password == password2) {
                accountService.registerUserAuth(
                    email = email,
                    password = password,
                    onSuccess = onSuccess,
                    onFail = onFail)

            }
            // The two passwords are not the same
            }
        // Some of the fields are null or blank
        }


    fun registerUserToDatabase(onSuccess: () -> Unit, onFail: () -> Unit) {
        accountService.createUserDB(
            fullName = fullName,
            phoneNumber = phoneNumber,
            zipCode = zipCode,
            birthDate = birthDate,
            gender = gender,
            onSuccess = onSuccess,
            onFail = onFail
        )
    }
}