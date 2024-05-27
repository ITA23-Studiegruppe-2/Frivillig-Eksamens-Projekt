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
import java.util.Calendar

class ActivityScreenViewModel: ViewModel() {
    var backgroundColor by mutableStateOf(Color(0xFFC8D5B9))

    val activitesRepository = ActivitiesRepository()

    // list of shifts
    var listOfActivities: MutableList<Activity> by mutableStateOf(mutableStateListOf())
    var tempListOfActivities: MutableList<Activity> by mutableStateOf(mutableStateListOf())

    // Variables for the date picker filter dialog
    val currentYear: Int
    val currentMonth: Int
    val currentDay: Int
    var selectedDate by mutableStateOf("Vælg dato")

    var showFilterDialog by mutableStateOf(false)

    // Variables for the location dropdown
    var isExpanded by mutableStateOf(false)
    var selectedCity by mutableStateOf("Lokation")

    var listOfCities: MutableList<String> by mutableStateOf(mutableStateListOf())

    init {
        getActivities()
        val calendar = Calendar.getInstance() // Opret en instans af kalenderen, som vi kan bruge til at arbejde med datoer og tidspunkter
        // Hent det nuværende år, måned og dag fra kalenderen
        currentYear = calendar.get(Calendar.YEAR)
        currentMonth = calendar.get(Calendar.MONTH)
        currentDay = calendar.get(Calendar.DAY_OF_MONTH)


    }

    fun getActivities() {
         viewModelScope.launch {
             // Get all the activities
             tempListOfActivities = activitesRepository.getActivities()
             // Get the updated list that also includes usersApplied
             listOfActivities = activitesRepository.checkIfAlreadyApplied(tempListOfActivities)
             getListOfAllCities()
         }
    }

    // Search bar
    var searchBar by mutableStateOf("")

    fun searchForActivitiesByTitle() {
        viewModelScope.launch {
            val newListOfActivities: MutableList<Activity> = activitesRepository.searchActivityTitle(searchBar)
            // If the new list isn't empty - good request
            if (newListOfActivities.isNotEmpty()) {
                // get the list of userIDs
                listOfActivities = activitesRepository.checkIfAlreadyApplied(newListOfActivities)

            } else {
                // We didn't find anything in the database :)
            }
        }
    }

    fun filterActivitiesByDate(date: String){

        listOfActivities = listOfActivities.filter { activity -> activity.date == date }.toMutableList()

    }

    fun getListOfAllCities(){
        var tempListOfAllCities: MutableList<String> by mutableStateOf(mutableStateListOf())

        listOfActivities.forEach { city ->
            if (!(tempListOfAllCities.contains(city.city))) {
                tempListOfAllCities.add(city.city)
            }
        }
        listOfCities = tempListOfAllCities
    }

    fun filterActivitiesByLocation(city: String) {
        if (selectedCity != "Lokation") {
            listOfActivities = listOfActivities.filter { activity -> activity.city == city }.toMutableList()
        }
    }
}

