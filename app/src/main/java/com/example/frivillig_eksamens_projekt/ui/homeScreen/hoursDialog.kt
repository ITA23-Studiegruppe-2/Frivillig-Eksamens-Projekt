package com.example.frivillig_eksamens_projekt.ui.homeScreen

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
/**
 *
 *
 * @author Christine Tofft
 *
 */
@Composable
fun HoursDialog(
    onDismiss: () -> Unit,
    title: String,
    text: String
){

    AlertDialog(
        title = {Text(text = title)},
        text = { Text(text = text)},
        onDismissRequest = onDismiss,
        confirmButton = {
            Button(
            modifier = Modifier,
            colors = ButtonDefaults.buttonColors(Color(0xFF364830)),
            onClick = { onDismiss() }) {
            Text(text = "Luk", color = Color.White)
        }
        }
    )
}