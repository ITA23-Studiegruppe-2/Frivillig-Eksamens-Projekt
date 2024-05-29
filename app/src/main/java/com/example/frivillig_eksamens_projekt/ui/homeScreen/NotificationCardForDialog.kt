package com.example.frivillig_eksamens_projekt.ui.homeScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.frivillig_eksamens_projekt.Models.Notification
/**
 *
 * @author Rasmus Planteig
 *
 */
@Composable
fun NotificationCardForDialog(currentNotification: Notification) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .border(1.dp, Color.Black, RoundedCornerShape(8.dp))
        .padding(6.dp)
    ) {
            Column(modifier = Modifier
                .fillMaxWidth()) {
                Text(
                    text = currentNotification.title,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = currentNotification.message
                )

            }

    }
}
