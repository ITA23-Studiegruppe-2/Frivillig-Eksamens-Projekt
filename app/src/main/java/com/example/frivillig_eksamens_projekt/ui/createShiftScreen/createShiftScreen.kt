package com.example.frivillig_eksamens_projekt.ui.createShiftScreen


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun CreateShift(
    onBackButtonClick: () -> Unit,
    onSuccess: () -> Unit
) {

    val viewModel: CreateShiftViewModel = viewModel()

    // Date Picker - Can only be created inside of a composable function
    val datePickerDialog = android.app.DatePickerDialog(
        androidx.compose.ui.platform.LocalContext.current,
        { _, year, month, dayOfMonth ->
            viewModel.date = "$dayOfMonth/${month + 1}/$year"
        },
        viewModel.currentYear,
        viewModel.currentMonth,
        viewModel.currentDay
    )

    Box(
        modifier = Modifier
            .background(Color(0xFFC8D5B9))
            .fillMaxSize()
    ) {
        Column {
            TopBarCreateShift(
                onBackButtonClick = onBackButtonClick,
                text = "Opret vagt"
            )
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.TopCenter
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Spacer(modifier = Modifier.height(9.dp))
                    InputFieldShift(
                        label = "Titel",
                        value = viewModel.title,
                        onValueChange = {viewModel.title = it},
                        height = 0.075f
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    DateSelector(
                        onClick = { datePickerDialog.show()},
                        value = viewModel.date
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(0.8f),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        TimeSelectDropDown(
                            onClick = {viewModel.isStartExpanded = it },
                            value = viewModel.startTime,
                            isExpanded = viewModel.isStartExpanded,
                            onDismiss = {viewModel.isStartExpanded = false},
                            onValueClick = {viewModel.startTime = it }
                        )
                        TimeSelectDropDown(
                            onClick = {viewModel.isEndExpanded = it},
                            value = viewModel.endTime,
                            isExpanded = viewModel.isEndExpanded,
                            onDismiss = {viewModel.isEndExpanded = false},
                            onValueClick = {viewModel.endTime = it}
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    InputFieldShift(
                        label = "Beskrivelse",
                        value = viewModel.description,
                        onValueChange = {viewModel.description = it},
                        height = 0.35f
                    )
                    InputFieldShift(
                        label = "Adresse",
                        value = viewModel.location,
                        onValueChange = {viewModel.location = it },
                        height = 0.2f
                    )
                    InputFieldShift(
                        label = "By",
                        value = viewModel.city,
                        onValueChange = {viewModel.city = it},
                        height = 0.3f
                    )
                    Spacer(modifier = Modifier.height(18.dp))

                    CreateShiftButton(
                        onClick = {viewModel.createActivity(onSuccess = onSuccess)}
                    )
                }
            }
        }
    }
}
