package com.example.frivillig_eksamens_projekt.ui.loginScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.frivillig_eksamens_projekt.services.AccountService

class LoginViewModel: ViewModel() {
    private var accountService: AccountService = AccountService()

    var rememberMe by mutableStateOf(false)
    var email by mutableStateOf("Planteig17@hotmail.com")
    var password by mutableStateOf("Planteig2667")


    fun login(email: String, password: String, onSuccessLogin: () -> Unit, onFailure: () -> Unit) {
        accountService.login(
            email = email,
            password = password,
            onSuccess = onSuccessLogin,
            onFailure = onFailure
        )
    }



}
