
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MailOutline
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.frivillig_eksamens_projekt.ui.chatScreen.OrganizationViewModel
import com.example.frivillig_eksamens_projekt.ui.registerScreen.InputfieldUser


@Composable
fun ChatScreen() {
    val viewModel: OrganizationViewModel = viewModel()  // Opdateret klassenavn
    var searchBarValue by remember { mutableStateOf("") }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = viewModel.backgroundColor
    ) {
        Column {
            Text(text = "Skriv med din organisation")
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(6.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(20.dp))

                InputfieldUser(
                    label = "Søg efter organisation",
                    icon = Icons.Outlined.Search, value = searchBarValue,
                    onValueChange = {
                        searchBarValue = it
                        viewModel.searchBar = it
                    },
                )

                Row {
                    Button(onClick = { viewModel.searchOrganisationByName() }) {
                        Text(text = "Search")
                    }
                    Button(onClick = { viewModel.getOrganizations() }) {
                        Text(text = "Reset")
                    }
                }

                LazyColumn {
                    items(viewModel.listOfOrganization) { organization ->
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(text = organization.name)
                            IconButton(onClick = { /* Navigation to addChatScreen */ }) {
                                Icon(Icons.Outlined.MailOutline, contentDescription = "Chat Icon")
                            }
                        }
                    }
                }
            }
        }
    }
}
