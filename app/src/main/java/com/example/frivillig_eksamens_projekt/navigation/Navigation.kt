package com.example.frivillig_eksamens_projekt.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.fragment.app.FragmentManager.BackStackEntry
import androidx.navigation.NavType
import androidx.navigation.activity
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import androidx.navigation.navArgument
import com.example.frivillig_eksamens_projekt.ui.OrgAllActivities.ListOfUsersAppliedToActivity.ListOfUsersApplied
import com.example.frivillig_eksamens_projekt.ui.OrgAllActivities.OrgAllActivitiesScreen
import com.example.frivillig_eksamens_projekt.ui.activityScreen.ActivityScreen
// import com.example.frivillig_eksamens_projekt.ui.badgesScreen.BadgesScreen
import com.example.frivillig_eksamens_projekt.ui.calendarScreen.CalendarScreen2
import com.example.frivillig_eksamens_projekt.ui.MyProfile.ProfileScreen
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
import com.example.frivillig_eksamens_projekt.ui.badgesScreen.BadgesScreen

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
                BadgesScreen(onBackButtonClick = {navController.popBackStack()})

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
                OrgHomeScreen(navController,{})
            }

            // Create Shift Screen
            composable(Screen.CreateShift.route) {
                CreateShift(
                    onBackButtonClick = { navController.popBackStack() },
                    onSuccess = {}
                )
            }


            currentRoute.value = Screen.RegisterOrg.route



            //User Home Screen
            composable(Screen.Home.route) {
                HomeScreen(navController,
                    onBadgeScreenClick = {navController.navigate(Screen.Badges.route)},
                    onActivityScreenClick = {navController.navigate(Screen.Activity.route)}
                )


                currentRoute.value = Screen.Home.route
            }

            // Choose what type of account (Bruger)
            composable(Screen.UserOrOrg.route) {
                UserOrOrganisation(
                    onSuccesUserSelection = { navController.navigate(Screen.RegisterUser.route) },
                    onSuccesOrgSelection = { navController.navigate(Screen.RegisterOrg.route)}
                )
                currentRoute.value = Screen.UserOrOrg.route
            }

            // Calendar Screen
            composable(Screen.Calendar.route) {
                CalendarScreen2(navController)
                currentRoute.value = Screen.Calendar.route
            }
            composable(Screen.Activities.route) {
                ActivityScreen(onBackButtonClick = {navController.popBackStack()})
                currentRoute.value = Screen.Activities.route
            }

            //Badges Screen
            composable(Screen.Badges.route) {
                // BadgesScreen(navController)

                currentRoute.value = Screen.Badges.route
            }

            // Upcoming Shifts Screen
            composable(Screen.UpcomingShifts.route) {
                UpcomingShifts(onBackButtonClick = {navController.popBackStack()})

                currentRoute.value = Screen.UpcomingShifts.route
            }

            // Hours Screen
            composable(Screen.Hours.route) {
                HoursScreen(navController)

                currentRoute.value = Screen.Hours.route
            }

            // Organisation Home Screen
            composable(Screen.OrgHomeScreen.route) {
                OrgHomeScreen(
                    navController,
                    onMyActivitiesClick = {navController.navigate(Screen.OrgOwnActivities.route)}
                )

                currentRoute.value = Screen.OrgHomeScreen.route
            }

            // Create Shift Screen
            composable(Screen.CreateShift.route) {
                CreateShift(
                    onBackButtonClick = {navController.popBackStack()},
                    onSuccess = {navController.popBackStack()}
                )
                currentRoute.value = Screen.CreateShift.route
            }

            composable(Screen.MyProfile.route) {
                ProfileScreen()
                currentRoute.value = Screen.MyProfile.route
            }

            //Orgs list of own activities
            composable(Screen.OrgOwnActivities.route) {
                OrgAllActivitiesScreen(
                    onBackButtonClick = {navController.popBackStack()},
                    onActivityListClick = { activityId ->
                        navController.navigate(Screen.ListOfUsersAppliedActivity.createRoute(activityId))}
                )
            }

            //List of users applied - org admin view
            composable(Screen.ListOfUsersAppliedActivity.route,
                arguments = listOf(navArgument("activityId") {type = NavType.StringType})
            )
            {
                    backStackEntry ->
                val activityId = backStackEntry.arguments?.getString("activityId")
                if (activityId != null) {
                    ListOfUsersApplied(activityId = activityId, onBackButtonClick = {navController.popBackStack()})
                }
            }
        }
    }
}

