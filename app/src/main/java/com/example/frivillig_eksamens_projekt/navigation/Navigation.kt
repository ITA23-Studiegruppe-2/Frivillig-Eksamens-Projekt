package com.example.frivillig_eksamens_projekt.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.frivillig_eksamens_projekt.ui.loginScreen.LoginScreen


@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "") {

        //Home Screen
        composable(Screen.Home.route) {
            // ADD HOMESCREEN COMPOSEABLE
        }

        // Start Screen
        composable(Screen.Start.route) {
            // ADD START SCREEN COMPOSABLE
        }

        // Login Screen
        composable(Screen.Login.route) {
            LoginScreen(
                onSuccessLogin = {navController.navigate(Screen.Home.route)}
            )
        }
        // Register Screen
        composable(Screen.Register.route) {
            // ADD REGISTER SCREEN COMPOSEABLE
        }

    }
}