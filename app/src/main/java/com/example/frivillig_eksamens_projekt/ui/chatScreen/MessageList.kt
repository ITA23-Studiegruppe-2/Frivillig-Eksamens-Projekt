package com.example.frivillig_eksamens_projekt.ui.chatScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.frivillig_eksamens_projekt.DTO.Message


// Opretter en liste af beskeder
@Composable
fun MessageList(messages: List<Message>) {
    Column {
        messages.forEach { message ->
            Text(text = message.message)
            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}

