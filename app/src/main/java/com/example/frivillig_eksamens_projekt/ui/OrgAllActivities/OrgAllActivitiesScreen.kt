package com.example.frivillig_eksamens_projekt.ui.OrgAllActivities

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.frivillig_eksamens_projekt.Models.Activity
import com.example.frivillig_eksamens_projekt.ui.createShiftScreen.TopBarCreateShift

@Composable
fun OrgAllActivitiesScreen(
    onBackButtonClick: () -> Unit,
    onActivityListClick: (String) -> Unit
) {

    val viewmodel: OrgAllActivitiesViewmodel = viewModel()



    Box(modifier = Modifier
        .fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {

            TopBarCreateShift(
                onBackButtonClick = onBackButtonClick,
                text = "Mine Aktiviteter"
            )
            LazyColumn(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(viewmodel.listOfActivities) {activity ->
                    ActivityCardAdmin(
                        activity = activity,
                        counter = 1,
                        onClick = { activity.documentId?.let { onActivityListClick(it) } }
                    )
                }
            }
        }
    }
}


@Composable
@Preview
fun showdat() {
    OrgAllActivitiesScreen({},{})
}