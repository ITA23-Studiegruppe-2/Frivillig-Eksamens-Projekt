package com.example.frivillig_eksamens_projekt.ui.OrgAllActivities.ListOfUsersAppliedToActivity

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.frivillig_eksamens_projekt.Models.Activity
import com.example.frivillig_eksamens_projekt.Models.User
import com.example.frivillig_eksamens_projekt.repositories.ActivitiesRepository
import com.example.frivillig_eksamens_projekt.repositories.UsersRepository
import com.example.frivillig_eksamens_projekt.ui.chatScreen.sendMessageScreen.organisation.OrgChatRepository
import kotlinx.coroutines.launch

class ListOfUsersAppliedViewmodel(
    activityId: String
): ViewModel() {

    var currentActivityId by mutableStateOf("")

    //Repositories
    val activitiesRepository: ActivitiesRepository = ActivitiesRepository()
    val usersRepository: UsersRepository = UsersRepository()
    val orgChatRepository: OrgChatRepository = OrgChatRepository()


    // List
    var listOFAllUsersApplied: MutableList<User> by mutableStateOf(mutableListOf())

    var listOfUsersApproved: MutableList<String> by mutableStateOf(mutableListOf())


    var currentActivityData: Activity = Activity()


    init {
        currentActivityId = activityId
        getListOfUsers()
        getActivityData()
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
                    // The user isn't inside of the first list - We haven't added the data yet.
                    usersRepository.addActivityIdToUserSubCollection(activityId = currentActivityId, userId = userId)

                    // Send the notification
                    usersRepository.sendNotificationToUser(
                        userUId = userId,
                        title = "Tillykke du er blevet tildelt en vagt!",
                        message = "Din vagt som ${currentActivityData.title} hos ${currentActivityData.organization} er blevet godkendt. Du kan læse dine vagtdetaljer under kommende vagter"
                    )
                }
                // If the user already is in the list -> Do nothing
            }

            listOfUsersApproved.forEach { userId ->
                if (!(currentListOfUsersApproved.contains(userId))) {
                    // The user isn't in the new list -> Remove the activityId
                    usersRepository.removeActivityIdFromUserSubCollection(activityId = currentActivityId, userId = userId)
                }
                // The user is in the new list -> let the first forEach handle it.
            }

        }
    }

    fun getActivityData() {
        viewModelScope.launch {
            currentActivityData = activitiesRepository.getActivityById(activityId = currentActivityId)!!
        }

    }


    // Designed to create or update a chat room for users
    // who have been approved for a specific activity
    fun chatRoomForApprovedUsers(
        activityId: String
    ) {
        viewModelScope.launch {
            val approvedUsersIds = listOfUsersApproved
            val orgId = orgChatRepository.fetchOrganizationsId(activityId)
            Log.d("chatRoomForApprovedUsers", "Approved users: $approvedUsersIds, OrgId: $orgId")
            if (approvedUsersIds.isNotEmpty()) {
                val result = orgChatRepository.createOrUpdateChatroomWithUsers(
                    activityId = activityId,
                    userIds = approvedUsersIds,
                    orgId = orgId,
                    timestamp = System.currentTimeMillis()
                )
                Log.d("chatRoomForApprovedUsers", "Chat room creation result: $result")
                if (result) {
                    sendSystemMessage(activityId, orgId)
                }
            } else {
                Log.d("chatRoomForApprovedUsers", "No approved users found.")
            }
            }
        }

    // Designed to send an initial system message to a chat room
    // associated with a specific activity and organization
    fun sendSystemMessage(activityId: String, orgId: String) {
        viewModelScope.launch {
            try {
                val senderId = orgId
                val senderName = "System"
                val messageText = "Velkommen til allesammen! - Mere information kommer snarest muligt"
                val timestamp = System.currentTimeMillis()

                val result = orgChatRepository.sendGroupMessage(
                    activityId = activityId,
                    messageText = messageText,
                    senderId = senderId,
                    senderName = senderName,
                    orgId = orgId,
                    timestamp = timestamp
                )
                if (result) {
                    Log.d("sendInitialMessage", "Initial message sent successfully.")
                } else {
                    Log.d("sendInitialMessage", "Failed to send initial message.")
                }
            } catch (e: Exception) {
                Log.e("sendInitialMessage", "Error sending initial message: ${e.message}")
            }
        }
    }
    }