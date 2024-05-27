package com.example.frivillig_eksamens_projekt.ui.OrgAllActivities.ListOfUsersAppliedToActivity

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.frivillig_eksamens_projekt.Models.Badge
import com.example.frivillig_eksamens_projekt.repositories.ActivitiesRepository
import com.example.frivillig_eksamens_projekt.repositories.BadgesRepository
import kotlinx.coroutines.launch

class UserInformationViewmodel(): ViewModel() {


    var checked by mutableStateOf(false)
    var listOfUsersBadges: MutableList<Badge> by mutableStateOf(mutableListOf())

    //Repositories
    val activitiesRepository: ActivitiesRepository = ActivitiesRepository()
    val badgesRepository: BadgesRepository = BadgesRepository()


    fun approveOrRemoveUserFromApprovedList(activityId: String, userId: String) {
        //Check what we need to do based on the currentState of the checkbox

        if (checked) {
            // The user needs to be added
            activitiesRepository.approveUserToActivity(activityId = activityId, userID = userId)
        } else {
            // The user should be removed
            activitiesRepository.removeApprovedUser(activityId = activityId, userID = userId)


        }
    }


    fun getUserBadges( userId: String) {
        viewModelScope.launch {

            listOfUsersBadges = badgesRepository.getBadgesForSpecificUser(userId)
        }
    }
}