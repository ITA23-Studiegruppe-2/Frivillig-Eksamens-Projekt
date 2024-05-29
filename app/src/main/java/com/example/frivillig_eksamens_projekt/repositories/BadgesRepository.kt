package com.example.frivillig_eksamens_projekt.repositories

import androidx.compose.ui.graphics.painter.Painter
import com.example.frivillig_eksamens_projekt.Models.Badge
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await

/**
 *
 * @author Rasmus Planteig
 * @author Christine Tofft
 *
 */

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

    suspend fun getBadgesForSpecificUser(userUId: String): MutableList<Badge> =
        db.collection("Users")
            .document(userUId)
            .collection("Badges")
            .get()
            .await()
            .toObjects(Badge::class.java)


    fun addBadgeToUser(badgeToAdd: Badge, badgeId: String) {
        val currentUserUId = currentUser?.uid
        if (currentUserUId != null) {
            db.collection("Users")
                .document(currentUserUId)
                .collection("Badges")
                .document(badgeId)
                .set(badgeToAdd)
        }
    }
}
