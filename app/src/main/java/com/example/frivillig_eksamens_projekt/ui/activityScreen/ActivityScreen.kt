package com.example.frivillig_eksamens_projekt.ui.activityScreen

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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.frivillig_eksamens_projekt.ui.createShiftScreen.TopBarCreateShift

@Composable
fun ActivityScreen(
    onBackButtonClick: () -> Unit
) {

    val viewModel = ActivityScreenViewModel()
    val fontStyle = MaterialTheme.typography.labelSmall.copy(
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp)


    Surface(
        modifier = Modifier.fillMaxSize(),
        color = viewModel.backgroundColor
    ) {

        if (viewModel.showFilterDialog){
            FilterDialog(
                onDismiss = {viewModel.showFilterDialog = false},
                viewModel = viewModel,
                listOfCities = viewModel.listOfCities
            )
        }

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
                    onValueChange = { viewModel.searchBar = it },
                    viewModel = viewModel
                )
                Row(
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        colors = ButtonDefaults.buttonColors(Color(0xFF364830)),
                        modifier = Modifier
                            .width(90.dp),
                        onClick = { viewModel.getActivities() }) {
                        Text(text = "Ryd", style = fontStyle)
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Button(
                        colors = ButtonDefaults.buttonColors(Color(0xFF364830)),
                        modifier = Modifier
                            .width(90.dp),
                        onClick = { viewModel.searchForActivitiesByTitle() }) {
                        Text(text = "Søg", style = fontStyle)
                    }
                    Spacer(modifier = Modifier.width(65.dp))
                }
                Spacer(modifier = Modifier.height(12.dp))

                LazyColumn {
                    items(viewModel.listOfActivities) { activity ->
                        if (!activity.listOfUsersApproved.contains(viewModel.currentUserUId)) {
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
}