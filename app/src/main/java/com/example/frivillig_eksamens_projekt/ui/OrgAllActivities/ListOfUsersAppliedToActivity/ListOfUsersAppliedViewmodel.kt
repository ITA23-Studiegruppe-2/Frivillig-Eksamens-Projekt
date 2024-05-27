package com.example.frivillig_eksamens_projekt.ui.OrgAllActivities.ListOfUsersAppliedToActivity

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.frivillig_eksamens_projekt.Models.User
import com.example.frivillig_eksamens_projekt.repositories.ActivitiesRepository
import com.example.frivillig_eksamens_projekt.repositories.UsersRepository
import kotlinx.coroutines.launch

class ListOfUsersAppliedViewmodel(
    activityId: String
): ViewModel() {

    var currentActivityId by mutableStateOf("")

    //Repositories
    val activitiesRepository: ActivitiesRepository = ActivitiesRepository()
    val usersRepository: UsersRepository = UsersRepository()


    // List
    var listOFAllUsersApplied: MutableList<User> by mutableStateOf(mutableListOf())

    var listOfUsersApproved: MutableList<String> by mutableStateOf(mutableListOf())

    init {
        currentActivityId = activityId
        getListOfUsers()


    }

    private fun getListOfUsers() {
        viewModelScope.launch {
            // Get the list of all users that have applied, and their information
            listOFAllUsersApplied = activitiesRepository.getUsersAppliedByActivityId(currentActivityId)

            //Handle their approved state
            listOfUsersApproved = activitiesRepository.getListOfApprovedUserIdByActivityId(currentActivityId)
        }
    }


    fun addOrRemoveActivityForUsers() {
        // Function that adds or removes the activityId from the subCollection of activities inside of the user document

        viewModelScope.launch {
            //Get the new list of users approved
            val currentListOfUsersApproved: MutableList<String> = activitiesRepository.getListOfApprovedUserIdByActivityId(currentActivityId)

            // Compare between the two list of approved Users - list 1 (Before any changes), list 2 (after changes)

            //Check if we need to add the activityId to the users subCollection
            currentListOfUsersApproved.forEach { userId ->
                if (!(listOfUsersApproved.contains(userId))) {
                    // The user isnt inside of the first list - We havent added the data yet.
                    usersRepository.addActivityIdToUserSubCollection(activityId = currentActivityId, userId = userId)
                }
                // If the user already is in the list -> Do nothing
            }

            listOfUsersApproved.forEach { userId ->
                if (!(currentListOfUsersApproved.contains(userId))) {
                    // The user isnt in the new list -> Remove the activityId
                    usersRepository.removeActivityIdFromUserSubCollection(activityId = currentActivityId, userId = userId)
                }
                // The user is in the new list -> let the first forEach handle it.
            }

        }
    }





}