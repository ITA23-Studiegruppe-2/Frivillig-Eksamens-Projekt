package com.example.frivillig_eksamens_projekt.repositories

import com.example.frivillig_eksamens_projekt.DTO.Activity
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await

class ActivitiesRepository() {
    val db = Firebase.firestore

    suspend fun getActivities(): MutableList<Activity> = db.collection("Activites")
        .get()
        .await()
        .toObjects(Activity::class.java)
}

