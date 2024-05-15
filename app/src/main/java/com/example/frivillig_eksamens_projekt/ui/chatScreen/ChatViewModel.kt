package com.example.frivillig_eksamens_projekt.ui.chatScreen

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.frivillig_eksamens_projekt.Models.Organization
import com.example.frivillig_eksamens_projekt.repositories.ChatRepository
import kotlinx.coroutines.launch

/*
Firbase.auth.UID

 */

// Håndterer logikken bag visningen af beskeder og søgning i chatten.
class ChatViewModel : ViewModel() {
    var backgroundColor by mutableStateOf(Color(0xFFC8D5B9))
    val chatRepository = ChatRepository()


    // Tilstand for beskeder
    val messages: State<List<com.example.frivillig_eksamens_projekt.DTO.Message>> =
        mutableStateOf(emptyList())


    // FIND ORGANISATION
    init {
        getOrganizations()
    }

    // list of organisations
    var listOfOrganization: MutableList<Organization> by mutableStateOf(mutableStateListOf())

    fun getOrganizations() {
        viewModelScope.launch {
            listOfOrganization = chatRepository.getOrganizations()
        }
    }

    // Search bar
    var searchBar by mutableStateOf("")

    fun searchOrganisationByName() {
        viewModelScope.launch {
            val newListOfOrganization: MutableList<Organization> =
                chatRepository.searchOrganizations(searchBar)
            println(newListOfOrganization)
            // Update the list with the search results instead of replacing it
            if (newListOfOrganization.isNotEmpty()) {
                listOfOrganization.clear()
                listOfOrganization.addAll(newListOfOrganization)
                println("Success")
            } else {
                // If no results found, clear the list
                listOfOrganization.clear()
                println("No success")
            }
        }
    }
    }
    // SKRIVE BESKEDER

/*
    // Funktion til at sende en besked
    suspend fun sendMessage(userId: String, message: String, orgId: String) {
        val newMessage = Message(message = message, userUID = userId, orgUID = orgId)
        chatRepository.sendMessage(userId, newMessage)
    }
}

 */
