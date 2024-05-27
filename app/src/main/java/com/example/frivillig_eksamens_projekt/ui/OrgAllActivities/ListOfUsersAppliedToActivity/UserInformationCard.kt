package com.example.frivillig_eksamens_projekt.ui.OrgAllActivities.ListOfUsersAppliedToActivity

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.frivillig_eksamens_projekt.Models.User

@Composable
fun UserInformationCard(user: User, checkedState: Boolean, activityId: String) {

    val viewmodel: UserInformationViewmodel = viewModel(key = user.userUID)
    // Handle the initialization of the checkedState
    LaunchedEffect(key1 = user.userUID) {
        viewmodel.checked = checkedState
    }


    Box(
        modifier = Modifier
            .padding(12.dp)
    ) {

        Row(
            modifier = Modifier
                .background(Color.White)
                .padding(8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            // User Information + badges
            Column {
                Box(modifier = Modifier
                ) {
                    Column {
                        Text(text = user.fullName)
                    }
                }
                Box(
                    modifier = Modifier
                ) {
                    
                }
                // Checkbox to choose applicant
            }
            Box(modifier = Modifier)
            Checkbox(
                checked = viewmodel.checked,
                onCheckedChange = {
                    viewmodel.checked = !viewmodel.checked
                    user.userUID?.let { it -> viewmodel.approveOrRemoveUserFromApprovedList(activityId = activityId, userId = it)}

            }
            )
        }
    }
}