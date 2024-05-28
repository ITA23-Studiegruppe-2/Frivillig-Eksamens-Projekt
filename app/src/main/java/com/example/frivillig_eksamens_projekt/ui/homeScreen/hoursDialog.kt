package com.example.frivillig_eksamens_projekt.ui.homeScreen

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun HoursDialog(
    onDismiss: () -> Unit,
    title: String,
    text: String
){
    val fontStyle = MaterialTheme.typography.labelSmall.copy(
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp)

    AlertDialog(
        title = {Text(text = title, style = MaterialTheme.typography.labelSmall.copy(
            fontWeight = FontWeight.SemiBold,
            fontSize = 22.sp))},
        text = { Text(text = text, style = fontStyle)},
        onDismissRequest = onDismiss,
        confirmButton = {
            Button(
            modifier = Modifier,
            colors = ButtonDefaults.buttonColors(Color(0xFF364830)),
            onClick = { onDismiss() }) {
            Text(text = "Luk", color = Color.White, style = fontStyle)
        }
        }
    )
}