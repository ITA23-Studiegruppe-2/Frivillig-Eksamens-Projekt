package com.example.frivillig_eksamens_projekt.ui.createShiftScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class CreateShiftViewModel: ViewModel() {

    // Variables
    var title by mutableStateOf("")
    var hours by mutableStateOf("")
    var date by mutableStateOf("")
    var email by mutableStateOf("")
    var description by mutableStateOf("")
    var amount by mutableStateOf("")

}