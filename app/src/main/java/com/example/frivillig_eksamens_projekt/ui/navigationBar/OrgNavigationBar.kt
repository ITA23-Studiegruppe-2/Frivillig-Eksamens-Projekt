package com.example.frivillig_eksamens_projekt.ui.navigationBar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.frivillig_eksamens_projekt.navigation.Screen

@Composable
fun OrgBottomNavigationBar(
    onCreateShiftClick: () -> Unit,
    onShiftPortalClick: () -> Unit,
    onHomePageClick: () -> Unit,
    onChatPageClick: () -> Unit,
    onAccountClick: () -> Unit,
    currentRoute: MutableState<String>

) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF364830))
            .padding(4.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        NavigationButton(iconType = Icons.Default.Create, description = "Opret vagt side", onClick = onCreateShiftClick, currentRoute = currentRoute, route = Screen.CreateShift.route)
        NavigationButton(iconType = Icons.Default.List, description = "Vagtportal", onClick = onShiftPortalClick, currentRoute = currentRoute, route = Screen.OrgOwnActivities.route)
        NavigationButton(iconType = Icons.Default.Home, description = "Home skærm", onClick = onHomePageClick, currentRoute = currentRoute, route = Screen.OrgHomeScreen.route)
        NavigationButton(iconType = Icons.Default.Email, description = "Beskeder", onClick = onChatPageClick, currentRoute = currentRoute, route = Screen.ConversationScreen.route)
        NavigationButton(iconType = Icons.Default.AccountCircle, description = "Min profil", onClick = onAccountClick, currentRoute = currentRoute, route = Screen.OrganisationProfile.route)
    }
}