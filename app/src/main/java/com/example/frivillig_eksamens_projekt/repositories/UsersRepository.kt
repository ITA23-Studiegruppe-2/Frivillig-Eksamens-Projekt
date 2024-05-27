package com.example.frivillig_eksamens_projekt.repositories


import com.example.frivillig_eksamens_projekt.Models.News
import com.example.frivillig_eksamens_projekt.Models.Notification
import com.example.frivillig_eksamens_projekt.Models.User
import com.example.frivillig_eksamens_projekt.Models.UserId
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await


class UsersRepository() {
    private val db = Firebase.firestore

    val currentUser = Firebase.auth.currentUser?.uid

    suspend fun getUser(): User? = currentUser?.let {
        db.collection("Users")
            .document(it)
            .get()
            .await()
            .toObject(User::class.java)
    }



     fun addUserToDatabase(user: User, userUID: String, onSuccess: () -> Unit, onFail: (String) -> Unit) {
        db.collection("Users")
            .document(userUID)
            .set(user)
            .addOnSuccessListener {
                // Send the user to home page - Successful registration
                onSuccess()


            }
            .addOnFailureListener {
                // Handle what errors we get
                // maybe if one of the fields are missing
                onFail("There was an error trying to reach the database!")
                // We should handle the deletion of the user stored in Authentication - TODO
            }
    }

    fun addActivityIdToUserSubCollection(activityId: String, userId: String) {
        val activityIdObject = hashMapOf(
            "activityId" to activityId
        )
        db.collection("Users")
            .document(userId)
            .collection("MyActivities")
            .document(activityId)
            .set(activityIdObject)
    }

    fun removeActivityIdFromUserSubCollection(activityId: String, userId: String) {
        db.collection("Users")
            .document(userId)
            .collection("MyActivities")
            .document(activityId)
            .delete()
    }


    // Fetch random news to homescreen
    suspend fun fetchRandomNews(): News? {
        return try {
            val result = db.collection("News")
                .get()
                .await()
            val newsList =
                result.mapNotNull { doc -> // filters if a document doesn't have a newsText
                    doc.getString("newsText")?.let { News(it) }
                }
            // Picks a random news if newsList isn't empty
            if (newsList.isNotEmpty()) newsList.random() else null
        } catch (e: Exception) {
            null
        }
    }


    /* COULD BE ITS OWN REPOSITORY -- NOTIFICATION CENTER */

    fun sendNotificationToUser(userUId: String, title: String, message: String) {
        //Create the notification object
        val notification: Notification = Notification(
            title = title,
            message = message
        )

        db.collection("Users")
            .document(userUId)
            .collection("MyNotifications")
            .add(notification)
    }


    suspend fun retrieveNotificationsByUserUId(userUId: String): MutableList<Notification> =
        db.collection("Users")
            .document(userUId)
            .collection("MyNotifications")
            .get()
            .await()
            .toObjects(Notification::class.java)



    suspend fun deleteNotificationsForUser(userUId: String) {
        //Deletes every document inside of the collection using batch - Which can allow up to 500 at a time
        val batch = Firebase.firestore.batch()
        db.collection("Users")
            .document(userUId)
            .collection("MyNotifications")
            .get()
            .await()
            .forEach{document ->
                batch.delete(document.reference)
            }
        // Commit the batch - Aka delete from the collection
        batch.commit().await()
    }
}



