package com.example.frivillig_eksamens_projekt.ui.chatScreen.sendMessageScreen

import MessageBubble
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun AddChatScreen(
    // To handle one of the Id's, that's why "?= null"
    conversationId: String? = null,
    orgId: String? = null
) {
    val viewModel: AddChatViewModel = viewModel()
    val messages by viewModel.messages.collectAsState()
    var messageText by remember { mutableStateOf("") }


    /*
    // Initialize chat based on conversationId or orgId
    LaunchedEffect(conversationId, orgId) {
        when {
            conversationId != null -> viewModel.initialize(conversationId)
            orgId != null -> viewModel.initializeWithOrgId(orgId)
        }
    }

     */


    // UI layout for the chat screen.
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(8.dp)) {
        LazyColumn(
            modifier = Modifier.weight(1f),

        ) {
            items(messages) { message ->
                MessageBubble(message = message, isOwnMessage = message.isOwn)
            }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                value = messageText,
                onValueChange = { messageText = it },
                label = { Text("Besked") },
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = MaterialTheme.colors.onSurface,
                    backgroundColor = MaterialTheme.colors.surface
                )
            )
            Button(
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF364830),
                    contentColor = Color.White),
                onClick = {
                    viewModel.addMessage(messageText)
                    messageText = ""
                }
            ) {
                Text("Send")
            }
        }
    }
}

