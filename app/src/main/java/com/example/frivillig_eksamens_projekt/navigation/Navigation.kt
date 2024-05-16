package com.example.frivillig_eksamens_projekt.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.frivillig_eksamens_projekt.ui.MyProfile.ProfileScreen
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
    val registerViewModel: CreateUserViewModel = CreateUserViewModel()

    // List of all the screens that shouldn't have a bottom navigation bar
    val screensWithNoBottomNavigation: List<String> = listOf(
        Screen.Start.route,
        Screen.Login.route,
        Screen.RegisterUser.route,
        Screen.RegisterUserSecond.route,
        Screen.UserOrOrg.route
    )

    // Store the current route - Should only be Start route, but just to make sure we get the current destination
    val currentRoute = remember { mutableStateOf(navController.currentDestination?.route ?: Screen.Start.route) }

    Scaffold(
        bottomBar = {
            if (!screensWithNoBottomNavigation.contains(currentRoute.value)) {
                BottomNavigationBar(
                    onSearchClick = { navController.navigate(Screen.Activities.route) },
                    onCalenderClick = { navController.navigate(Screen.Calendar.route) },
                    onHomePageClick = { navController.navigate(Screen.Home.route) },
                    onChatPageClick = { navController.navigate(Screen.ChatPage.route) }, // Assuming you have this route
                    onAccountClick = { navController.navigate(Screen.Profile.route) } // Navigate to profile screen
                )
            }
        },
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screen.Start.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(Screen.Home.route) {
                ActivityScreen()
                currentRoute.value = Screen.Home.route
            }
            composable(Screen.Start.route) {
                StartScreen(
                    onLoginClick = { navController.navigate(Screen.Login.route) },
                    onRegisterClick = { navController.navigate(Screen.UserOrOrg.route) }
                )
                currentRoute.value = Screen.Start.route
            }
            composable(Screen.Login.route) {
                LoginScreen(
                    onSuccessLogin = { navController.navigate(Screen.Home.route) }
                )
                currentRoute.value = Screen.Login.route
            }
            composable(Screen.RegisterUser.route) {
                CreateUserScreen(
                    onSuccess = { navController.navigate(Screen.RegisterUserSecond.route) },
                    onFail = { println("Failed") },
                    viewModel = registerViewModel,
                    onClick = {}
                )
                currentRoute.value = Screen.RegisterUser.route
            }
            composable(Screen.RegisterUserSecond.route) {
                CreateUserSecondScreen(
                    onSuccess = { navController.navigate(Screen.Home.route) },
                    onFail = { println("Failed") },
                    viewModel = registerViewModel,
                    onClick = {}
                )
                currentRoute.value = Screen.RegisterUserSecond.route
            }
            composable(Screen.UserOrOrg.route) {
                UserOrOrganisation(
                    onSuccesUserSelection = { navController.navigate(Screen.RegisterUser.route) },
                    onSuccesOrgSelection = {}
                )
                currentRoute.value = Screen.UserOrOrg.route
            }
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
            composable(Screen.Profile.route) {
                ProfileScreen()
                currentRoute.value = Screen.Profile.route
            }
        }
    }
}
