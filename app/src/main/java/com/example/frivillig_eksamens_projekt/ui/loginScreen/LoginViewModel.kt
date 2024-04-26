package com.example.frivillig_eksamens_projekt.ui.loginScreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.frivillig_eksamens_projekt.services.AccountService

class LoginViewModel: ViewModel() {
    var accountService: AccountService = AccountService()

    var rememberMe by mutableStateOf(false)
    var email by   mutableStateOf("")
    var password by  mutableStateOf("")


    fun login(email: String, password: String, onSuccessLogin: () -> Unit, onFailure: () -> Unit) {
        accountService.authenticate(email, password, onSuccessLogin, onFailure)
    }


}
