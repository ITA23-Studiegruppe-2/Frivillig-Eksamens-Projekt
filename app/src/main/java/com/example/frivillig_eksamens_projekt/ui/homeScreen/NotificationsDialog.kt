package com.example.frivillig_eksamens_projekt.ui.homeScreen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.DialogProperties
import com.example.frivillig_eksamens_projekt.Models.Notification

@Composable
fun NotificationsDialog(
    onDismissRequest: () -> Unit,
    listOfNotifications: MutableList<Notification>,
    onMarkAsReadButtonClick: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        confirmButton = {
            Button(
                onClick = onMarkAsReadButtonClick,
                colors = ButtonDefaults.buttonColors(Color(0xFF364830))) {
                Text(text = "Markér som læst")
            }
        },
        dismissButton = {
            Button(onClick = onDismissRequest,
                colors = ButtonDefaults.buttonColors(Color(0xFF364830))) {
                Text(text = "Fortryd")
            }
        },
        title = {
            Text(text = "Notifikationer")
                },
        text = {
            if (listOfNotifications.isEmpty()) {
                Text(text = "Du har ingen notifikationer at vise :)")
            } else {
                LazyColumn {
                    items(listOfNotifications) { notification ->
                        NotificationCardForDialog(currentNotification = notification)

                    }
                }
            }
            
        },
        properties = DialogProperties(dismissOnClickOutside = true)

    )
}