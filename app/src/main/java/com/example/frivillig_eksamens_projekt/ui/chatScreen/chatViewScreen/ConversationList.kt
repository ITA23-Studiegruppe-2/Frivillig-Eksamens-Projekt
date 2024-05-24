
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.frivillig_eksamens_projekt.ui.chatScreen.chatViewScreen.ConversationItem

@Composable
fun ConversationList(
    onResumeClick: (String) -> Unit,

) {
    val secondaryColor = Color(0xFF364830)
    val viewModel: ConversationViewModel = viewModel()
    val conversations = viewModel.conversations



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
                Text(
                    text = "Dine samtaler",
                    fontSize = 28.sp,
                    color = secondaryColor
                )
            }

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
                        onResumeClick = { onResumeClick(conversation.conversationId)
                        }
                    )
                }
            }
        }
    }
}
