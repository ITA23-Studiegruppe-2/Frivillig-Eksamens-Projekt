
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.frivillig_eksamens_projekt.Models.Message


@Composable
fun MessageBubble(
    message: Message,
    isOwnMessage: Boolean) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp),
        horizontalAlignment = if (isOwnMessage) Alignment.End else Alignment.Start
    ) {

        Text(
            text = message.senderName,
            fontSize = 12.sp,
            color = Color.Black,
            modifier = Modifier.align(
                if (isOwnMessage) Alignment.End else Alignment.Start
            )

        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = if (isOwnMessage) Arrangement.End else Arrangement.Start
        ) {
            Surface(
                shape = RoundedCornerShape(12.dp),
                color = if (isOwnMessage) Color(0xFF364830) else Color.White,
                modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
            ) {
                Text(
                    text = message.content,
                    modifier = Modifier.padding(all = 10.dp),
                    color = if (isOwnMessage) Color.White else Color(0xFF364830)
                )
            }
        }
    }
}
