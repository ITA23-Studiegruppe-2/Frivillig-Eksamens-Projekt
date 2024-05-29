package com.example.frivillig_eksamens_projekt.repositories

import com.example.frivillig_eksamens_projekt.Models.Organization
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

/**
 *
 * @author Rasmus Planteig
 *
 */
class OrganisationRepository {
    private val db = Firebase.firestore

    fun addOrgToDatabase(orgUID: String, onSuccess: () -> Unit, onFail: (String) -> Unit, cvrNumber: String, name: String, email: String) {
        val currentOrg = Organization(
            orgID = orgUID,
            cvrNumber = cvrNumber,
            email = email,
            name = name,
            orgUID = orgUID
        )

        db.collection("Organizations")
            .document(orgUID)
            .set(currentOrg)
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener {
                onFail("Failed")
            }
    }

    suspend fun fetchCurrentOrgData(orgUID: String) =
        db.collection("Organizations")
            .document(orgUID)
            .get()
            .await()
            .toObject(Organization::class.java)

}
