package com.example.frivillig_eksamens_projekt.repositories

import com.example.frivillig_eksamens_projekt.Models.Organization
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class OrganisationRepository {
    private val db = Firebase.firestore



    fun addOrgToDatabase(orgUID: String, onSuccess: () -> Unit, onFail: (String) -> Unit, cvrNumber: String, name: String, email: String) {
        // Create the return object
        // Maybe remove orgID - ? It isn't sent up right now TODO
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
                // Handle Success TODO
                onSuccess()
            }
            .addOnFailureListener {
                // Handle error TODO
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