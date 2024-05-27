package com.example.frivillig_eksamens_projekt.navigation

import ConversationList
import GroupChatScreen
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.frivillig_eksamens_projekt.ui.MyProfile.ProfileScreen
import com.example.frivillig_eksamens_projekt.ui.OrgAllActivities.ListOfUsersAppliedToActivity.ListOfUsersApplied
import com.example.frivillig_eksamens_projekt.ui.OrgAllActivities.OrgAllActivitiesScreen
import com.example.frivillig_eksamens_projekt.ui.OrganisationProfile.OrganisationScreen
import com.example.frivillig_eksamens_projekt.ui.activityScreen.ActivityScreen
import com.example.frivillig_eksamens_projekt.ui.badgesScreen.BadgesScreen
import com.example.frivillig_eksamens_projekt.ui.calendarScreen.CalendarScreen2
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
import com.example.frivillig_eksamens_projekt.ui.adviceScreen.AdviceScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    // Needs one viewmodel for both registration screens - Initialize it here
    val registerViewModel: CreateUserViewModel = CreateUserViewModel()


    /* Bottom Navigation bar */
    // List of all the screens that shouldn't have a bottom navigation bar
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
            // If the currentRoute (Screen) isn't in the list of screensWithNoBottomNavigation render the navigation bar
            // Set the new current route each time we change it!
            if (!screensWithNoBottomNavigation.contains(currentRoute.value)) {
                BottomNavigationBar(
                    onSearchClick = { navController.navigate(Screen.Activity.route)},
                    onCalenderClick = { navController.navigate(Screen.Calendar.route) },
                    onHomePageClick = { navController.navigate(Screen.Home.route) },
                    onChatPageClick = { navController.navigate(Screen.ConversationScreen.route) },
                    onAccountClick = { navController.navigate(Screen.OrganisationProfile.route) }
                )


            }
        },
    ){
        paddingValues -> NavHost(
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
                onRegisterClick = { navController.navigate(Screen.UserOrOrg.route) },
                onBackButtonClick = { navController.popBackStack() }
            )
            currentRoute.value = Screen.Start.route

        }
        // Login Screen
        composable(Screen.Login.route) {
            // Handle both org and user homeScreen navigation
            LoginScreen(
                onUserSuccessLogin = { navController.navigate(Screen.Home.route) },
                onBackButtonClick = { navController.popBackStack()},
                onOrgSuccessLogin = {navController.navigate(Screen.OrgHomeScreen.route)}
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
                onBackButtonClick = { navController.popBackStack() },
                onLoginHyperLink = {navController.navigate(Screen.Login.route)}
            )
            currentRoute.value = Screen.RegisterUser.route
        }

            // Register User Second Screen
            composable(Screen.RegisterUserSecond.route) {
                // Its a user login -> Send it to the user homeScreen
                CreateUserSecondScreen(
                    onSuccess = { navController.navigate(Screen.Home.route) },
                    // TEMP () ADD INDICATOR
                    onFail = { println("Failed") },
                    onBackButtonClick = { navController.popBackStack() },
                    viewModel = registerViewModel
                )
                currentRoute.value = Screen.RegisterUserSecond.route
            }

        //Register Organisation Screen
        composable(Screen.RegisterOrg.route) {
            CreateOrgScreen(
                onSuccess = { navController.navigate(Screen.OrgHomeScreen.route) }, //Skal laves om til Org Home Screen
                onFail = { /*TODO*/ },
                onBackButtonClick = {navController.popBackStack()}
            )

                currentRoute.value = Screen.RegisterOrg.route
            }

        //User Home Screen
        composable(Screen.Home.route) {
            HomeScreen(
                navController,
                onBadgeScreenClick = {navController.navigate(Screen.Badges.route)},
                onActivityScreenClick = {navController.navigate(Screen.Activity.route)},
                onChatScreenClick = { navController.navigate(Screen.ConversationScreen.route) })

            currentRoute.value = Screen.Home.route
        }

        // Choose what type of account (Bruger)
        composable(Screen.UserOrOrg.route) {
            UserOrOrganisation(
                onSuccesUserSelection = { navController.navigate(Screen.RegisterUser.route) },
                onSuccesOrgSelection = { navController.navigate(Screen.RegisterOrg.route)},
                onBackButtonClick = {navController.popBackStack()}
            )
            currentRoute.value = Screen.UserOrOrg.route
        }

        // Calendar Screen
        composable(Screen.Calendar.route) {
            CalendarScreen2(
                onBackButtonClick = {navController.popBackStack()}
            )
            currentRoute.value = Screen.Calendar.route
        }

        //Badges Screen
        composable(Screen.Badges.route) {
            BadgesScreen(
                onBackButtonClick = {navController.popBackStack()})

            currentRoute.value = Screen.Badges.route
        }

        //Activity Screen
        composable(Screen.Activity.route) {
            ActivityScreen(
                onBackButtonClick = {navController.popBackStack()}
            )
            currentRoute.value = Screen.Activity.route
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
                    onMyActivitiesClick = { navController.navigate(Screen.OrgOwnActivities.route)},
                    onChatScreenClick = {navController.navigate(Screen.Home.route)},
                    onCreateShiftClick = {navController.navigate(Screen.CreateShift.route)},
                    onVolunteersClick = {navController.navigate(Screen.Advice.route)}
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

        // Volunteers Screen
        composable(Screen.Advice.route) {
            AdviceScreen (
                onBackButtonClick = {navController.popBackStack()})
        }





            // Resume a conversation
        composable(Screen.GroupChat.route,
            arguments = listOf(
                navArgument("conversationId"){ type = NavType.StringType },
                navArgument("organizationName"){ type = NavType.StringType})
            )

        {
            backStackEntry ->
            val conversationId = backStackEntry.arguments?.getString("conversationId")
            val organizationName = backStackEntry.arguments?.getString("organizationName")
            if (conversationId != null && organizationName != null) {
               GroupChatScreen(
                   conversationId = conversationId,
                   activityId = conversationId,
                   organizationName = organizationName,
                   onBackButtonClick = { navController.popBackStack() } )
            }
        }

        composable(Screen.ConversationScreen.route) {
            ConversationList(
                onResumeClick = { conversationId, organizationName ->
                    navController.navigate(Screen.GroupChat.createRoute(conversationId, organizationName))},
                onBackButtonClick = { navController.popBackStack()})

        }

       composable(Screen.MyProfile.route) {
           ProfileScreen()
           currentRoute.value = Screen.MyProfile.route
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
        composable(Screen.OrganisationProfile.route) {
            OrganisationScreen()
        }
        }
    }
}



