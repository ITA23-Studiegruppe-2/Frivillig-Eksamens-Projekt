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
import com.example.frivillig_eksamens_projekt.ui.badgesScreen.BadgesScreen
import com.example.frivillig_eksamens_projekt.ui.calendarScreen.CalendarScreen2
import com.example.frivillig_eksamens_projekt.ui.calender.CalendarScreen
import com.example.frivillig_eksamens_projekt.ui.chooseScreen.UserOrOrganisation
import com.example.frivillig_eksamens_projekt.ui.createShiftScreen.CreateShift
import com.example.frivillig_eksamens_projekt.ui.homeScreen.HomeScreen
import com.example.frivillig_eksamens_projekt.ui.homeScreen.OrgHomeScreen
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
import androidx.compose.runtime.LaunchedEffect
import com.example.frivillig_eksamens_projekt.ui.OrganisationScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val registerViewModel: CreateUserViewModel = CreateUserViewModel()

    val screensWithNoBottomNavigation: List<String> = listOf(
        Screen.Start.route,
        Screen.Login.route,
        Screen.RegisterUser.route,
        Screen.RegisterUserSecond.route,
        Screen.UserOrOrg.route,
        Screen.Logo.route,
        Screen.RegisterOrg.route
    )

    val currentRoute = remember { mutableStateOf(Screen.Start.route) }

    LaunchedEffect(navController) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            currentRoute.value = destination.route ?: Screen.Start.route
        }
    }

    Scaffold(
        bottomBar = {
            if (!screensWithNoBottomNavigation.contains(currentRoute.value)) {
                BottomNavigationBar(
                    onSearchClick = { navController.navigate(Screen.Activities.route) },
                    onCalenderClick = { navController.navigate(Screen.Calendar.route) },
                    onHomePageClick = { navController.navigate(Screen.Home.route) },
                    onChatPageClick = { /*TODO*/ },
                    onAccountClick = { navController.navigate(Screen.OrganisationProfile.route) }
                )
            }
        },
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screen.Logo.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            // Logo Screen
            composable(Screen.Logo.route) {
                LogoScreen(navController = navController)
            }

            // Start Screen
            composable(Screen.Start.route) {
                StartScreen(
                    onLoginClick = { navController.navigate(Screen.Login.route) },
                    onRegisterClick = { navController.navigate(Screen.UserOrOrg.route) }
                )
            }

            // Login Screen
            composable(Screen.Login.route) {
                LoginScreen(
                    onUserSuccessLogin = { navController.navigate(Screen.Home.route) },
                    onClick = {},
                    onOrgSuccessLogin = { navController.navigate(Screen.OrgHomeScreen.route) }
                )
            }

            // Register User Screen
            composable(Screen.RegisterUser.route) {
                CreateUserScreen(
                    onSuccess = { navController.navigate(Screen.RegisterUserSecond.route) },
                    onFail = { println("Failed") },
                    viewModel = registerViewModel,
                    onBackButtonClick = { navController.popBackStack() }
                )
            }

            // Register User Second Screen
            composable(Screen.RegisterUserSecond.route) {
                CreateUserSecondScreen(
                    onSuccess = { navController.navigate(Screen.Home.route) },
                    onFail = { println("Failed") },
                    onBackButtonClick = { navController.popBackStack() },
                    viewModel = registerViewModel
                )
            }

            // Register Organisation Screen
            composable(Screen.RegisterOrg.route) {
                CreateOrgScreen(
                    onSuccess = { navController.navigate(Screen.OrgHomeScreen.route) },
                    onFail = { /*TODO*/ },
                    onBackButtonClick = { navController.popBackStack() }
                )
            }

            // User Home Screen
            composable(Screen.Home.route) {
                HomeScreen(navController = navController, onBadgeScreenClick = {}, onActivityScreenClick = {})
            }

            // Choose what type of account (Bruger)
            composable(Screen.UserOrOrg.route) {
                UserOrOrganisation(
                    onSuccesUserSelection = { navController.navigate(Screen.RegisterUser.route) },
                    onSuccesOrgSelection = { navController.navigate(Screen.RegisterOrg.route) }
                )
            }

            // Calendar Screen
            composable(Screen.Calendar.route) {
                CalendarScreen2(
                    navController = navController
                )
            }

            // Activities Screen
            composable(Screen.Activities.route) {
                ActivityScreen(onBackButtonClick = {navController.popBackStack()})
            }

            // Badges Screen
            composable(Screen.Badges.route) {
                BadgesScreen(navController)
            }



            // Upcoming Shifts Screen
            composable(Screen.UpcomingShifts.route) {
                UpcomingShifts(onBackButtonClick = {navController.popBackStack()})
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
                CreateShift(
                    onBackButtonClick = { navController.popBackStack() },
                    onSuccess = {}
                )
            }

            // Profile Screen
            composable(Screen.MyProfile.route) {
                ProfileScreen()
            }

            // Organisation Profile Screen
            composable(Screen.OrganisationProfile.route) {
                OrganisationScreen()
            }
        }
    }
}
