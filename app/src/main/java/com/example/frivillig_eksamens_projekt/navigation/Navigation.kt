package com.example.frivillig_eksamens_projekt.navigation

import androidx.compose.runtime.Composable
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
import com.example.frivillig_eksamens_projekt.ui.registerScreen.CreateUserScreen
import com.example.frivillig_eksamens_projekt.ui.registerScreen.CreateUserSecondScreen
import com.example.frivillig_eksamens_projekt.ui.registerScreen.registerOrg.CreateOrgScreen
import com.example.frivillig_eksamens_projekt.ui.registerScreen.CreateUserViewModel
import com.example.frivillig_eksamens_projekt.ui.startScreen.StartScreen
import com.example.frivillig_eksamens_projekt.ui.upcomingShiftsScreen.UpcomingShifts
import java.time.LocalDate


@Composable
fun Navigation() {
    val navController = rememberNavController()
    // Needs one viewmodel for both registration screens - Initialize it here
    val registerViewModel: CreateUserViewModel = CreateUserViewModel()

    NavHost(
        navController = navController,
        startDestination = Screen.Logo.route) {

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
                navController)
        }

        // Register User Second Screen
        composable(Screen.RegisterUserSecond.route) {
            CreateUserSecondScreen(
                onSuccess = {navController.navigate(Screen.Home.route)},
                // TEMP () ADD INDICATOR
                onFail = { println("Failed")},
                navController,
                viewModel = registerViewModel)
        }

        //Register Organisation Screen
        composable(Screen.RegisterOrg.route){
            CreateOrgScreen(
                onSuccess = {navController.navigate(Screen.Home.route)}, //Skal laves om til Org Home Screen
                onFail = { /*TODO*/ },
                navController)
        }

        //User Home Screen
        composable(Screen.Home.route) {
            HomeScreen(userViewModel = UserViewModel(), navController)
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

    }
}
