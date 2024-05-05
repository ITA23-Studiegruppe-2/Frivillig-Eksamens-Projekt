package com.example.frivillig_eksamens_projekt.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.frivillig_eksamens_projekt.ui.activityScreen.ActivityScreen
import com.example.frivillig_eksamens_projekt.ui.chooseScreen.UserOrOrganisation
import com.example.frivillig_eksamens_projekt.ui.loginScreen.LoginScreen
import com.example.frivillig_eksamens_projekt.ui.navigationBar.BottomNavigationBar
import com.example.frivillig_eksamens_projekt.ui.registerScreen.CreateUserScreen
import com.example.frivillig_eksamens_projekt.ui.registerScreen.CreateUserSecondScreen
import com.example.frivillig_eksamens_projekt.ui.startScreen.StartScreen


@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Start.route) {

        //Home Screen
        composable(Screen.Home.route) {
            ActivityScreen()
        }

        // Start Screen
        composable(Screen.Start.route) {
            StartScreen(
                onLoginClick = { navController.navigate(Screen.Login.route) },
                onRegisterClick = { navController.navigate(Screen.UserOrOrg.route)})
        }

        // Login Screen
        composable(Screen.Login.route) {
            LoginScreen(
                onSuccessLogin = {navController.navigate(Screen.Home.route)},
                onFailure = { println("Error")}
            )
        }
        // Register User Screen
        composable(Screen.RegisterUser.route) {
            CreateUserScreen(
                onSuccess = {navController.navigate(Screen.RegisterUserSecond.route)},
                // TEMP () ADD INDICATOR
                onFail = { println("Failed")})

        }

        // Register User Second Screen
        composable(Screen.RegisterUserSecond.route) {
            CreateUserSecondScreen(
                onSuccess = {navController.navigate(Screen.Home.route)},
                // TEMP () ADD INDICATOR
                onFail = { println("Failed")})

        }
        // Choose what type of account (Bruger)
        composable(Screen.UserOrOrg.route) {
            UserOrOrganisation(
                onSuccesUserSelection = {navController.navigate(Screen.RegisterUser.route)}
            )
        }

        // Calendar Screen
        composable(Screen.Calendar.route) {
                BottomNavigationBar(
                    onSearchClick = { /*TODO*/ },
                    onCalenderClick = { navController.navigate(Screen.Calendar.route) },
                    onHomePageClick = { /*TODO*/ },
                    onEmailClick = { /*TODO*/ }) {
                }
        }
    }
}
