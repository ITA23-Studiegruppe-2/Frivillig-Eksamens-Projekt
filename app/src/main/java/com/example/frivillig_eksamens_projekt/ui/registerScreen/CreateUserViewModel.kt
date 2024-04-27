package com.example.frivillig_eksamens_projekt.ui.registerScreen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import com.example.frivillig_eksamens_projekt.services.AccountService

class CreateUserViewModel {

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

    fun registerUserToDatabase(onSuccess: () -> Unit, onFail: () -> Unit) {
        accountService.authenticate(
            email = email,
            password = password,
            onSuccess = onSuccess,
            onFail = onFail
        )
    }

}