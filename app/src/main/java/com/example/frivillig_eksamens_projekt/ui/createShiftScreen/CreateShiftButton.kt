package com.example.frivillig_eksamens_projekt.ui.createShiftScreen

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun CreateShiftButton(
    onClick: () -> Unit
) {
    Button(onClick = onClick) {
        Text(text = "Opret vagt")
    }
}