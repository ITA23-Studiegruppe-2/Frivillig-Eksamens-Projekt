package com.example.frivillig_eksamens_projekt.ui.chatScreen.chatViewScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.frivillig_eksamens_projekt.Models.Conversation
/**
 *
 * @author Lucas Jacobsen
 *
 */
@Composable
fun ConversationItem(
    conversation: Conversation,
    onResumeClick: (String, String) -> Unit,
    onClick: () -> Unit
) {
    Surface(
        color = Color.White,
        modifier = Modifier
            .padding(8.dp)
            .clickable { onResumeClick(conversation.conversationId, conversation.organizationName) }
    ) {
        Box(
            modifier = Modifier
                .height(80.dp)
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = conversation.organizationName,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Text(
                        text = "${conversation.lastMessage.content}",
                        fontSize = 14.sp,
                        color = Color.Gray,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = "Arrow Forward",
                    tint = Color.Gray,
                    modifier = Modifier.height(24.dp)
                )
            }
        }
    }
}
