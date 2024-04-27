package com.example.frivillig_eksamens_projekt.ui.startScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun StartScreen(
    onLoginClick: () -> Unit,
    onRegisterClick: () -> Unit
) {

    // TEMP DESIGN - RASMUS STÅR IKKE FOR DET!!!!

    Column {
        Text(text = "Velkommen til Volunify")
        Row {
            Button(onClick = onLoginClick) {
                Text(text = "Login")
            }
            Button(onClick = onRegisterClick) {
                Text(text = "Register")

            }
        }
    }

}