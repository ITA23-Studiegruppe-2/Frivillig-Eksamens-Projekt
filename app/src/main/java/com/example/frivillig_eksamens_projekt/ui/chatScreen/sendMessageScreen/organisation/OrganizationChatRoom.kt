import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.frivillig_eksamens_projekt.ui.chatScreen.sendMessageScreen.organisation.OrgChatViewModel
import com.example.frivillig_eksamens_projekt.ui.registerScreen.BackButton

@Composable
fun GroupChatScreen(
    conversationId: String,
    activityId: String,
    organizationName: String,
    onBackButtonClick: () -> Unit
) {
    val viewModel: OrgChatViewModel = OrgChatViewModel(conversationId)
    // val viewModel: OrgChatViewModel = viewModel()
    val messages by viewModel.messages.collectAsState()
    var messageText by remember { mutableStateOf("") }
    val orgId = viewModel.currentUserId
    val secondaryColor = Color(0xFF364830)

    LaunchedEffect(conversationId) {
        viewModel.loadMessages(conversationId)
        viewModel.listenToMessages(conversationId) // Ensure real-time updates
        viewModel.initializeChatForApprovedUsers(activityId, orgId)
    }

    Surface (
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFC8D5B9))
    ) {



    // Building the UI layout for the group chat screen
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Box(
            modifier = Modifier
                .padding(8.dp)
                .height(70.dp)
                .width(390.dp)
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(8.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            BackButton(onClick = onBackButtonClick)
            androidx.compose.material3.Text(
                text = organizationName,
                fontSize = 28.sp,
                color = secondaryColor,
                fontWeight = FontWeight.Bold
            )
        }


        // LazyColumn to display messages efficiently
        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(messages) { message ->
                // MessageBubble to display each message with sender identification
                MessageBubble(message = message, isOwnMessage = message.senderId == viewModel.currentUserId)
            }
        }

        Spacer(modifier = Modifier.height(16.dp)) // Adds space between messages and input

        // Row for input and send button
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
                )
            )
            Button(
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = secondaryColor,
                    contentColor = Color.White
                ),
                onClick = {
                    // Send message using ViewModel and clear text field
                    viewModel.sendGroupMessage(activityId, messageText, orgId)
                    messageText = ""
                }
            ) {
                Text("Send")
            }
        }
    }
    }
}
