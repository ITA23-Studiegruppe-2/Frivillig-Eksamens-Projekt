package com.example.frivillig_eksamens_projekt.Models

import com.google.firebase.firestore.DocumentId
/**
 *
 * @author Rasmus Planteig
 * @author Lucas Jacobsen
 * @author Anders Keller
 *
 */
data class Organization(
    @DocumentId var orgID: String? = null,
    val cvrNumber: String = "",
    val email: String = "",
    val name: String = "",
    val orgUID: String? = "",
)