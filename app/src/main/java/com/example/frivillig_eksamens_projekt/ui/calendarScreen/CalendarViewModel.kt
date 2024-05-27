package com.example.frivillig_eksamens_projekt.ui.calendarScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.frivillig_eksamens_projekt.Models.Activity
import com.example.frivillig_eksamens_projekt.repositories.ActivitiesRepository
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class CalendarViewModel(): ViewModel() {
    private val activitiesRepository = ActivitiesRepository()
    var userActivities: MutableList<Activity> by mutableStateOf(mutableStateListOf())

    init {
        getUserActivities()

    }

    private fun getUserActivities() {
        val currentUser = Firebase.auth.currentUser?.uid
        if (currentUser != null) {
            viewModelScope.launch {
                userActivities = activitiesRepository.getActivitiesForUser(
                    activitiesRepository.getActivities(),
                    currentUser
                )
                // Only get activities the user has applied(TO CHANGE) for
            }
        }
    }
    fun getActivityForDate(date: LocalDate): Activity? {
        val targetDate = date.format(DateTimeFormatter.ofPattern("dd/M/yyyy"))
        return userActivities.find { it.date == targetDate }
    }
}