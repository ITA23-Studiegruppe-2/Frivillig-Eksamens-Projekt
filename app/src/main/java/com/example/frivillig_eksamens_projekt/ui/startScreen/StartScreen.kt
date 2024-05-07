package com.example.frivillig_eksamens_projekt.ui.startScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.frivillig_eksamens_projekt.ui.MyProfile.ProfileScreen

@Composable
fun StartScreen(
    onLoginClick: () -> Unit,
    onRegisterClick: () -> Unit
) {
    val backgroundColor = Color(0xFFC8D5B9) // Set your desired background color
    val buttonColor = Color(0xFF364830) // Set to the new dark green color for buttons
    val buttonHeight = 48.dp // Standard material design button height

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = backgroundColor), // Set background color using Modifier.background()
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Velkommen til Volunify", fontSize = 30.sp)
        Spacer(modifier = Modifier.height(20.dp))

        val buttonWidth = 150.dp

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.wrapContentWidth()
        ) {
            Button(
                onClick = onLoginClick,
                modifier = Modifier
                    .width(buttonWidth)
                    .height(buttonHeight),
                colors = ButtonDefaults.buttonColors(containerColor = buttonColor)
            ) {
                Text(text = "Login")
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Eller")
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                onClick = onRegisterClick,
                modifier = Modifier
                    .width(buttonWidth)
                    .height(buttonHeight),
                colors = ButtonDefaults.buttonColors(containerColor = buttonColor)
            ) {
                Text(text = "Opret")
            }
        }
    }
}
