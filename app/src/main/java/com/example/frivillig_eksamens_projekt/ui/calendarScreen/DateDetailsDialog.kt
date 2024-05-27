package com.example.frivillig_eksamens_projekt.ui.calendarScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.frivillig_eksamens_projekt.Models.Activity
import java.util.Locale

@Composable
fun DateDetailsDialog(
    date: LocalDate?,
    activity: Activity?,
    onDismiss: () -> Unit
) {
    val buttonColors = ButtonDefaults.buttonColors(
        containerColor = Color(0xFF364830)
    )

    if (date != null) {
        AlertDialog(
            onDismissRequest = { onDismiss() },
            title = {
                    Text(text = date.format(DateTimeFormatter.ofPattern("dd MMM yyyy").withLocale(Locale("da", "DK"))))
            },
            text = {
                if (activity != null) {
                        Column {
                            Text("Vagt detaljer:",
                                fontSize = 15.sp,
                                textDecoration = TextDecoration.Underline,
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier
                                    .padding(bottom = 4.dp))
                            Text(text = "Du har en vagt som ${activity.title} hos ${activity.organization}. Din vagt er klokken: ${activity.timeStamp}.")
                            Text(text = "Du kan finde flere detaljer under dine kommende vagter.",
                                modifier = Modifier
                                    .padding(top = 8.dp))
                        }
                    } else {
                        Text(text = "Du har ikke nogen vagter denne dag." )
                    }
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