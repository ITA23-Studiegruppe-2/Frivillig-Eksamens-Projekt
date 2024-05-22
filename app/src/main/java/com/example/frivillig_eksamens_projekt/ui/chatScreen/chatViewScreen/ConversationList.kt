import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.frivillig_eksamens_projekt.ui.activityScreen.TopBar
import com.example.frivillig_eksamens_projekt.ui.chatScreen.chatViewScreen.ConversationItem

@Composable
fun ConversationList(
    viewModel: ConversationViewModel = viewModel(),
    onCreateClick: () -> Unit,
    onResumeClick: (String) -> Unit
) {
    val conversations = viewModel.conversations

    TopBar()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = viewModel.backgroundColor
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Dine samtaler",
                fontSize = 28.sp,
                modifier = Modifier.padding(vertical = 16.dp)
            )

            LaunchedEffect(viewModel.currentUserId) {
                viewModel.currentUserId?.let {
                    viewModel.fetchMessages(it)
                }
            }

            Spacer(modifier = Modifier.height(30.dp))
            LazyColumn {
                items(conversations) { conversation ->
                    ConversationItem(
                        conversation = conversation,
                        onResumeClick = { conversationId -> onResumeClick(conversationId)
                        }
                    )
                }
            }
        }
    }
    CustomFloatingActionButton(onCreateClick = onCreateClick)
}
