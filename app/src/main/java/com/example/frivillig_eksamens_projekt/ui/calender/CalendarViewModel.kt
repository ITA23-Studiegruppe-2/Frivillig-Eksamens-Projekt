package com.example.frivillig_eksamens_projekt.ui.calender

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import java.util.Calendar

class CalendarViewModel : ViewModel() { // Variabler til at holde styr på det nuværende år, måned og dag
    val currentYear: Int
    val currentMonth: Int
    val currentDay: Int
    var selectedDate by mutableStateOf("Fødselsdag")

    init {
        val calendar = Calendar.getInstance() // Opret en instans af kalenderen, som vi kan bruge til at arbejde med datoer og tidspunkter

        // Hent det nuværende år, måned og dag fra kalenderen
        currentYear = calendar.get(Calendar.YEAR)
        currentMonth = calendar.get(Calendar.MONTH)
        currentDay = calendar.get(Calendar.DAY_OF_MONTH)
    }
}
