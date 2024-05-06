package com.example.frivillig_eksamens_projekt.DTO

data class User(
    var fullName: String,
    var phoneNumber: String,
    var zipCode: String,
    var birthDate: String,
    var gender: String,
    var userUID: String?
)
