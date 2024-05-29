package com.example.frivillig_eksamens_projekt.ui.createShiftScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.frivillig_eksamens_projekt.Models.Organization
import com.example.frivillig_eksamens_projekt.repositories.ActivitiesRepository
import com.example.frivillig_eksamens_projekt.repositories.OrganisationRepository
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import kotlinx.coroutines.launch
import java.util.Calendar

/**
 *
 * @author Rasmus Planteig
 * @author Christine Tofft
 */
class CreateShiftViewModel(): ViewModel(
) {

    //Repository
    val activityRepository: ActivitiesRepository = ActivitiesRepository()
    val organisationRepository: OrganisationRepository = OrganisationRepository()


    // Variables
    var title by mutableStateOf("")
    var date by mutableStateOf("Vælg dato")
    var email by mutableStateOf("")
    var description by mutableStateOf("")
    var location by mutableStateOf("")
    var city by mutableStateOf("")

    var orgId: String?


    // Handle the datePicker
    val currentYear: Int
    val currentMonth: Int
    val currentDay: Int

    // Initialize the datePicker with the current date
    init {
        val calendar = Calendar.getInstance()
        currentYear = calendar.get(Calendar.YEAR)
        currentMonth = calendar.get(Calendar.MONTH)
        currentDay = calendar.get(Calendar.DAY_OF_MONTH)

        //Get the currentOrgData
        orgId = Firebase.auth.currentUser?.uid
        orgId?.let { getOrgData(it) }

    }

    // Handle time picker
    var isStartExpanded by mutableStateOf(false)
    var isEndExpanded by mutableStateOf(false)

    var startTime by mutableStateOf("Start tid")
    var endTime by mutableStateOf("Slut tid")

    var timeFrame: String = ""
        get() = "$startTime-$endTime"
    var currentOrgData: Organization = Organization()

    // Org data


    fun createActivity(onSuccess: () -> Unit) {
        if (orgId != null) {
            // Get the orgId and the org name.
            activityRepository.createActivity(
                title = title,
                date = date,
                timeFrame = timeFrame,
                description = description,
                location = location,
                orgId = orgId!!,
                organisation = currentOrgData.name,
                onSuccess = onSuccess,
                city = city
            )
        }

    }

    fun getOrgData(orgId: String) {
        viewModelScope.launch {
            currentOrgData = organisationRepository.fetchCurrentOrgData(orgId)!!
        }
    }
    // Handle hours by itself - function that takes the timestamps and calculates the hours

}