
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.frivillig_eksamens_projekt.ui.chatScreen.sendMessageScreen.organisation.OrgChatViewModel


@Composable
fun GroupChatScreen(
    conversationId: String,
    activityId: String) {

    val viewModel: OrgChatViewModel = OrgChatViewModel(conversationId)
    // val viewModel: OrgChatViewModel = viewModel()
    val messages by viewModel.messages.collectAsState()
    var messageText by remember { mutableStateOf("") }
    val orgId = viewModel.currentUserId


    LaunchedEffect(conversationId) {
            viewModel.loadMessages(conversationId)
            viewModel.listenToMessages(conversationId) // Ensure real-time updates
            viewModel.initializeChatForApprovedUsers(activityId, orgId)
    }


    // Building the UI layout for the group chat screen
    Column(
        modifier = Modifier.fillMaxSize().padding(8.dp)) {
        // LazyColumn to display messages efficiently
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(messages) { message ->
                // MessageBubble to display each message with sender identification
                MessageBubble(message = message, isOwnMessage = message.senderId == viewModel.currentUserId)
            }
        }
        // Row for input and send button
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()) {

            OutlinedTextField(
                value = messageText,
                onValueChange = { messageText = it },
                label = { Text("Besked") },
                modifier = Modifier.weight(1f).padding(end = 8.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = MaterialTheme.colors.onSurface,
                    backgroundColor = MaterialTheme.colors.surface
                )
            )
            Button(
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF364830),
                    contentColor = Color.White),
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
