package com.example.frivillig_eksamens_projekt.ui.homeScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.frivillig_eksamens_projekt.Models.User
import com.example.frivillig_eksamens_projekt.repositories.UsersRepository
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import kotlinx.coroutines.launch

class UserViewModel: ViewModel() {
    val usersRepository: UsersRepository = UsersRepository()

    init {
        viewModelScope.launch {
            getUserData()
        }

    }

    var name by mutableStateOf("")


    suspend fun getUserData(){
        val userData = usersRepository.getUser()
        if (userData != null) {
            name = userData.fullName
        }
    }
}