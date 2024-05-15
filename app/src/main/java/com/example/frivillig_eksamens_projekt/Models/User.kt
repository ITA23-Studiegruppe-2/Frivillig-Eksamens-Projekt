package com.example.frivillig_eksamens_projekt.Models

data class User(
    var fullName: String = "med dig",
    var phoneNumber: String = "",
    var zipCode: String = "",
    var birthDate: String = "",
    var gender: String = "",
    var userUID: String? = ""
)
