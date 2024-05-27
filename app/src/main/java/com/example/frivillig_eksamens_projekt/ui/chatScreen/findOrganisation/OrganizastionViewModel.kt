package com.example.frivillig_eksamens_projekt.ui.chatScreen.findOrganisation

/*
//----- Chat ----/

class OrganizationViewModel : ViewModel() {
    var backgroundColor by mutableStateOf(Color(0xFFC8D5B9))
    private val chatRepository = ChatRepository()


    var listOfOrganization by mutableStateOf(mutableStateListOf<Organization>())
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



    // Delays the search to get live searching
    fun updateSearchBar(value: String) {
        searchBar = value
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(300)  //
            searchOrganisationByName()
        }
    }
}

 */
