package com.example.frivillig_eksamens_projekt.ui.createShiftScreen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun DateSelector(
    onClick: () -> Unit,
    value: String
) {
    Button(
        modifier = Modifier
            .fillMaxWidth(0.8f),
        onClick = onClick
    ) {
        Text(text = value)
    }
}