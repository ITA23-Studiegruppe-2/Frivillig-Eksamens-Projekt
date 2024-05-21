package com.example.frivillig_eksamens_projekt.repositories

import com.example.frivillig_eksamens_projekt.Models.Activity
import com.google.firebase.Firebase
import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await

class ActivitiesRepository() {
    val db = Firebase.firestore

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


    // TODO APPLY FOR ACTIVITY

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
            activity.listOfUsersApplied.add(currentActivityListOfUsers.toString())
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

    // TODO CREATE ACTIVITY

    fun createActivity(
        title: String,
        date: String,
        timeFrame: String,
        description: String,
        location: String,
        orgId: String,
        organisation: String,
        onSuccess: () -> Unit
    ) {
        // Create the activity object
        val activity = Activity(
            title = title,
            date = date,
            timeStamp = timeFrame,
            description = description,
            location = location,
            organisationId = orgId,
            organization = organisation
        )
        // Create the activity in the database
        db.collection("Activites")
            .add(activity)
            .addOnSuccessListener { onSuccess()}

    }
}
