import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
import com.example.frivillig_eksamens_projekt.ui.chatScreen.ChatViewModel

@Composable
fun ConversationList() {
    val viewModel: ChatViewModel = viewModel()
    val userMessages = viewModel.userMessages

    Box(modifier = Modifier.fillMaxSize()) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = viewModel.backgroundColor,
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Dine samtaler", fontSize = 28.sp)
                Spacer(modifier = Modifier.height(5.dp))

                // LaunchedEffect for at opdatere beskeder, når userId ændres
                LaunchedEffect(viewModel.userId) {
                    viewModel.userId?.let {
                        viewModel.fetchMessages(it)
                    }
                }

                // Liste af beskeder
                LazyColumn {
                    items(userMessages) { message ->
                        Text(text = message.content)
                    }
                }
            }
        }

        CustomFloatingActionButton(onClick = { /* ToDo Implementer onClick handling her */ })
    }
}
