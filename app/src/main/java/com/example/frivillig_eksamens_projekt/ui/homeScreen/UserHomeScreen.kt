package com.example.frivillig_eksamens_projekt.ui.homeScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Icon
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
import com.example.frivillig_eksamens_projekt.ui_elements.theme.title

@Composable
fun HomeScreen(
    navController: NavController,
    onBadgeScreenClick: () -> Unit,
    onActivityScreenClick: () -> Unit,
    onChatScreenClick: () -> Unit
) {

    val bagdesIcon: Painter = painterResource(id = R.drawable.badges)
    val shiftsIcon: Painter = painterResource(id = R.drawable.shift)
    val hoursIcon: Painter = painterResource(id = R.drawable.hours)
    val calendarIcon: Painter = painterResource(id = R.drawable.calendar)

    val secondaryColor = Color(0xFF364830)
    val viewModel = UserViewModel()



    Surface (
        modifier = Modifier
            .fillMaxSize(),
        color = Color(0xFFC8D5B9)
    ){
        //Dialog for notifications
        if (viewModel.dialogShow) {
            NotificationsDialog(
                onDismissRequest = { viewModel.dialogShow = false},
                listOfNotifications = viewModel.listOfNotifications,
                onMarkAsReadButtonClick = {
                    viewModel.markNotificationsAsRead()
                    viewModel.dialogShow = false

                }
            )
        }

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
                        color = Color.White,
                        style = title
                    )
                    Icon(
                        imageVector = Icons.Outlined.Notifications,
                        contentDescription = "Notification icon",
                        modifier = Modifier
                            .size(36.dp)
                            .clickable {
                                       viewModel.dialogShow = true
                            },
                        tint = viewModel.bellColor
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
                        InfoCards(label = "Badges", icon = bagdesIcon, onClick = onBadgeScreenClick)
                        InfoCards(label = "Kommende vagter", icon = shiftsIcon) {
                            navController.navigate(Screen.UpcomingShifts.route)
                        }
                    }
                    Column {
                        InfoCards(label = "Timer", icon = hoursIcon) {
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
                    Shortcut(onClick = onActivityScreenClick, label = "Ledige vagter")
                    Shortcut(onClick = onChatScreenClick, label = "Mine samtaler")
                }
            Box(
                modifier = Modifier
                    .padding(12.dp)
            ) {

                HomeScreenNews()
            }
        }
    }
}


