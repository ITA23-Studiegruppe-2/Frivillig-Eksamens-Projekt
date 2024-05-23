
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MailOutline
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.frivillig_eksamens_projekt.ui.chatScreen.findOrganisation.OrganizationViewModel
import com.example.frivillig_eksamens_projekt.ui.registerScreen.InputfieldUser


@Composable
fun ChatScreen(
    onNewChatClick: (String) -> Unit
) {
    val viewModel: OrganizationViewModel = viewModel()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = viewModel.backgroundColor
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Skriv med din organisation",
                fontSize = 28.sp,
                modifier = Modifier.padding(bottom = 30.dp)
            )

            InputfieldUser(
                label = "Søg efter organisation",
                icon = Icons.Outlined.Search,
                value = viewModel.searchBar,
                onValueChange = { newValue ->
                    viewModel.updateSearchBar(newValue)
                },
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = { viewModel.searchOrganisationByName() },
                    modifier = Modifier.weight(1f)
                ) {
                    Text(text = "Search")
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    onClick = {
                        viewModel.getOrganizations()
                        viewModel.updateSearchBar("")
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text(text = "Reset")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn {
                items(viewModel.listOfOrganization) { organization ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = organization.name,
                            modifier = Modifier.weight(1f)
                        )
                        IconButton(
                            onClick = { organization.orgID?.let(onNewChatClick) } // Send orgId to navigation
                        ) {
                            Icon(Icons.Outlined.MailOutline, contentDescription = "Chat Icon")
                        }
                    }
                }
            }
        }
    }
}

