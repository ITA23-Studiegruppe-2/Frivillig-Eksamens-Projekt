package com.example.frivillig_eksamens_projekt.ui.chatScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp




@Composable
fun ChatPage(userId: String) {
    val viewModel = ChatViewModel()
    // Hent beskedliste fra Firebase Firestore
    val messages by viewModel.messages

    Column {
        // Vis beskedlisten
        MessageList(
            messages = messages
        )

        Spacer(modifier = Modifier.height(16.dp))


        Box(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.CenterHorizontally)
        ) {
            // Inputfelt til at skrive og sende nye beskeder
            SendMessageInput(onSendMessage = { message ->
                viewModel.sendMessage(userId, message)
            })
        }
    }
}
