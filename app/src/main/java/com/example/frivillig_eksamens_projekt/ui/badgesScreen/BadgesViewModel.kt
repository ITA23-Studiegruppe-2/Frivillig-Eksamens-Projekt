package com.example.frivillig_eksamens_projekt.ui.badgesScreen

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.frivillig_eksamens_projekt.Models.Badge
import com.example.frivillig_eksamens_projekt.repositories.BadgesRepository
import kotlinx.coroutines.launch

class BadgesViewModel: ViewModel() {

    private val badgesRepository = BadgesRepository()
    private val _badges = MutableLiveData<List<Badge>>()
    val badges: LiveData<List<Badge>> = _badges

    var showDialog by mutableStateOf(false)
    var selectedBadge by mutableStateOf<Badge?>(null)


    init {
        loadBadges()
    }

    private fun loadBadges() = viewModelScope.launch {
        try {
            val listOfAllBadges = badgesRepository.getAllBadges()
            val listOfUserBadges = badgesRepository.getUserBadges()?.map { it.name }?.toSet()

            val tempList = listOfAllBadges.map { badge: Badge ->
                badge.copy(path = if (listOfUserBadges?.contains(badge.name) == true) badge.path else badge.pathLocked)
            }
            _badges.postValue(tempList)
        } catch (e: Exception) {
            Log.e("BadgesViewModel", "Error loading badges", e)
        }
    }
}



