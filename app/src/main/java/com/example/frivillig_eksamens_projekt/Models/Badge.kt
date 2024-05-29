package com.example.frivillig_eksamens_projekt.Models

import com.google.firebase.firestore.DocumentId

/**
 *
 * @author Christine Tofft
 *
 */
data class Badge(

    @DocumentId var documentId: String? = null,

    var name: String = "",
    var path: String = "",
    var pathLocked: String = "",
    val title: String = "",
    val description: String = ""
)