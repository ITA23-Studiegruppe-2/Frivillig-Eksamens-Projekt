package com.example.frivillig_eksamens_projekt.ui.OrgAllActivities.ListOfUsersAppliedToActivity

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.frivillig_eksamens_projekt.Models.User
import com.example.frivillig_eksamens_projekt.ui.badgesScreen.BadgeIcon

@Composable
fun UserInformationCard(user: User, checkedState: Boolean, activityId: String) {

    val viewmodel: UserInformationViewmodel = viewModel(key = user.userUID)
    // Handle the initialization of the checkedState
    LaunchedEffect(key1 = user.userUID) {
        viewmodel.checked = checkedState
        user.userUID?.let { viewmodel.getUserBadges(userId = it) }
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
                   LazyRow {
                       items(viewmodel.listOfUsersBadges) { badge ->
                           BadgeIcon(badge = badge) {}
                       }
                   }
                }

            }
            // Checkbox to choose applicant
            Box(modifier = Modifier
                .border(2.dp, color = Color.Black, shape = RoundedCornerShape(9.dp))
            )
            Checkbox(
                checked = viewmodel.checked,
                onCheckedChange = {
                    viewmodel.checked = !viewmodel.checked
                    user.userUID?.let { userUId -> viewmodel.approveOrRemoveUserFromApprovedList(activityId = activityId, userId = userUId)}
            }
            )
        }
    }
}