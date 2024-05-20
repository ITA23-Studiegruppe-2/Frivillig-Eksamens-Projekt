package com.example.frivillig_eksamens_projekt.repositories

import androidx.compose.ui.graphics.painter.Painter
import com.example.frivillig_eksamens_projekt.Models.Badge
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await

class BadgesRepository() {

    private val db = Firebase.firestore
    val currentUser = Firebase.auth.currentUser


    suspend fun getUserBadges(): MutableList<Badge>? =
        currentUser?.uid?.let {
            db.collection("Users")
                .document(it)
                .collection("Badges")
                .get()
                .await()
                .toObjects(Badge::class.java)
        }

    suspend fun getAllBadges(): MutableList<Badge> =
        db.collection("Badges")
            .get()
            .await()
            .toObjects(Badge::class.java)
}

    /*

    suspend fun showBadges(
        listOfUserBadges: MutableList<Badge>?,
        listOfAllBadges: MutableList<Badge>
    ): MutableList<String> {
        val tempList: MutableList<String> = mutableListOf()
        val userBadgeNames = listOfUserBadges?.map { it.name }?.toSet() ?: emptySet()

        for (badge in listOfAllBadges) {
            val badgePath =
                if (userBadgeNames.contains(badge.name)) badge.path else badge.pathLocked
            tempList.add(badgePath)
        }
        return tempList
    }
}
       /*

        if (listOfUserBadges != null) {
            val userBadgeNames = listOfUserBadges.map { it.name }.toSet()
            tempList.addAll(listOfAllBadges.map { badge ->
                if (userBadgeNames.contains(badge.name)) {
                    badge.path
                } else {
                    badge.pathLocked
                }
            })

            val userMappedBadges = listOfUserBadges.map { it.name }
            for (badge in listOfAllBadges) {
                if (userMappedBadges.contains(badge.name)) {
                    tempList.add(badge.path)
                } else {
                    tempList.add(badge.pathLocked)

                }

            }

        }
        return tempList
        /*
        var returnList: MutableList<Painter> = tempList.map {  }
        return tempList

         */