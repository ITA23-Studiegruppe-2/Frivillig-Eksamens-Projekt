
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
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.MaterialTheme
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
import com.example.frivillig_eksamens_projekt.ui.createShiftScreen.TopBarCreateShift

@Composable
fun GroupChatScreen(
    conversationId: String,
    activityId: String,
    organizationName: String,
    onBackButtonClick: () -> Unit
) {
    val viewModel: OrgChatViewModel = OrgChatViewModel(conversationId, activityId)
    val messages by viewModel.messages.collectAsState() // Convert "StateFlow" to a compose-friendly "state". Listens to new messages, and updates the UI automatic.
    var messageText by remember { mutableStateOf("") }
    val orgId by viewModel::orgId
    val fontStyle = MaterialTheme.typography.labelSmall.copy(
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp)




    // Listen for changes in conversationId, and start again
    LaunchedEffect (conversationId) {
        viewModel.initializeChat(conversationId, activityId)
    }

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(viewModel.backgroundColor)
        ) {
            TopBarCreateShift(onBackButtonClick = onBackButtonClick, text = "$organizationName")


            Spacer(modifier = Modifier.height(20.dp))

            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                items(messages) { message ->
                    MessageBubble(message = message, isOwnMessage = message.senderId == viewModel.currentUserId)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = messageText,
                    onValueChange = { messageText = it },
                    label = { Text("Besked", style = fontStyle)},
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp)
                        .padding(4.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        textColor = viewModel.secondaryColor,
                        backgroundColor = Color.White,
                    )
                )
                Button(
                    modifier = Modifier.padding(5.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = viewModel.secondaryColor,
                        contentColor = Color.White,
                    ),
                    onClick = {
                        viewModel.sendGroupMessage(activityId, messageText, orgId)
                        messageText = ""
                    }
                ) {
                    Text("Send", style = fontStyle)
                }
            }
        }
    }
}

