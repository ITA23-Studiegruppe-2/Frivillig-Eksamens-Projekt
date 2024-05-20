package com.example.frivillig_eksamens_projekt.ui.createShiftScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class TimeSelectViewmodel() : ViewModel() {

    var selectedTime by mutableStateOf("")

}