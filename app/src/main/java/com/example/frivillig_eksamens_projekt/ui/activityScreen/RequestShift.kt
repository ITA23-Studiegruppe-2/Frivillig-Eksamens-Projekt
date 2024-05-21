package com.example.frivillig_eksamens_projekt.ui.activityScreen

import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.frivillig_eksamens_projekt.repositories.ActivitiesRepository


@Composable
fun RequestShiftCheckbox(requestShiftViewModel: RequestShiftViewModel, activityID: String) {

    val isRequested = requestShiftViewModel.isRequested.value

    Checkbox(
        checked = isRequested,
        onCheckedChange = {
            requestShiftViewModel.setRequested(it)
            requestShiftViewModel.applyForActivity(activityID)
        },
        colors = CheckboxDefaults.colors(
            checkedColor = Color(0xFF364830)
        )
    )

}
