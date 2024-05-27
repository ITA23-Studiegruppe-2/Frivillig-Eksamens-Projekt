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
    onMyActivitiesClick: () -> Unit,
    onCreateShiftClick: () -> Unit,
    onChatScreenClick: () -> Unit,
    onVolunteersClick: () -> Unit
){

    val secondaryColor = Color(0xFF364830)

    val createShiftIcon: Painter = painterResource(id = R.drawable.createshift)
    val peopleIcon: Painter = painterResource(id = R.drawable.people)
    val hoursIcon: Painter = painterResource(id = R.drawable.hours)
    val chatIcon: Painter = painterResource(id = R.drawable.chat)

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
                            onCreateShiftClick()
                        }
                        InfoCards(label = "Chat", icon = chatIcon) {
                            onChatScreenClick()
                        }
                    }
                    Column {
                        InfoCards(label = "Vagtportal", icon = hoursIcon) {
                            onMyActivitiesClick()
                        }
                        InfoCards(label = "Tips & Tricks", icon = peopleIcon) {
                            onVolunteersClick()
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier
                    .padding(12.dp)
            ) {
                HomeScreenNews()
            }
        }
    }
}


