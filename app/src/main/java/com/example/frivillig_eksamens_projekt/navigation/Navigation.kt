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
import com.example.frivillig_eksamens_projekt.ui.activityScreen.ActivityScreen
import com.example.frivillig_eksamens_projekt.ui.badgesScreen.BadgesScreen
import com.example.frivillig_eksamens_projekt.ui.calendarScreen.CalendarScreen2
import com.example.frivillig_eksamens_projekt.ui.calender.CalendarScreen
import com.example.frivillig_eksamens_projekt.ui.calender.CalendarViewModel
import com.example.frivillig_eksamens_projekt.ui.chooseScreen.UserOrOrganisation
import com.example.frivillig_eksamens_projekt.ui.createShiftScreen.CreateShift
import com.example.frivillig_eksamens_projekt.ui.createShiftScreen.CreateShiftViewModel
import com.example.frivillig_eksamens_projekt.ui.homeScreen.HomeScreen
import com.example.frivillig_eksamens_projekt.ui.homeScreen.OrgHomeScreen
import com.example.frivillig_eksamens_projekt.ui.homeScreen.UserViewModel
import com.example.frivillig_eksamens_projekt.ui.hoursScreen.HoursScreen
import com.example.frivillig_eksamens_projekt.ui.loginScreen.LoginScreen
import com.example.frivillig_eksamens_projekt.ui.logoScreen.LogoScreen
import com.example.frivillig_eksamens_projekt.ui.navigationBar.BottomNavigationBar
import com.example.frivillig_eksamens_projekt.ui.registerScreen.CreateUserScreen
import com.example.frivillig_eksamens_projekt.ui.registerScreen.CreateUserSecondScreen
import com.example.frivillig_eksamens_projekt.ui.registerScreen.CreateUserViewModel
import com.example.frivillig_eksamens_projekt.ui.registerScreen.registerOrg.CreateOrgScreen
import com.example.frivillig_eksamens_projekt.ui.startScreen.StartScreen
import com.example.frivillig_eksamens_projekt.ui.upcomingShiftsScreen.UpcomingShifts


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
    // Store the current route - Should only be Start route, but just to make sure we get the current destination
    val currentRoute = remember { mutableStateOf(navController.currentDestination?.route ?: Screen.Start.route) }
    Scaffold(
        bottomBar = {
            // If the currentRoute (Screen) isnt in the list of screensWithNoBottomNavigation render the navigation bar
            // Set the new current route each time we change it!
            if (!screensWithNoBottomNavigation.contains(currentRoute.value)) {
                BottomNavigationBar(
                    onSearchClick = { navController.navigate(Screen.Activities.route)},
                    onCalenderClick = { navController.navigate(Screen.Calendar.route) },
                    onHomePageClick = { navController.navigate(Screen.Home.route) },
                    onChatPageClick = { /*TODO*/ },
                    onAccountClick = { /*Todo*/ }
                )


            }
        },
    ){
        paddingValues -> NavHost(
        navController = navController,
        startDestination = Screen.Logo.route,
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
                onRegisterClick = { navController.navigate(Screen.UserOrOrg.route) }
            )
            currentRoute.value = Screen.Start.route

        }

        // Login Screen
        composable(Screen.Login.route) {
            LoginScreen(
                onSuccessLogin = { navController.navigate(Screen.Home.route) },
                onClick = {}
            )
            currentRoute.value = Screen.Login.route
        }
        // Register User Screen
        composable(Screen.RegisterUser.route) {
            CreateUserScreen(
                onSuccess = { navController.navigate(Screen.RegisterUserSecond.route) },
                // TEMP () ADD INDICATOR
                onFail = { println("Failed") },
                viewModel = registerViewModel,
                onBackButtonClick = { navController.popBackStack() }
            )
            currentRoute.value = Screen.RegisterUser.route
        }

        // Register User Second Screen
        composable(Screen.RegisterUserSecond.route) {
            CreateUserSecondScreen(
                onSuccess = { navController.navigate(Screen.Home.route) },
                // TEMP () ADD INDICATOR
                onFail = { println("Failed") },
                navController,
                viewModel = registerViewModel
            )
        }

        //Register Organisation Screen
        composable(Screen.RegisterOrg.route) {

            CreateOrgScreen(
                onSuccess = { navController.navigate(Screen.Home.route) }, //Skal laves om til Org Home Screen
                onFail = { /*TODO*/ },
                navController
            )

            currentRoute.value = Screen.RegisterUserSecond.route
        }


        //User Home Screen
        composable(Screen.Home.route) {
            HomeScreen(userViewModel = UserViewModel(), navController)
        }
        // Choose what type of account (Bruger)
        composable(Screen.UserOrOrg.route) {
            UserOrOrganisation(
                onSuccesUserSelection = { navController.navigate(Screen.RegisterUser.route) },
                onSuccesOrgSelection = {}
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

        //Badges Screen
        composable(Screen.Badges.route) {
            BadgesScreen(navController)
        }

        // Second Calendar Screen
        composable(Screen.Calendar2.route) {
            CalendarScreen2(navController)
        }

        // Upcoming Shifts Screen
        composable(Screen.UpcomingShifts.route) {
            UpcomingShifts(navController)
        }

        // Hours Screen
        composable(Screen.Hours.route) {
            HoursScreen(navController)
        }

        // Organisation Home Screen
        composable(Screen.OrgHomeScreen.route) {
            OrgHomeScreen(navController)
        }

        // Create Shift Screen
        composable(Screen.CreateShift.route) {
            CreateShift(navController, viewModel = CreateShiftViewModel())
        }

        composable(Screen.Logo.route) {
            LogoScreen(navController = navController)
        }
    }
    }
}

