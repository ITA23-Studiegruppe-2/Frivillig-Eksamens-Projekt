package com.example.frivillig_eksamens_projekt.ui.badgesScreen

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
    val fontStyle = MaterialTheme.typography.labelSmall.copy(
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp)

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(text = title, style = MaterialTheme.typography.labelSmall.copy(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 24.sp))
        },
        text = {
            Text(badgeDescription, style = fontStyle)
        },
        confirmButton = {
            Button(
                modifier = Modifier,
                colors = buttonColors,
                onClick = { onDismiss() }) {
                Text(text = "Luk", color = Color.White, style = fontStyle)
            }
        })
}