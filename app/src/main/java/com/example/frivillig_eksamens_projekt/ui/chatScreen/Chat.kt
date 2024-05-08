package com.example.frivillig_eksamens_projekt.ui.chatScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ChatScreen(viewModel: ChatViewModel = viewModel()) {

    //Henter værdier fra viewModel
    val messages = viewModel.messages.value
    val searchQuery = viewModel.searchQuery.value

    Surface(color = Color(0xFFC8D5B9)) {
        Column(
            modifier = Modifier.fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = "Chat med en bruger eller organisation",
                fontSize = 28.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            //Søgefelt til at filtrere beskeder
            SearchField(searchQuery = searchQuery) { query ->
                viewModel.updateSearchQuery(query)
            }

            //Liste over beskeder
            MessageList(messages = messages)

            //Indtastningsfelt til at sende nye beskeder
            SendMessageInput { message ->
                viewModel.sendMessage(userId = "userId", message = message)
            }
        }
    }
}



