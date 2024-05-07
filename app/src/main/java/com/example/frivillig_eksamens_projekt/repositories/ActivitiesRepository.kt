package com.example.frivillig_eksamens_projekt.repositories

import com.example.frivillig_eksamens_projekt.DTO.Activity
import com.google.firebase.Firebase
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.firestore.toObjects
import kotlinx.coroutines.tasks.await

class ActivitiesRepository() {
    val db = Firebase.firestore

    suspend fun getActivities(): MutableList<Activity> = db.collection("Activites")
        .get()
        .await()
        .toObjects(Activity::class.java)

     suspend fun searchActivityTitle(title: String): MutableList<Activity> =
         db.collection("Activites")
             .whereEqualTo("title",title)
             .get()
             .await()
             .toObjects(Activity::class.java)

}
