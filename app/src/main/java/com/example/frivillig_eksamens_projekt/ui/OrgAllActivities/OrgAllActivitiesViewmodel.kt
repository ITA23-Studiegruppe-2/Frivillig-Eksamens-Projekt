package com.example.frivillig_eksamens_projekt.ui.OrgAllActivities

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.frivillig_eksamens_projekt.Models.Activity
import com.example.frivillig_eksamens_projekt.repositories.ActivitiesRepository
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import kotlinx.coroutines.launch

/**
 *
 * @author Rasmus Planteig
 *
 */
class OrgAllActivitiesViewmodel: ViewModel() {

    val activitiesRepository: ActivitiesRepository = ActivitiesRepository()
    var listOfActivities: MutableList<Activity> by mutableStateOf(mutableListOf())

    val currentOrgUID = Firebase.auth.currentUser?.uid


    init {
        fetchActivities()
    }
    private fun fetchActivities() {
        if (currentOrgUID != null) {
            viewModelScope.launch {
                listOfActivities = activitiesRepository.addListOfAppliedToActivities(activitiesRepository.fetchActivityByOrgID(activitiesRepository.getActivities(),currentOrgUID))
            }
        }
    }

}