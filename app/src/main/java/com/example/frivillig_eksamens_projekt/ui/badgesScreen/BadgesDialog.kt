package com.example.frivillig_eksamens_projekt.ui.badgesScreen

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import java.time.format.DateTimeFormatter

@Composable
fun BadgeDialog(
    onDismiss: () -> Unit,
    title: String,
    badgeDescription: String
){
    val buttonColors = ButtonDefaults.buttonColors(
        containerColor = Color(0xFF364830)
    )
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(text = title)
        },
        text = {
            Text(badgeDescription)
        },
        confirmButton = {
            Button(
                modifier = Modifier,
                colors = buttonColors,
                onClick = { onDismiss() }) {
                Text(text = "Luk", color = Color.White)
            }
        })
}