package com.example.frivillig_eksamens_projekt.ui.homeScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.frivillig_eksamens_projekt.Models.News
import com.example.frivillig_eksamens_projekt.repositories.UsersRepository
import kotlinx.coroutines.launch


class UserViewModel: ViewModel() {
    val usersRepository: UsersRepository = UsersRepository()

    // Using mutableStateOf to hold the news data
    var news by mutableStateOf<News?>(null)

    var name by mutableStateOf("")


    init {
        viewModelScope.launch {
            getUserData()
            fetchRandomNews()
        }

    }




    suspend fun getUserData() {
        val userData = usersRepository.getUser()
        if (userData != null) {
            name = userData.fullName
        }
    }



    suspend fun fetchRandomNews() {
        val fetchedNews = usersRepository.fetchRandomNews()
        news = fetchedNews
        }
    }
