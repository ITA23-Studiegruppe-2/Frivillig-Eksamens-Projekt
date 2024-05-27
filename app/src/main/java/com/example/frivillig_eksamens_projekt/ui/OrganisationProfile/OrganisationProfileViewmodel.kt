package com.example.frivillig_eksamens_projekt.ui.OrganisationProfile

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.frivillig_eksamens_projekt.Models.Organization
import com.example.frivillig_eksamens_projekt.repositories.OrganisationRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class OrganisationProfileViewModel : ViewModel() {
    var name: String by mutableStateOf("")
    var cvrNumber: String by mutableStateOf("")
    var email: String by mutableStateOf("")

    private val organisationRepository: OrganisationRepository = OrganisationRepository()

    init {
        fetchCurrentOrgData()
    }

    private fun fetchCurrentOrgData() {
        val orgID = Firebase.auth.currentUser?.uid
        viewModelScope.launch {
            try {
                val currentOrganisationInformation = orgID?.let { orgID ->
                    organisationRepository.fetchCurrentOrgData(orgID)
                }
                currentOrganisationInformation?.let {
                    name = currentOrganisationInformation.name
                    cvrNumber = currentOrganisationInformation.cvrNumber
                    email = currentOrganisationInformation.email
                } ?: run {
                    Log.e("OrganisationProfileVM", "No organisation data found")
                }
            } catch (e: Exception) {
                Log.e("OrganisationProfileVM", "Error fetching organisation data", e)
            }
        }
    }

}

