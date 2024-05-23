package com.example.frivillig_eksamens_projekt.ui.chatScreen.chatViewScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.frivillig_eksamens_projekt.Models.Conversation

@Composable
fun ConversationItem(
    conversation: Conversation,
    onResumeClick: (String) -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onResumeClick(conversation.conversationId) }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = conversation.orgName,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Seneste besked: ${conversation.lastMessage.content}",
                fontSize = 16.sp
            )
        }
    }
}
