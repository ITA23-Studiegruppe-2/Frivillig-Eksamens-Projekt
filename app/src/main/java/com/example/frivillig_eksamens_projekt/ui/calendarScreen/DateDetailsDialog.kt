package com.example.frivillig_eksamens_projekt.ui.calendarScreen

import androidx.compose.material.Button
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import androidx.compose.material.ButtonColors
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults

@Composable
fun DateDetailsDialog(
    date: LocalDate?,
    onDismiss: () -> Unit
) {
    val buttonColors = ButtonDefaults.buttonColors(
        containerColor = Color(0xFF364830)
    )

    if (date != null) {
        AlertDialog(
            onDismissRequest = { onDismiss },
            title = {
                    Text(text = date.format(DateTimeFormatter.ofPattern("dd MMM yyyy")))
            },
            text = {
                   Text("Vagt detaljer:")
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
}