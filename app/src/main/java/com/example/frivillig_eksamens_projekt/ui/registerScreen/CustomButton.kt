package com.example.frivillig_eksamens_projekt.ui.registerScreen

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomButton(text: String) {
    val buttonColors = ButtonDefaults.buttonColors(
        containerColor = Color(0xFF364830)
    )
    Button(
        onClick = { },
        colors = buttonColors,
        modifier = Modifier
            .width(180.dp)
            .height(60.dp)

    ) {
        Text(text = text, fontSize = 18.sp)
    }
}