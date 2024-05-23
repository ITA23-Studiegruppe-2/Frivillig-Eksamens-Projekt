package com.example.frivillig_eksamens_projekt.ui.navigationBar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun BottomNavigationBar(
    onSearchClick: () -> Unit,
    onCalenderClick: () -> Unit,
    onHomePageClick: () -> Unit,
    onChatPageClick: () -> Unit,
    onAccountClick: () -> Unit

) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF364830))
                .padding(4.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            NavigationButton(iconType = Icons.Default.Search, description = "Search page", onClick = onSearchClick)
            NavigationButton(iconType = Icons.Default.DateRange, description = "Calender page", onClick = onCalenderClick)
            NavigationButton(iconType = Icons.Default.Home, description = "Home page", onClick = onHomePageClick)
            NavigationButton(iconType = Icons.Default.Email, description = "Chat page", onClick = onChatPageClick)
            NavigationButton(iconType = Icons.Default.AccountCircle, description = "My profile page", onClick = onAccountClick)
        }



}

@Preview (showBackground = true )
@Composable
fun Preview() {
    BottomNavigationBar({},{},{},{},{})

}
// LIST OF ICON NAME TO USE
// Use Icons.Default.<Name> as parameter

// Search
// DateRange
// Home
// Email
// AccountCircle