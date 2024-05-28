package com.example.frivillig_eksamens_projekt.ui.OrgAllActivities.ListOfUsersAppliedToActivity

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.frivillig_eksamens_projekt.ui.createShiftScreen.TopBarCreateShift

@Composable
fun ListOfUsersApplied(
    activityId: String,
    onBackButtonClick: () -> Unit
) {

    //Send the activityId to the viewmodel
    val listOfUsersAppliedViewmodel: ListOfUsersAppliedViewmodel =
        ListOfUsersAppliedViewmodel(activityId)


    Box(
        modifier = Modifier
            .background(Color(0xFFC8D5B9))
            .fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopBarCreateShift(
                onBackButtonClick = onBackButtonClick,
                text = "Vælg frivillige"
            )
            Spacer(modifier = Modifier.height(20.dp))
            LazyColumn {
                items(listOfUsersAppliedViewmodel.listOFAllUsersApplied) { user ->

                    if (listOfUsersAppliedViewmodel.listOfUsersApproved.contains(user.userUID)) {
                        UserInformationCard(user = user, true, activityId)
                    } else {
                        UserInformationCard(user = user, false, activityId)
                    }
                }
            }
            Button(
                modifier = Modifier
                    .width(220.dp)
                    .height(80.dp)
                    .padding(12.dp),
                colors = ButtonDefaults.buttonColors(Color(0xFF364830)),
                onClick = {
                    listOfUsersAppliedViewmodel.addOrRemoveActivityForUsers()
                    listOfUsersAppliedViewmodel.chatRoomForApprovedUsers(activityId)
                    onBackButtonClick()

                }) {
                Text(text = "Godkend frivillige", color = Color.White, fontSize = 16.sp, style = MaterialTheme.typography.labelSmall.copy(
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp))

            }
        }
    }
}