package com.example.frivillig_eksamens_projekt.ui.registerScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.frivillig_eksamens_projekt.services.AccountService
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

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

    fun registerUserToDatabase(onSuccess: () -> Unit, onFail: () -> Unit) {

        // Im not sure if it needs to be different functions
        runBlocking {
            accountService.registerUser(
                email = email,
                password = password,
                onSuccess = onSuccess,
                onFail = onFail
            )
            accountService.createUserDB(
                fullName = fullName,
                phoneNumber = phoneNumber,
                zipCode = zipCode,
                birthDate = birthDate,
                gender = gender,
                onSuccess = onSuccess
            )
        }
    }
}