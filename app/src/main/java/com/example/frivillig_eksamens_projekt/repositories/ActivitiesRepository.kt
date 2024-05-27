package com.example.frivillig_eksamens_projekt.repositories

import com.example.frivillig_eksamens_projekt.Models.Activity
import com.example.frivillig_eksamens_projekt.Models.User
import com.example.frivillig_eksamens_projekt.Models.UserId
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await

class ActivitiesRepository() {
    private val db = Firebase.firestore


    suspend fun getActivities(): MutableList<Activity> =
        db.collection("Activites")
            .get()
            .await()
            .toObjects(Activity::class.java)

     suspend fun searchActivityTitle(title: String): MutableList<Activity> =
         db.collection("Activites")
             .whereEqualTo("title",title)
             .get()
             .await()
             .toObjects(Activity::class.java)

    suspend fun getActivityById(activityId: String): Activity? =
        db.collection("Activites")
            .document(activityId)
            .get()
            .await()
            .toObject(Activity::class.java)

    suspend fun getActivitiesForUser(listOfActivities: MutableList<Activity>, userUID: String): MutableList<Activity> {
        // Initialize a return list
        val listOfActivitiesWithUsers: MutableList<Activity> = mutableListOf()

        listOfActivities.forEach { activity ->
            val currentActivityList = activity.documentId?.let {
                db.collection("Activites")
                    .document(it)
                    .collection("usersApplied")
                    .get()
                    .await()
                    .documents
                    .map { userDocument -> userDocument.id }
            }

            if (currentActivityList != null) {
                activity.listOfUsersApplied = currentActivityList.toMutableList()
            }
            if (activity.listOfUsersApplied.contains(userUID)) {
                listOfActivitiesWithUsers.add(activity)
            }

        }
        println(listOfActivitiesWithUsers)
        return listOfActivitiesWithUsers
    }



    /* CHECK APPLICATION STATE */
    suspend fun checkIfAlreadyApplied(listOfActivities: List<Activity>): MutableList<Activity>  {
        // Initialize a return list
        val listOfActivitesWithUsers: MutableList<Activity> = mutableListOf()
        // iterate through each activity and find the documents in the activity's collections
        // Map it so we get the id for alle of the documents ( Users)
        listOfActivities.forEach { activity ->
            val currentActivityListOfUsers = activity.documentId?.let {
                db.collection("Activites")
                    .document(it)
                    .collection("usersApplied")
                    .get()
                    .await()
                    .documents
                    .map { userDocument -> userDocument.id }
            }
            // Append the list of userIDs to the return list
            if (currentActivityListOfUsers != null) {
                activity.listOfUsersApplied = currentActivityListOfUsers.toMutableList()
            }
            listOfActivitesWithUsers.add(activity)
        }
        return listOfActivitesWithUsers
    }

    fun applyForActivity(appliedState: Boolean, userID: String, currentActivityID: String) {
        // Check to see if the user already has applied
        if (!appliedState) {
            // The user has already applied
            // Remove the document with the userid in the userApplied collection
            db.collection("Activites")
                .document(currentActivityID)
                .collection("usersApplied")
                .document(userID)
                .delete()

        } else {
            // The user hasn't applied yet
            // Create the "object" with the userID Data
            val userIDObject = hashMapOf(
                "userUID" to userID
            )

            // Find the usersApplied collection and insert the userIDObject
            db.collection("Activites")
                .document(currentActivityID)
                .collection("usersApplied")
                .document(userID)
                .set(userIDObject)
        }
    }

    fun createActivity(
        title: String,
        date: String,
        timeFrame: String,
        description: String,
        location: String,
        orgId: String,
        organisation: String,
        onSuccess: () -> Unit,
        city: String
    ) {
        // Create the activity object
        val activity = Activity(
            title = title,
            date = date,
            timeStamp = timeFrame,
            description = description,
            location = location,
            organisationId = orgId,
            organization = organisation,
            city = city
        )
        // Create the activity in the database
        db.collection("Activites")
            .add(activity)
            .addOnSuccessListener { onSuccess()}
    }


   suspend fun fetchActivityByOrgID(listOfActivities: MutableList<Activity>, orgUID: String): MutableList<Activity> {
       return listOfActivities.filter { activity -> activity.organisationId == orgUID }.toMutableList()
   }

    suspend fun getUsersAppliedByActivityId(activityId: String): MutableList<User> {
        val listOfAllUserObjects: MutableList<User> = mutableListOf()

       val listOfALlUserId = db.collection("Activites")
            .document(activityId)
            .collection("usersApplied")
            .get()
            .await()
            .documents
            .map { userId -> userId.id }

        listOfALlUserId.forEach{activity ->

           val currentUser =  db.collection("Users")
                .document(activity)
                .get()
                .await()
                .toObject(User::class.java)

            if (currentUser != null) {
                listOfAllUserObjects.add(currentUser)
            }
        }
        return listOfAllUserObjects
    }

    suspend fun getListOfApprovedUserIdByActivityId(activityId: String): MutableList<String>{
        val listOfUserIds = db.collection("Activites")
            .document(activityId)
            .collection("userApproved")
            .get()
            .await()
            .documents
            .map { documentId -> documentId.id }
        return listOfUserIds.toMutableList()
    }


    // Handle org side of approving users
     fun approveUserToActivity(activityId: String, userID: String) {

        // Create the object to insert into the collection
         val user: UserId = UserId(documentId = userID, userUId = userID)
        db.collection("Activites")
            .document(activityId)
            .collection("userApproved")
            .document(userID)
            .set(user)
    }

    fun removeApprovedUser(activityId: String, userID: String) {
        db.collection("Activites")
            .document(activityId)
            .collection("userApproved")
            .document(userID)
            .delete()
    }


    suspend fun getAllActivitiesIdCompletedByUser(userUId: String): MutableList<String> =
            db.collection("Users")
                .document(userUId)
                .collection("MyActivities")
                .get()
                .await()
                .documents
                .map { documentId -> documentId.id }
                .toMutableList()

    suspend fun getAllActivityDataFromListOfId(listOfActivityIds: MutableList<String>): MutableList<Activity> {
        val listOfActivityObjects: MutableList<Activity> = mutableListOf()
        listOfActivityIds.forEach {activityId ->
            val currentActivityData = db.collection("Activites")
                .document(activityId)
                .get()
                .await()
                .toObject(Activity::class.java)

            if (currentActivityData != null) {
                listOfActivityObjects.add(currentActivityData)
            }
        }
        return listOfActivityObjects
    }

    suspend fun addListOfAppliedToActivities(listOfActivities: MutableList<Activity>): MutableList<Activity>{
        var listOfAllActivities: MutableList<Activity> = mutableListOf()

        listOfActivities.forEach { activity ->
            val listOfAllUserId = activity.documentId?.let {
                db.collection("Activites")
                    .document(it)
                    .collection("usersApplied")
                    .get()
                    .await()
                    .documents
                    .map { userId -> userId.id }
            }
            activity.listOfUsersApplied = listOfAllUserId as MutableList<String>
            listOfAllActivities.add(activity)



        }
        return listOfAllActivities

    }
}
