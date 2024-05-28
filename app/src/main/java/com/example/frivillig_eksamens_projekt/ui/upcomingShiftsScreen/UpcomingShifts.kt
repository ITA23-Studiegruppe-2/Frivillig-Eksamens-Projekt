package com.example.frivillig_eksamens_projekt.ui.upcomingShiftsScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.frivillig_eksamens_projekt.ui.activityScreen.ActivityScreenViewModel
import com.example.frivillig_eksamens_projekt.ui.activityScreen.ShiftCard
import com.example.frivillig_eksamens_projekt.ui.createShiftScreen.TopBarCreateShift
import com.example.frivillig_eksamens_projekt.ui.registerScreen.BackButton

@Composable
fun UpcomingShifts(
    onBackButtonClick: () -> Unit
){
    val viewModel: UpcomingShiftsViewModel = viewModel()

    Surface (
        modifier = Modifier
            .fillMaxSize(),
        color = Color(0xFFC8D5B9)
    ){
        Column (
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopBarCreateShift(onBackButtonClick = onBackButtonClick, text = "Kommende vagter")
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(6.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(30.dp))
                LazyColumn {
                    items(viewModel.upcomingActivities) { activity ->
                        println(viewModel.upcomingActivities)
                        activity.documentId?.let {
                            UpcomingShiftCard(
                                title = activity.title,
                                organization = activity.organization,
                                date = activity.date,
                                time = activity.timeStamp,
                                city = activity.city,
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