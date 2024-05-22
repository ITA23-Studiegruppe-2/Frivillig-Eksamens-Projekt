package com.example.frivillig_eksamens_projekt.ui.activityScreen

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
import com.example.frivillig_eksamens_projekt.ui.createShiftScreen.TopBarCreateShift

@Composable
fun ActivityScreen(
    onBackButtonClick: () -> Unit
) {

    val viewModel = ActivityScreenViewModel()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = viewModel.backgroundColor
    ) {
        Column {
            TopBarCreateShift(onBackButtonClick = onBackButtonClick, text = "Ledige vagter")
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(6.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SearchBar(
                    searchBarValue = viewModel.searchBar,
                    onValueChange = { viewModel.searchBar = it }
                )
                Row {
                    Button(onClick = { viewModel.searchForActivitiesByTitle() }) {
                        Text(text = "Search")
                    }
                    Button(onClick = { viewModel.getActivities() }) {
                        Text(text = "Reset")
                    }
                }

                LazyColumn {
                    items(viewModel.listOfActivities) { activity ->
                        activity.documentId?.let {
                            ShiftCard(
                                title = activity.title,
                                organization = activity.organization,
                                date = activity.date,
                                time = activity.timeStamp,
                                activityID = it,
                                listOfUsers = activity.listOfUsersApplied,
                                description = activity.description,
                                location = activity.location
                            )
                        }
                    }
                }
            }
        }
    }
}