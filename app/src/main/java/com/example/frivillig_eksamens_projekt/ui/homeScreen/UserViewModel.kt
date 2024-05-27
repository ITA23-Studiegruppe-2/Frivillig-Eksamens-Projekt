package com.example.frivillig_eksamens_projekt.ui.homeScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.frivillig_eksamens_projekt.Models.Activity
import com.example.frivillig_eksamens_projekt.Models.Badge
import com.example.frivillig_eksamens_projekt.Models.Notification
import com.example.frivillig_eksamens_projekt.Models.User
import com.example.frivillig_eksamens_projekt.Models.UserStats
import com.example.frivillig_eksamens_projekt.repositories.ActivitiesRepository
import com.example.frivillig_eksamens_projekt.repositories.BadgesRepository
import com.example.frivillig_eksamens_projekt.repositories.UsersRepository
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import kotlinx.coroutines.launch
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class UserViewModel: ViewModel() {

    //Repositories
    val usersRepository: UsersRepository = UsersRepository()
    val badgesRepository: BadgesRepository = BadgesRepository()
    val activitiesRepository: ActivitiesRepository = ActivitiesRepository()

    init {
        getUserData()
        getUserNotification()

    }

    var listOfNotifications: MutableList<Notification> = mutableListOf()

    var name by mutableStateOf("")
    var bellColor by mutableStateOf(Color.White)

    //Dialog
    var dialogShow by mutableStateOf(false)


     private fun getUserData(){
        viewModelScope.launch {
            val userData = usersRepository.getUser()
            if (userData != null) {
                name = userData.fullName
            }
        }
    }
    // Get the notifications if there is any
    private fun getUserNotification() {
        viewModelScope.launch {
            val currentUserUId: String? = Firebase.auth.currentUser?.uid
            if (currentUserUId != null) {
                listOfNotifications = usersRepository.retrieveNotificationsByUserUId(currentUserUId)
                if (listOfNotifications.isNotEmpty()) {
                    bellColor = Color.Yellow
                }
            }
        }
    }

    fun markNotificationsAsRead() {
        // Changes the bell, clears the list and updates the database
        bellColor = Color.White
        listOfNotifications = mutableListOf()

        val currentUserUId: String? = Firebase.auth.currentUser?.uid
        viewModelScope.launch {
            if (currentUserUId != null) {
                usersRepository.deleteNotificationsForUser(currentUserUId)
            }
        }

    }

    // Handle the addition of badges to users

    //Notes to function - Could also fetch the list of the users current badges,
    // and check if the badge we are trying to award is contained within (Don't award it)
    // The only thing that does it maybe reduce the amount of calls we give to firebase
    // It shouldn't have any technical advantage, as firebase should be able to tell that there already is a document with that id,
    // and therefore not create a new document (Badge)
    fun awardUsersWithBadgeCheck() {
        viewModelScope.launch {
            val currentUserUId: String? = Firebase.auth.currentUser?.uid
            if (currentUserUId != null) {
                //Get the list of all badges
                val listOfAllBadges: MutableList<Badge> = badgesRepository.getAllBadges()

                //Get the list of all activities for the user
                val listOfAllActivitiesCompletedByUser: MutableList<Activity> = activitiesRepository.getAllActivitiesCompletedByUser(currentUserUId)

                // Go through the list of all activities and calculate hours + the amount of activities (size of list)

                val currentUserStats: UserStats = formatStatsFromActivities(listOfAllActivitiesCompletedByUser)



                // Have functions that takes a value as parameter, and uses that to award (If the threshold is crossed) a badge
            }
        }
    }


    private fun formatStatsFromActivities(listOfActivities: MutableList<Activity>): UserStats {
        // Initialize the values that we want to
        var hours: Int = 0
        var amountOfActivities: Int = listOfActivities.size

        listOfActivities.forEach{activity ->
            // Get the formatted hours
            hours += calculateHours(
                startTime = activity.timeStamp.substring(0,5),
                endTime = activity.timeStamp.substring(6,11)
            )
        }

        //Create the "object" that stores the users stats
        val userStats = UserStats(
            hours = hours,
            amountOfActivities = amountOfActivities
        )
        return userStats
    }

    private fun calculateHours(startTime: String, endTime: String): Int {
        val start = LocalTime.parse(startTime, DateTimeFormatter.ofPattern("HH:mm"))
        val end = LocalTime.parse(endTime, DateTimeFormatter.ofPattern("HH:mm"))

        val hours = end.hour - start.hour
        val minutes = end.minute - start.minute

        //Hours in decimal
        return  hours + (minutes/60)

    }

    // List Of award badge functions.
    // 5 20 30 50 75 100 150
    fun awardHoursBadge(currentUserStats: UserStats) {
    }


}