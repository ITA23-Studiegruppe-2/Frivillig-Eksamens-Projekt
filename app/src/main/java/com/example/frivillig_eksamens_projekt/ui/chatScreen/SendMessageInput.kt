package com.example.frivillig_eksamens_projekt.ui.chatScreen

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


// viser et indtastningsfelt og en knap til at sende beskeder i chatten.
// * Komponenten accepterer en funktion "onSendMessage" for at sende beskeder, når brugeren trykker på send-knappen.
// * Det gemmer også den aktuelle beskedtekst i et "mutableStateOf" og nulstiller den, når beskeden er sendt.

@Composable
fun SendMessageInput(onSendMessage: (String) -> Unit) {
    var messageText by remember { mutableStateOf("") }

    Row(verticalAlignment = Alignment.CenterVertically) {
        TextField(
            value = messageText,
            onValueChange = { messageText = it },
            label = { Text("Skriv din besked her") },
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Button(
            onClick = {
                onSendMessage(messageText)
                messageText = ""
            }
        ) {
            Text(text = "Send")
        }
    }
}