package com.example.frivillig_eksamens_projekt.ui.OrgAllActivities.ListOfUsersAppliedToActivity

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.frivillig_eksamens_projekt.repositories.ActivitiesRepository

class UserInformationViewmodel(): ViewModel() {


    var checked by mutableStateOf(false)

    val activitiesRepository: ActivitiesRepository = ActivitiesRepository()

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
}