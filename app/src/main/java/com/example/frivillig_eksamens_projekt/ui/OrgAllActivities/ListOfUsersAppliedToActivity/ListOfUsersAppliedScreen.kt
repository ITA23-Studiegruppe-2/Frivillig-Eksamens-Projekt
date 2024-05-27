package com.example.frivillig_eksamens_projekt.ui.OrgAllActivities.ListOfUsersAppliedToActivity

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
                onClick = {
                    listOfUsersAppliedViewmodel.addOrRemoveActivityForUsers()
                    listOfUsersAppliedViewmodel.chatRoomForApprovedUsers(activityId)
                    onBackButtonClick()

                }) {
                Text(text = "Notify Users")

            }
        }
    }
}