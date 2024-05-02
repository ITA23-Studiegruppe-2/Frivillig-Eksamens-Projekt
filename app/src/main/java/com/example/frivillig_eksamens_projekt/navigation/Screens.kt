package com.example.frivillig_eksamens_projekt.navigation

sealed class Screen (val route: String) {
    object Home: Screen(route = "home_screen")

    object Start: Screen(route = "start_screen")

    // Authentication Aspect
    object Login: Screen(route = "login_screen")

    object RegisterUser: Screen(route = "register_screen")

    object RegisterUserSecond: Screen(route = "registerSecond_screen")

    object UserOrOrg: Screen(route = "userOrOrg_Screen")
}