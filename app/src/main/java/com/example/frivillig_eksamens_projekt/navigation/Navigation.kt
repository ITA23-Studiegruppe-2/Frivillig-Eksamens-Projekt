package com.example.frivillig_eksamens_projekt.navigation

/*

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
                viewModel = registerViewModel,
                onClick = {}
            )
            currentRoute.value = Screen.RegisterUser.route

        }

        // Register User Second Screen
        composable(Screen.RegisterUserSecond.route) {
            CreateUserSecondScreen(
                onSuccess = {navController.navigate(Screen.Home.route)},
                // TEMP () ADD INDICATOR
                onFail = { println("Failed")},
                viewModel = registerViewModel,
                onClick = {}
            )
            currentRoute.value = Screen.RegisterUserSecond.route

        }
        // Choose what type of account (Bruger)
        composable(Screen.UserOrOrg.route) {
            UserOrOrganisation(
                onSuccesUserSelection = {navController.navigate(Screen.RegisterUser.route)},
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

    }
    }


}
 */
