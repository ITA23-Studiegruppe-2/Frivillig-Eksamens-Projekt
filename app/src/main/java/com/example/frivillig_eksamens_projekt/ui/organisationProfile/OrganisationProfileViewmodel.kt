package com.example.frivillig_eksamens_projekt.ui.organisationProfile

// Import necessary Android and Kotlin libraries
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.frivillig_eksamens_projekt.repositories.OrganisationRepository
import kotlinx.coroutines.launch
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

// ViewModel class for the organisation profile screen
class OrganisationProfileViewModel : ViewModel() {
    // Mutable state properties for organisation's name, CVR number, and email
    var name: String by mutableStateOf("")
    var cvrNumber: String by mutableStateOf("")
    var email: String by mutableStateOf("")

    // Instance of OrganisationRepository to handle data fetching
    private val organisationRepository: OrganisationRepository = OrganisationRepository()

    // Initialization block to fetch current organisation data when ViewModel is created
    init {
        fetchCurrentOrgData()
    }

    // Function to fetch current organisation data from the repository
    private fun fetchCurrentOrgData() {
        // Get the organisation ID from the currently authenticated Firebase user
        val orgID = Firebase.auth.currentUser?.uid

        // Launch a coroutine in the ViewModel's scope to fetch data asynchronously
        viewModelScope.launch {
            try {
                // Fetch current organisation data
                val currentOrganisationInformation = orgID?.let { orgID ->
                    organisationRepository.fetchCurrentOrgData(orgID)
                }

                // If data is fetched, update the properties
                currentOrganisationInformation?.let {
                    name = currentOrganisationInformation.name
                    cvrNumber = currentOrganisationInformation.cvrNumber
                    email = currentOrganisationInformation.email
                } ?: run {
                    // Log an error if no organisation data is found
                    Log.e("OrganisationProfileVM", "No organisation data found")
                }
            } catch (e: Exception) {
                // Log any exception that occurs during data fetching
                Log.e("OrganisationProfileVM", "Error fetching organisation data", e)
            }
        }
    }
}
