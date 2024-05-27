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

    object ChatPage: Screen(route = "chatPage_screen")

    object CreateShift: Screen(route = "create_shift_screen")

    object OrgHomeScreen: Screen(route = "orgHome_screen")

    object Hours: Screen(route = "hours")

    object UpcomingShifts: Screen(route = "upcoming_shifts_screen")

    object MyProfile: Screen(route = "my_profile_screen")

//<<<<<<< HEAD


    object GroupChat: Screen(route = "group_chat_screen/{conversationId}/{organizationName}") {
        fun createRoute(conversationId: String, organizationName: String) =
            "group_chat_screen/$conversationId/$organizationName"
    }

    object ConversationScreen: Screen(route = "conversation_screen")

    object Chat: Screen(route = "chat_screen")

    object AddChatScreen: Screen(route = "sendMessage_screen")
//=======
    object OrganisationProfile: Screen(route = "Organisation_Profile")
//>>>>>>> origin/main
}