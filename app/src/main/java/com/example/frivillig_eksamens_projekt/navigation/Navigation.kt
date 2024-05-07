package com.example.frivillig_eksamens_projekt.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.frivillig_eksamens_projekt.ui.activityScreen.ActivityScreen
import com.example.frivillig_eksamens_projekt.ui.calender.CalendarScreen
import com.example.frivillig_eksamens_projekt.ui.calender.CalendarViewModel
import com.example.frivillig_eksamens_projekt.ui.chooseScreen.UserOrOrganisation
import com.example.frivillig_eksamens_projekt.ui.loginScreen.LoginScreen
import com.example.frivillig_eksamens_projekt.ui.navigationBar.BottomNavigationBar
import com.example.frivillig_eksamens_projekt.ui.registerScreen.CreateUserScreen
import com.example.frivillig_eksamens_projekt.ui.registerScreen.CreateUserSecondScreen
import com.example.frivillig_eksamens_projekt.ui.registerScreen.CreateUserViewModel
import com.example.frivillig_eksamens_projekt.ui.startScreen.StartScreen


@Composable
fun Navigation() {
    val navController = rememberNavController()
    // Needs one viewmodel for both registration screens - Initialize it here
    val registerViewModel: CreateUserViewModel = CreateUserViewModel()


    /* Bottom Navigation bar */
    // List of all the screens that shouldnt have a bottom navigation bar
    var screensWithNoBottomNavigation: List<String> = listOf(
        Screen.Start.route,
        Screen.Login.route,
        Screen.RegisterUser.route,
        Screen.RegisterUserSecond.route,
        Screen.UserOrOrg.route
    )
    // Store the current route
    var currentRoute = remember { mutableStateOf(navController.currentDestination?.route ?: Screen.Start.route) }
    Scaffold(
        bottomBar = {
            // If the currentRoute (Screen) isnt in the list of screensWithNoBottomNavigation render the navigation bar
            if (!screensWithNoBottomNavigation.contains(currentRoute.value)) {
                BottomNavigationBar(
                    onSearchClick = { navController.navigate(Screen.Activities.route)},
                    onCalenderClick = { navController.navigate(Screen.Calendar.route) },
                    onHomePageClick = { navController.navigate(Screen.Home.route) },
                    onEmailClick = { /*TODO*/ },
                    onAccountClick = { /*Todo*/ }
                )


            }
        },
    ){
        paddingValues -> NavHost(
        navController = navController,
        startDestination = Screen.Start.route,
            modifier = Modifier.padding(paddingValues)
        ) {

        //Home Screen
        composable(Screen.Home.route) {
            ActivityScreen()
            currentRoute.value = Screen.Home.route
        }

        // Start Screen
        composable(Screen.Start.route) {
            StartScreen(
                onLoginClick = { navController.navigate(Screen.Login.route) },
                onRegisterClick = { navController.navigate(Screen.UserOrOrg.route)}
            )
            currentRoute.value = Screen.Start.route

        }

        // Login Screen
        composable(Screen.Login.route) {
            LoginScreen(
                onSuccessLogin = {navController.navigate(Screen.Home.route)}
            )
            currentRoute.value = Screen.Login.route
        }
        // Register User Screen
        composable(Screen.RegisterUser.route) {
            CreateUserScreen(
                onSuccess = {navController.navigate(Screen.RegisterUserSecond.route)},
                // TEMP () ADD INDICATOR
                onFail = { println("Failed")},
                viewModel = registerViewModel
            )
            currentRoute.value = Screen.RegisterUser.route

        }

        // Register User Second Screen
        composable(Screen.RegisterUserSecond.route) {
            CreateUserSecondScreen(
                onSuccess = {navController.navigate(Screen.Home.route)},
                // TEMP () ADD INDICATOR
                onFail = { println("Failed")},
                viewModel = registerViewModel
            )
            currentRoute.value = Screen.RegisterUserSecond.route

        }
        // Choose what type of account (Bruger)
        composable(Screen.UserOrOrg.route) {
            UserOrOrganisation(
                onSuccesUserSelection = {navController.navigate(Screen.RegisterUser.route)}
            )
            currentRoute.value = Screen.UserOrOrg.route
        }

        // Calendar Screen
        composable(Screen.Calendar.route) {
            CalendarScreen(
                onCalendarClick = { navController.navigate(Screen.Calendar.route) },
                viewModel = CalendarViewModel()
            )
            currentRoute.value = Screen.Calendar.route
        }
        composable(Screen.Activities.route) {
            ActivityScreen()
            currentRoute.value = Screen.Activities.route
        }

    }
    }


}
