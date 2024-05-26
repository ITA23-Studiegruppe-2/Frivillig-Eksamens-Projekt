package com.example.frivillig_eksamens_projekt.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.frivillig_eksamens_projekt.repositories.OrganisationRepository
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class OrganisationProfileViewModel : ViewModel() {

    var name by mutableStateOf("")
    var email by mutableStateOf("")
    var cvrNumber by mutableStateOf("")

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    private val organisationRepository: OrganisationRepository = OrganisationRepository()

    init {
        fetchCurrentOrgData()
    }

    fun fetchCurrentOrgData() {
        val userUID = auth.currentUser?.uid ?: return
        viewModelScope.launch {
            try {
                val currentOrganisationInformation = organisationRepository.fetchCurrentOrgData(userUID).firstOrNull()
                currentOrganisationInformation?.let {
                    name = it.name
                    cvrNumber = it.cvrNumber
                    email = it.email
                }
            } catch (e: Exception) {
                Log.e("OrganisationProfileViewModel", "Error fetching organization data", e)
            }
        }
    }
}
