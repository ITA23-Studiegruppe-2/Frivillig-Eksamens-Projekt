package com.example.frivillig_eksamens_projekt.ui.loginScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.frivillig_eksamens_projekt.services.AccountService

/**
 *
 * @author Rasmus Planteig
 *
 */
class LoginViewModel : ViewModel() {
    private var accountService: AccountService = AccountService()

    var email by mutableStateOf("")
    var password by mutableStateOf("")

    //Error Handling
    var errorMessage by mutableStateOf("")


    fun login(
        email: String,
        password: String,
        onUserSuccessLogin: () -> Unit,
        onFailure: (String) -> Unit,
        onOrgSuccessLogin: () -> Unit
    ) {

        //Check to see if any of the fields are empty
        val listOfInputs: List<String> = listOf(email, password)
        if (checkAllFieldsNotBlank(listOfInputs)) {
            // Allow the user to try and login
            accountService.login(
                email = email,
                password = password,
                onUserSuccess = onUserSuccessLogin,
                onFailure = onFailure,
                onOrgSuccess = onOrgSuccessLogin
            )
        } else {
            // Tell the user to fill all the fields
            errorMessage = "Vær sød at udfylde alle felter!"
        }


    }
    // Function to check if provided list doesnt contain empty fields
    private fun checkAllFieldsNotBlank(listOfInputs: List<String>): Boolean {
        return listOfInputs.all { value -> !value.isNullOrBlank() }
    }
}
