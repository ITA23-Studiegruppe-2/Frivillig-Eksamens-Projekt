
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.frivillig_eksamens_projekt.Models.Message


@Composable
fun MessageBubble(
    message: Message,
    isOwnMessage: Boolean) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = if (isOwnMessage) Arrangement.End else Arrangement.Start
    ) {
        Surface(
            shape = RoundedCornerShape(12.dp),
            color = if (isOwnMessage) Color(0xFF364830) else Color.Gray,
            modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
        ) {
            Text(
                text = message.content,
                modifier = Modifier.padding(all = 10.dp),
                color = Color.White
            )
        }
    }
}
