package com.example.frivillig_eksamens_projekt.ui.chatScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.frivillig_eksamens_projekt.Models.Organization
import com.example.frivillig_eksamens_projekt.repositories.ChatRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


//----- Chat ----/

class OrganizationViewModel : ViewModel() {
    var backgroundColor by mutableStateOf(Color(0xFFC8D5B9))
    private val chatRepository = ChatRepository()


    var     listOfOrganization by mutableStateOf(mutableStateListOf<Organization>())
    var searchBar by mutableStateOf("")

    private var searchJob: Job? = null

    init {
        getOrganizations()
    }

    fun getOrganizations() {
        viewModelScope.launch {
            listOfOrganization.clear()
            listOfOrganization.addAll(chatRepository.getOrganizations())
        }
    }


    fun searchOrganisationByName() {
        viewModelScope.launch {
            val newListOfOrganization = chatRepository.searchOrganizations(searchBar)
            if (newListOfOrganization.isNotEmpty()) {

                listOfOrganization.clear()
                listOfOrganization.addAll(newListOfOrganization)
                println("Success: ${newListOfOrganization.size} organizations found.")
            } else {
                listOfOrganization.clear()
                println("No success: No organizations found.")
            }
        }
    }




    // Function to delay the search for 300ms

    fun searchOrganisationByNameDebounced() {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(300)
            searchOrganisationByName()
        }
    }
}
