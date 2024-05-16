package com.example.frivillig_eksamens_projekt.ui.OrganisationProfile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class OrganisationProfile(
    val name: String = "",
    val email: String = "",
    val photoUrl: String = "",
    val providerId: String = "",
    val zipCode: String = "",
    val cvr: String = ""
)

class OrganisationProfileViewModel : ViewModel() {
    private val auth = FirebaseAuth.getInstance()
    private val firestore = FirebaseFirestore.getInstance()

    private val _organisationProfile = MutableStateFlow<OrganisationProfile?>(null)
    val organisationProfile = _organisationProfile.asStateFlow()

    init {
        viewModelScope.launch {
            val currentUser = auth.currentUser
            currentUser?.let {
                firestore.collection("organizations").document(it.uid).get().addOnSuccessListener { document ->
                    if (document != null) {
                        val name = document.getString("name") ?: "N/A"
                        val email = document.getString("email") ?: "N/A"
                        val photoUrl = document.getString("photoUrl") ?: "N/A"
                        val providerId = document.getString("providerId") ?: "N/A"
                        val zipCode = document.getString("zipCode") ?: "N/A"
                        val cvr = document.getString("cvr") ?: "N/A"
                        _organisationProfile.value = OrganisationProfile(
                            name = name,
                            email = email,
                            photoUrl = photoUrl,
                            providerId = providerId,
                            zipCode = zipCode,
                            cvr = cvr
                        )
                    }
                }
            }
        }
    }
}
