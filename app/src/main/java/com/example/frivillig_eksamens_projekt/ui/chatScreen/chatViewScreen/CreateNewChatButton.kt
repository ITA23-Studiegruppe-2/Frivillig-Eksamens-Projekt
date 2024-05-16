
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CustomFloatingActionButton(
    onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp)
    ) {
        FloatingActionButton(
            onClick = onClick,
            modifier = Modifier
                .align(Alignment.TopEnd),
            content = {

                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Ny Besked",
                    tint = Color.Black
                )
            }
        )
    }
}
