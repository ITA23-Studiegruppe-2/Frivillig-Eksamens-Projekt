
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.frivillig_eksamens_projekt.ui.chatScreen.chatViewScreen.ConversationItem
import com.example.frivillig_eksamens_projekt.ui.createShiftScreen.TopBarCreateShift

@Composable
fun ConversationList(
    onResumeClick: (String, String) -> Unit,
    onBackButtonClick: () -> Unit

) {
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

            TopBarCreateShift(onBackButtonClick = onBackButtonClick, text = "Dine samtaler")



            // used to make sure, that when currentUserId changes,
            // messages for the new user are fetched by calling fetchMessages with the new currentUserId.
            LaunchedEffect(viewModel.currentUserId) {
                viewModel.currentUserId?.let {
                    viewModel.fetchMessages(it)
                }
            }

            Spacer(modifier = Modifier.height(30.dp))
            LazyColumn {
                items(conversations) { conversation ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        shape = RoundedCornerShape(8.dp),
                        elevation = CardDefaults.cardElevation(4.dp),
                        colors = CardDefaults.cardColors(Color.White),
                        border = BorderStroke(1.dp, Color.Black)
                    ) {
                        ConversationItem(
                            conversation = conversation,
                            onResumeClick = { Conversation, organizationName ->
                                onResumeClick(
                                    conversation.conversationId,
                                    conversation.organizationName
                                )
                            },
                            onClick = {
                                onResumeClick(
                                    conversation.conversationId,
                                    conversation.organizationName
                                )
                            }
                        )
                    }

                }
            }
        }
    }
}
