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

    var errorMessage by mutableStateOf("")


    fun registerOrgAuthAndDatabase(onSuccess: () -> Unit, onFail: (String) -> Unit) {
        val listOfInputFieldsToCheck: List<String> = listOf(orgName,email,password,cvrNumber)
        if (checkAllFieldsNotBlank(listOfInputFieldsToCheck)) {
            accountService.registerOrganisationAuth(
                email = email,
                password = password,
                onSuccess = onSuccess,
                onFail = onFail,
                cvrNumber = cvrNumber,
                name = orgName
            )
        } else {
            errorMessage = "Udfyld venligst alle felterne!"
        }

    }

    // Function to check if provided list doesnt contain empty fields
    private fun checkAllFieldsNotBlank(listOfInputs: List<String>): Boolean {
        return listOfInputs.all { value -> !value.isNullOrBlank() }
    }
}