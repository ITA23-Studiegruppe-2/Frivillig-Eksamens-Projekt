package com.example.frivillig_eksamens_projekt.ui.createShiftScreen

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DateSelector(
    onClick: () -> Unit,
    value: String
) {


    Button(
        modifier = Modifier
            .fillMaxWidth(0.38f)
            .height(50.dp)
            .border(1.5.dp, Color(0xFF364830), RoundedCornerShape(23.dp))
            .shadow(4.dp, shape = RoundedCornerShape(23.dp)),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White)
    ) {
        Text(text = value, fontSize = 15.sp, color = Color(0xFF364830))
    }
}