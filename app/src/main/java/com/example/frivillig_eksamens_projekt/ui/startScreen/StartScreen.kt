package com.example.frivillig_eksamens_projekt.ui.startScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.frivillig_eksamens_projekt.ui.registerScreen.BackButton
import com.example.frivillig_eksamens_projekt.ui.registerScreen.CustomButton

@Composable
fun StartScreen(
    onLoginClick: () -> Unit,
    onRegisterClick: () -> Unit,
    onBackButtonClick: () -> Unit
) {



    BackButton(
        onClick = onBackButtonClick,
        modifier = Modifier
            .padding(15.dp)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFC8D5B9)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = "Velkommen til Volunify", fontSize = 30.sp)
        Spacer(modifier = Modifier.height(20.dp))

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CustomButton(text = "Login") {
                onLoginClick()
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Eller")
            Spacer(modifier = Modifier.height(10.dp))

            CustomButton(text = "Opret") {
                onRegisterClick()
            }
        }
    }
}
