package com.example.frivillig_eksamens_projekt.ui.registerScreen.registerOrg

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import com.example.frivillig_eksamens_projekt.services.AccountService

class CreateOrgViewModel {

    // Account services - Firebase
    val accountService = AccountService()

    var backgroundColor by mutableStateOf(Color(0xFFC8D5B9))
        private set

    //variables
    var orgName by mutableStateOf("")
    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var cvrNumber by mutableStateOf("")

    fun registerOrgAuthAndDatabase(onSuccess: () -> Unit, onFail: (String) -> Unit) {
        accountService.registerOrganisationAuth(
            email = email,
            password = password,
            onSuccess = onSuccess,
            onFail = onFail,
            cvrNumber = cvrNumber,
            name = orgName
        )
    }
}