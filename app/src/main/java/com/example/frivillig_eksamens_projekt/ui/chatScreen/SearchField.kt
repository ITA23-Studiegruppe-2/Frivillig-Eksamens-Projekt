package com.example.frivillig_eksamens_projekt.ui.chatScreen

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


// Komponent til visning af et søgefelt med et tekstfelt og en knap.
// Den accepterer den aktuelle søgequery "searchQuery" og en funktion "onSearchQueryChange"
// til at opdatere søgequery, når teksten i tekstfeltet ændres.
@Composable
fun SearchField(searchQuery: String, onSearchQueryChange: (String) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        TextField(
            value = searchQuery,
            onValueChange = { query -> onSearchQueryChange(query) }, // Lytter til ændringer i søgeteksten
            label = { Text("Søg efter organisationer") },
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Button(
            onClick = {
                // Udfør søgning
            }
        ) {
            Text(text = "Søg")
        }
    }
}