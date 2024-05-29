package com.example.frivillig_eksamens_projekt.ui.activityScreen

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.frivillig_eksamens_projekt.repositories.ActivitiesRepository
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

/**
 *
 * @author Rasmus Planteig
 * @author Christine Tofft
 *
 */

class RequestShiftViewModel: ViewModel() {
    val activitiesRepository: ActivitiesRepository = ActivitiesRepository()
    private val _isRequested = mutableStateOf(false)
    val isRequested: State<Boolean>  get() = _isRequested

    // Update functions to toggle the checkbox states
    fun setRequested (checked: Boolean) {
        _isRequested.value = checked
    }

    var isExpanded by mutableStateOf(false)


    fun applyForActivity(currentActivityID: String) {
        Firebase.auth.uid?.let {
            activitiesRepository.applyForActivity(
                appliedState = _isRequested.value,
                currentActivityID = currentActivityID,
                userID = it
            )
        }
    }
}