package com.example.frivillig_eksamens_projekt.ui.activityScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color

import com.example.frivillig_eksamens_projekt.repositories.ActivitiesRepository


import com.example.frivillig_eksamens_projekt.DTO.Activity

import kotlinx.coroutines.runBlocking

class ActivityScreenViewModel {
    var backgroundColor by mutableStateOf(Color(0xFFC8D5B9))

    val activitesRepository = ActivitiesRepository()

    // list of shifts
    var listOfActivities: MutableList<Activity> by mutableStateOf(mutableStateListOf())
    init {
        getActivities()
    }

     fun getActivities() {
         runBlocking {
             listOfActivities = activitesRepository.getActivities()
         }
    }

    // Search bar






}

