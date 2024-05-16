package com.example.frivillig_eksamens_projekt.ui.badgesScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.frivillig_eksamens_projekt.R
import com.example.frivillig_eksamens_projekt.ui.registerScreen.BackButton

@Composable
fun BadgesScreen(navController: NavController)
{
    val secondaryColor = Color(0xFF364830)

    Surface (
        modifier = Modifier
            .fillMaxSize(),
        color = Color(0xFFC8D5B9)
    ){
        Column (
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .height(70.dp)
                    .width(390.dp)
                    .background(color = Color.White, shape = RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
                ) {
                BackButton (onClick = {navController.popBackStack()})
                Text(text = "Badges", fontSize = 28.sp, color = secondaryColor)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Box(
                modifier = Modifier
                    .padding(26.dp),
                contentAlignment = Alignment.Center
            ){
                Text(
                    text = "Saml mærkater i Volunify ved at tage " +
                            "forskellige vagter og se dine belønninger vokse!",
                    fontSize = 16.sp,
                    color = secondaryColor
                )
            }
            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier
                    .width(380.dp)
                    .background(color = Color.White, shape = RoundedCornerShape(8.dp))
                    .padding(20.dp)
            ){
                Column {
                    Row (
                        modifier = Modifier
                            .fillMaxWidth(),
                    ){
                        Badges(
                            label = "Newbie",
                            icon = painterResource(id = R.drawable.newbie))
                        {}
                        Badges(
                            label = "5 hours",
                            icon = painterResource(id = R.drawable.hour5))
                        {}
                        Badges(
                            label = "Christmas",
                            icon = painterResource(id = R.drawable.christmas))
                        {}
                        Badges(
                            label = "Locked hour 20",
                            icon = painterResource(id = R.drawable.hour20_locked))
                        {}
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                    ){
                        Badges(
                            label = "Locked loyal",
                            icon = painterResource(id = R.drawable.loyal_locked))
                        {}
                        Badges(
                            label = "Locked hour 50",
                            icon = painterResource(id = R.drawable.hour50_locked))
                        {}
                        Badges(
                            label = "Locked different organisations",
                            icon = painterResource(id = R.drawable.different_locked)) 
                        {}
                        Badges(
                            label = "Locked recommended by others",
                            icon = painterResource(id = R.drawable.recommended_locked))
                        {}
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                    ){
                        Badges(
                            label = "Locked animals",
                            icon = painterResource(id = R.drawable.animal_locked))
                        {}
                        Badges(
                            label = "Locked recommended others",
                            icon = painterResource(id = R.drawable.curious_locked))
                        {}
                        Badges(
                            label = "Locked earth",
                            icon = painterResource(id = R.drawable.earth_locked))
                        {}
                        Badges(
                            label = "Locked 75 hours",
                            icon = painterResource(id = R.drawable.hour75_locked))
                        {}
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                    ){
                        Badges(
                            label = "Locked night",
                            icon = painterResource(id = R.drawable.night_locked))
                        {}
                        Badges(
                            label = "Locked early",
                            icon = painterResource(id = R.drawable.early_locked))
                        {}
                        Badges(
                            label = "Locked 100 hours",
                            icon = painterResource(id = R.drawable.hour100_locked))
                        {}
                        Badges(
                            label = "Locked helped people",
                            icon = painterResource(id = R.drawable.helped_people_locked))
                        {}
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                    ){
                        Badges(
                            label = "Locked festival",
                            icon = painterResource(id = R.drawable.festival_locked))
                        {}
                        Badges(
                            label = "Locked 150 hours",
                            icon = painterResource(id = R.drawable.hour150_locked))
                        {}
                        Badges(
                            label = "Locked more than 150 hours",
                            icon = painterResource(id = R.drawable.over150_locked))
                        {}
                        Badges(
                            label = "Locked expert",
                            icon = painterResource(id = R.drawable.expert_locked))
                        {}
                    }
                }

            }
        }
    }
}