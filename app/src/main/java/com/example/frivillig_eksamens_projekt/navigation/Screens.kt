package com.example.frivillig_eksamens_projekt.navigation

sealed class Screen (val route: String) {
    object Home: Screen(route = "home_screen")

    object Start: Screen(route = "start_screen")

    // Authentication Aspect
    object Login: Screen(route = "login_screen")

    object RegisterUser: Screen(route = "register_screen")

    object RegisterUserSecond: Screen(route = "registerSecond_screen")

    object UserOrOrg: Screen(route = "userOrOrg_Screen")

    object Calendar: Screen(route = "calendar_screen")

    object RegisterOrg: Screen(route = "registerOrg_screen")

    object Logo: Screen(route = "logo_screen")

    object Activity: Screen(route = "activity_screen")

    object Badges: Screen(route = "badges_screen")

    object Activities: Screen(route = "activities_screen")
}