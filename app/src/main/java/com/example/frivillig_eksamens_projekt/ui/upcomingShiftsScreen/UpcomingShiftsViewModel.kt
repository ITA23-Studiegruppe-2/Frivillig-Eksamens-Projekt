package com.example.frivillig_eksamens_projekt.ui.upcomingShiftsScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.frivillig_eksamens_projekt.Models.Activity
import com.example.frivillig_eksamens_projekt.navigation.Screen
import com.example.frivillig_eksamens_projekt.repositories.ActivitiesRepository
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import kotlinx.coroutines.launch

/**
 *
 * @author Christine Tofft
 *
 *
 */
class UpcomingShiftsViewModel(): ViewModel() {

    private val activitiesRepository = ActivitiesRepository()
    var upcomingActivities: MutableList<Activity> by mutableStateOf(mutableStateListOf())

    init {
        getUserActivities()

    }



    private fun getUserActivities(){
        val currentUser = Firebase.auth.currentUser?.uid
        if (currentUser != null) {
            viewModelScope.launch {
                upcomingActivities = activitiesRepository.getActivitiesForUser(activitiesRepository.getActivities(), currentUser)
            }
        }

    }





}