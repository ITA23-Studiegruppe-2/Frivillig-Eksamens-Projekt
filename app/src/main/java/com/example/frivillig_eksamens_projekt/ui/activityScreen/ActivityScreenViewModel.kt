package com.example.frivillig_eksamens_projekt.ui.activityScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel

import com.example.frivillig_eksamens_projekt.repositories.ActivitiesRepository
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

import com.example.frivillig_eksamens_projekt.Models.Activity

class ActivityScreenViewModel: ViewModel() {
    var backgroundColor by mutableStateOf(Color(0xFFC8D5B9))

    val activitesRepository = ActivitiesRepository()

    // list of shifts
    var listOfActivities: MutableList<Activity> by mutableStateOf(mutableStateListOf())
    var tempListOfActivities: MutableList<Activity> by mutableStateOf(mutableStateListOf())
    init {
        getActivities()
    }

    fun getActivities() {
         viewModelScope.launch {
             // Get all the activities
             tempListOfActivities = activitesRepository.getActivities()
             // Get the updated list that also includes usersApplied
             listOfActivities = activitesRepository.checkIfAlreadyApplied(tempListOfActivities)
         }
    }

    // Search bar
    var searchBar by mutableStateOf("")

    fun searchForActivitiesByTitle() {
        viewModelScope.launch {
            val newListOfActivities: MutableList<Activity> = activitesRepository.searchActivityTitle(searchBar)
            // If the new list isnt empty - good request
            if (newListOfActivities.isNotEmpty()) {
                // get the list of userIDs
                listOfActivities = activitesRepository.checkIfAlreadyApplied(newListOfActivities)

            } else {
                // We didnt find anything in the database :)

            }
        }
    }



}

