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
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.frivillig_eksamens_projekt.R
import com.example.frivillig_eksamens_projekt.navigation.Screen

@Composable
fun OrgHomeScreen(
    navController: NavController,
    onMyActivitiesClick: () -> Unit
){

    val secondaryColor = Color(0xFF364830)

    val createShiftIcon: Painter = painterResource(id = R.drawable.createshift)
    val peopleIcon: Painter = painterResource(id = R.drawable.people)
    val hoursIcon: Painter = painterResource(id = R.drawable.hours)
    val calendarIcon: Painter = painterResource(id = R.drawable.calendar)

    val viewModel: OrgViewModel = OrgViewModel()

    Surface (
        modifier = Modifier
            .fillMaxSize(),
        color = Color(0xFFC8D5B9)
    ){
        Column() {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(secondaryColor),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(22.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Hej ${viewModel.name}!",
                        modifier = Modifier,
                        fontSize = 23.sp,
                        color = Color.White
                    )
                    Icon(
                        imageVector = Icons.Outlined.Notifications,
                        contentDescription = "Notification icon",
                        modifier = Modifier
                            .size(36.dp),
                        tint = Color.White
                    )
                }
            }
            Spacer(modifier = Modifier.height(28.dp))
            Box() {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                )
                {
                    Column {
                        InfoCards(label = "Opret vagt", icon = createShiftIcon) {
                            navController.navigate(Screen.CreateShift.route)
                        }
                        InfoCards(label = "Frivillige", icon = peopleIcon) {
                            navController.navigate(Screen.UpcomingShifts.route)
                        }
                    }
                    Column {
                        InfoCards(label = "Vagtportal", icon = hoursIcon) {
                            navController.navigate(Screen.Hours.route)
                        }
                        InfoCards(label = "Kalender", icon = calendarIcon) {
                            navController.navigate(Screen.Calendar.route)
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
                Column (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp)
                ){
                    Shortcut(onClick = {}, label = "Chat med de frivillige")
                    Shortcut(onClick = onMyActivitiesClick, label = "Mine ??")
                }
            Box(
                modifier = Modifier
                    .padding(12.dp)
            ) {
                HomeScreenNews(title = "New News", news = "hej hej hej hej hej" +
                        "hej hej hejh eejh hej hej hej hej hej hej")

            }
        }
    }
}


