package com.example.frivillig_eksamens_projekt.ui.homeScreen


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.frivillig_eksamens_projekt.repositories.OrganisationRepository
import com.google.firebase.auth.auth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

class OrgViewModel: ViewModel() {
    val organizationRepository: OrganisationRepository = OrganisationRepository()

    init {
        getOrganizationData()
    }


    var name by mutableStateOf("")

     fun getOrganizationData(){
        viewModelScope.launch {
            val currentOrgUid  = Firebase.auth.currentUser?.uid
            if (currentOrgUid != null) {
                val currentOrg  = organizationRepository.fetchCurrentOrgData(currentOrgUid)
                if (currentOrg != null) {
                    name = currentOrg.name
                }
            }
        }
    }

    fun getAccountType(): String? {
        return Firebase.auth.currentUser?.displayName
    }

}