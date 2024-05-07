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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.frivillig_eksamens_projekt.R
import com.example.frivillig_eksamens_projekt.ui.registerScreen.BackButton

@Composable
fun BadgesScreen(
    onClick: () -> Unit
) {
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
                    .padding(22.dp),
                contentAlignment = Alignment.Center
                ) {
                BackButton (onClick = onClick)
                Spacer(modifier = Modifier.height(18.dp))
                Text(text = "Badges", fontSize = 28.sp, color = secondaryColor)
            }
            Spacer(modifier = Modifier.height(25.dp))
            Text(
                text = "Her finder du de udmærkelser du har opnået:", 
                fontSize = 16.sp, 
                color = secondaryColor)
            
            Spacer(modifier = Modifier.height(36.dp))

            Box(
                modifier = Modifier
                    .width(380.dp)
                    .background(color = Color.White)
                    .padding(20.dp)
            ){
                Column {
                    Box(
                        modifier = Modifier,
                    ) {
                        Text(text = "Opnåede badges:")
                    }
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
                            icon = painterResource(id = R.drawable.christmas)) {}
                    }
                    Box(
                        modifier = Modifier,
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "Låste badges:")
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                    ){
                        Badges(
                            label = "Locked hour 20",
                            icon = painterResource(id = R.drawable.hour20_locked))
                        {}
                        Badges(
                            label = "Locked hour 50",
                            icon = painterResource(id = R.drawable.hour50_locked))
                        {}
                        Badges(
                            label = "Locked Different",
                            icon = painterResource(id = R.drawable.different_locked)) 
                        {}
                        Badges(
                            label = "Locked loyal", 
                            icon = painterResource(id = R.drawable.loyal_locked)) 
                        {}
                    }
                }

            }
        }
    }
}