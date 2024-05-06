package com.example.frivillig_eksamens_projekt.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.frivillig_eksamens_projekt.ui.activityScreen.ActivityScreen
import com.example.frivillig_eksamens_projekt.ui.calender.CalendarScreen
import com.example.frivillig_eksamens_projekt.ui.calender.CalendarViewModel
import com.example.frivillig_eksamens_projekt.ui.chooseScreen.UserOrOrganisation
import com.example.frivillig_eksamens_projekt.ui.homeScreen.HomeScreen
import com.example.frivillig_eksamens_projekt.ui.homeScreen.UserViewModel
import com.example.frivillig_eksamens_projekt.ui.loginScreen.LoginScreen
import com.example.frivillig_eksamens_projekt.ui.logoScreen.LogoScreen
import com.example.frivillig_eksamens_projekt.ui.registerScreen.CreateUserScreen
import com.example.frivillig_eksamens_projekt.ui.registerScreen.CreateUserSecondScreen
import com.example.frivillig_eksamens_projekt.ui.registerScreen.registerOrg.CreateOrgScreen
import com.example.frivillig_eksamens_projekt.ui.registerScreen.CreateUserViewModel
import com.example.frivillig_eksamens_projekt.ui.startScreen.StartScreen


@Composable
fun Navigation() {
    val navController = rememberNavController()
    // Needs one viewmodel for both registration screens - Initialize it here
    val registerViewModel: CreateUserViewModel = CreateUserViewModel()

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route) {

        //Logo Screen
        composable(Screen.Logo.route) {
            LogoScreen(navController)
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

        // User or Org Screen
        composable(Screen.UserOrOrg.route) {
            UserOrOrganisation(
                onSuccesUserSelection = {navController.navigate(Screen.RegisterUser.route)},
                onSuccesOrgSelection = {navController.navigate(Screen.RegisterOrg.route)},
            )
        }

        // Register User Screen
        composable(Screen.RegisterUser.route) {
            CreateUserScreen(
                onSuccess = {navController.navigate(Screen.RegisterUserSecond.route)},
                // TEMP () ADD INDICATOR
                onFail = { println("Failed")},
                viewModel = registerViewModel,
                onClick = {navController.navigateUp()}
            )
        }

        // Register User Second Screen
        composable(Screen.RegisterUserSecond.route) {
            CreateUserSecondScreen(
                onSuccess = {navController.navigate(Screen.Home.route)},
                // TEMP () ADD INDICATOR
                onFail = { println("Failed")},
                viewModel = registerViewModel,
                onClick = {navController.navigateUp()})
        }

        //Register Organisation Screen
        composable(Screen.RegisterOrg.route){
            CreateOrgScreen(
                onSuccess = { },
                onFail = { /*TODO*/ },
                onClick = {navController.navigateUp()})
        }

        //Home Screen
        composable(Screen.Home.route) {
            HomeScreen(userViewModel = UserViewModel())
        }

        //Activity Screen
        composable(Screen.Activity.route) {
            ActivityScreen()
        }

        // Calendar Screen
        composable(Screen.Calendar.route) {
                CalendarScreen(
                    onCalendarClick = { navController.navigate(Screen.Calendar.route) },
                    viewModel = CalendarViewModel()
                )
            }
    }
}
