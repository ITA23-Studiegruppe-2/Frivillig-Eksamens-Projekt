package com.example.frivillig_eksamens_projekt.ui.OrganisationProfile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import android.util.Log

data class OrganisationProfile(
    val name: String = "",
    val email: String = "",
    val cvrNumber: String = ""
)

class OrganisationProfileViewModel : ViewModel() {
    private val auth = FirebaseAuth.getInstance()
    private val firestore = FirebaseFirestore.getInstance()

    private val _organisationProfile = MutableStateFlow<OrganisationProfile?>(null)
    val organisationProfile = _organisationProfile.asStateFlow()

    init {
        fetchOrganisationProfile()
    }

    private fun fetchOrganisationProfile() {
        val currentUser = auth.currentUser
        if (currentUser != null) {
            firestore.collection("Users").document(currentUser.uid).get()
                .addOnSuccessListener { document ->
                    if (document != null && document.exists()) {
                        val orgId = document.getString("orgId")
                        if (orgId != null) {
                            fetchOrganizationDetails(orgId)
                        } else {
                            Log.e("OrganisationProfileViewModel", "orgId is null")
                        }
                    }
                }
                .addOnFailureListener { exception ->
                    Log.e("OrganisationProfileViewModel", "Error fetching user profile", exception)
                }
        } else {
            Log.e("OrganisationProfileViewModel", "User is not authenticated")
        }
    }

    private fun fetchOrganizationDetails(orgId: String) {
        firestore.collection("Organizations").document(orgId).get()
            .addOnSuccessListener { document ->
                if (document != null && document.exists()) {
                    val name = document.getString("name") ?: "N/A"
                    val email = document.getString("email") ?: "N/A"
                    val cvrNumber = document.getString("cvrNumber") ?: "N/A"
                    _organisationProfile.value = OrganisationProfile(
                        name = name,
                        email = email,
                        cvrNumber = cvrNumber
                    )
                }
            }
            .addOnFailureListener { exception ->
                Log.e("OrganisationProfileViewModel", "Error fetching organisation details", exception)
            }
    }
}
