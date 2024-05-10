package com.example.frivillig_eksamens_projekt.ui.chatScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.frivillig_eksamens_projekt.ui.activityScreen.SearchBar


@Composable
fun ChatScreen() {

    val viewModel = ChatViewModel()

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
                SearchBar(
                    searchBarValue = viewModel.searchBar,
                    onValueChange = { viewModel.searchBar = it },
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
                        Text(text = organization.name)

                            }
                        }
                    }
                }
            }
}
