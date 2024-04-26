package com.example.frivillig_eksamens_projekt.ui.activityScreen

import androidx.compose.material3.Checkbox
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel



@Composable
fun RequestShiftCheckbox(requestShiftViewModel: RequestShiftViewModel) {

    val isRequested = requestShiftViewModel.isRequested.value

    Checkbox(
        checked = isRequested,
        onCheckedChange = {requestShiftViewModel.setRequested(it)},
    )
}
