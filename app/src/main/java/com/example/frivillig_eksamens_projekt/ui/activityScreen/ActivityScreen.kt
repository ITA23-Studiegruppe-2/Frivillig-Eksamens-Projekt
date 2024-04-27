package com.example.frivillig_eksamens_projekt.ui.activityScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ActivityScreen() {

    val viewModel = ActivityScreenViewModel()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = viewModel.backgroundColor
    ) {
        Column {
            TopBar()
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(6.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SearchBar()

                LazyColumn {

                    items(viewModel.listOfActivities) { activity ->
                        ShiftCard(title = activity.title, organization = activity.organization, date = activity.date, time = activity.timeStamp)
                    }
                }
            }
        }
    }
}