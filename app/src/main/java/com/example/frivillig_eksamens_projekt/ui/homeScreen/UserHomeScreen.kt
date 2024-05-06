package com.example.frivillig_eksamens_projekt.ui.homeScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Build
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.frivillig_eksamens_projekt.repositories.UsersRepository

@Composable
fun HomeScreen(userViewModel: UserViewModel) {

    Surface (
        modifier = Modifier
            .fillMaxSize(),
        color = Color(0xFFC8D5B9)
    ){
        Column(
            modifier = Modifier,
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(22.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Navn Efternavn",
                    modifier = Modifier,
                    fontSize = 23.sp
                )
                Icon(
                    imageVector = Icons.Outlined.Notifications,
                    contentDescription = "Notification icon",
                    modifier = Modifier
                        .size(36.dp)
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            Box() { 
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                )
                {
                    Column {
                        InfoCards(label = "Badges", icon = Icons.Outlined.CheckCircle) {}
                        InfoCards(label = "Kommende vagter", icon = Icons.Outlined.Menu) {}
                    }
                    Column {
                        InfoCards(label = "Ved ikke", icon = Icons.Outlined.Build) {}
                        InfoCards(label = "Ved ikke", icon = Icons.Outlined.Delete) {}
                    }
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Text(text = "Lorem ipsum dolor sit amet, consectetur adipisci elit, " +
                        "sed eiusmod tempor incidunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrum " +
                        "exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea " +
                        "commodi consequatur. Quis aute iure reprehenderit in voluptate velit esse " +
                        "cillum dolore eu fugiat nulla pariatur. Excepteur sint obcaecat cupiditat " +
                        "non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.")
            }
        }
    }
}


