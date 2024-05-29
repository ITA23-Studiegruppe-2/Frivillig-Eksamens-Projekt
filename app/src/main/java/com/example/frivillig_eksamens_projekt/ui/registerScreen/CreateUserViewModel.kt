package com.example.frivillig_eksamens_projekt.ui.registerScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.example.frivillig_eksamens_projekt.services.AccountService

/**
 *
 * @author Rasmus Planteig
 * @author Christine Tofft
 *
 */

class CreateUserViewModel : ViewModel() {

    // Account services - Firebase
    private val accountService = AccountService()

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

    // Error handling
    var errorMessage by mutableStateOf("")

    //Gender dropdown
    var isExpanded by mutableStateOf(false)
    var dropDownVisible by mutableStateOf(false)

    //Terms and conditions
    var isTermsAndConditionsChecked by mutableStateOf(false)

    fun registerUserAuthentication(onSuccess: () -> Unit, onFail: (String) -> Unit) {

        //Check to see if the fields are empty
        var listOfInputs: List<String> = listOf(fullName, email, password, password2)
        if (checkAllFieldsNotBlank(listOfInputs)) {
            //Check to see if the password is in a valid format
            if (passwordRegExCheck(password)) {
                //Check to see if the two passwords are the same
                if (password == password2) {
                    // All of the checks are valid - Allow the Authentication of user.
                    accountService.registerUserAuth(
                        email = email,
                        password = password,
                        onSuccess = onSuccess,
                        onFail = onFail
                    )
                } else {
                    // The two passwords are not the same -
                    errorMessage = "Passwords do not match"
                }

            } else {
                // The password isnt in valid format -
                errorMessage = "Please use a stronger password"
            }

        } else {
            // Some of the fields are null or blank -
            errorMessage = "Please fill out all fields"
        }
    }

    // RegEx to check if password is valid
    private fun passwordRegExCheck(password: String): Boolean {
        val regex = Regex("^(?=.*[0-9])(?=.*[A-Z]).{8,}\$")
        return regex.matches(password)
    }

    // Function to check if provided list doesnt contain empty fields
    private fun checkAllFieldsNotBlank(listOfInputs: List<String>): Boolean {
        return listOfInputs.all { value -> !value.isNullOrBlank() }
    }

    fun registerUserToDatabase(onSuccess: () -> Unit, onFail: (String) -> Unit) {
        //Check to see if the fields are empty - fullName has already been checked in the function before
        // TODO ADD GENDER
        val listOfInputs: List<String> = listOf(phoneNumber, zipCode, birthDate)
        if (checkAllFieldsNotBlank(listOfInputs)) {
            accountService.createUserDB(
                fullName = fullName,
                phoneNumber = phoneNumber,
                zipCode = zipCode,
                birthDate = birthDate,
                gender = gender,
                onSuccess = onSuccess,
                onFail = onFail
            )
        } else {
            errorMessage = "Please fill out all the fields"
        }

    }
}

