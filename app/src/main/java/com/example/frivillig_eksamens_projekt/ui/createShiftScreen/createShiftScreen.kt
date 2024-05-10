import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import android.widget.Toast

@Composable
fun CustomOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = Color.Black,
            backgroundColor = Color.White,
            cursorColor = Color.Black,
            focusedBorderColor = Color.Gray,
            unfocusedBorderColor = Color.Gray
        ),
        modifier = modifier.padding(4.dp),
        shape = RoundedCornerShape(8.dp)
    )
}

@Composable
fun CreateShiftScreen() {
    var jobTitle by remember { mutableStateOf("") }
    var hours by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    var contactPerson by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .background(Color(0xFFC8D5B9))
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Opret en vagt", style = MaterialTheme.typography.h3)
            Spacer(Modifier.weight(1f))
            Icon(
                imageVector = Icons.Filled.Notifications,
                contentDescription = "Notifications Bell",
                modifier = Modifier.size(45.dp)
            )
        }
        Spacer(modifier = Modifier.height(40.dp))
        CustomOutlinedTextField(
            value = jobTitle,
            onValueChange = { jobTitle = it },
            label = "Title på stilling",
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            CustomOutlinedTextField(
                value = hours,
                onValueChange = { hours = it },
                label = "Antal timer",
                modifier = Modifier.weight(1f).padding(end = 8.dp)
            )
            CustomOutlinedTextField(
                value = date,
                onValueChange = { date = it },
                label = "Vagtens dato",
                modifier = Modifier.weight(1f)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        CustomOutlinedTextField(
            value = contactPerson,
            onValueChange = { contactPerson = it },
            label = "Kontakt person",
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
        )
        CustomOutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = "Tilføj en beskrivelse",
            modifier = Modifier.fillMaxWidth().height(170.dp)
        )
        Spacer(modifier = Modifier.height(80.dp))
        Button(
            onClick = {
                Toast.makeText(context, "Vagt oprettet!", Toast.LENGTH_SHORT).show()
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF364830)),
            modifier = Modifier.fillMaxWidth().height(60.dp).padding(horizontal = 50.dp),
            shape = RoundedCornerShape(50)
        ) {
            Text("Done", color = Color.White)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CreateShiftScreen()
}
