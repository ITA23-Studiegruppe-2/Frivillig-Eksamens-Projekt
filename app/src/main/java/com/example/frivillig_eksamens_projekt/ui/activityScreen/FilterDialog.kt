package com.example.frivillig_eksamens_projekt.ui.activityScreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FilterDialog(onDismiss: () -> Unit, viewModel: ActivityScreenViewModel, listOfCities: MutableList<String>){

    val buttonColors = ButtonDefaults.buttonColors(
        containerColor = Color(0xFF364830))

    val secondaryColor = Color(0xFF364830)

    val filterButtonColors = ButtonDefaults.buttonColors(
        containerColor = Color(0xFFEBEAF3),
        contentColor = secondaryColor)

    val border = BorderStroke(2.dp, secondaryColor)

    val datePickerDialog = android.app.DatePickerDialog(
        androidx.compose.ui.platform.LocalContext.current,
        { _, year, month, dayOfMonth ->
            viewModel.selectedDate = "$dayOfMonth/${month + 1}/$year" // When user picks a date, selectedDate gets updated
            viewModel.filterActivitiesByDate(viewModel.selectedDate)
        },
        viewModel.currentYear,
        viewModel.currentMonth,
        viewModel.currentDay
    )

    AlertDialog(
        onDismissRequest = { onDismiss },
        title = { Text(text = "Filtrer i aktiviteter") },
        text = {
               Column(
                   modifier = Modifier
                       .fillMaxWidth(),
                   horizontalAlignment = Alignment.CenterHorizontally,
                   verticalArrangement = Arrangement.spacedBy(22.dp)
               ){
                   Spacer(modifier = Modifier.height(10.dp))
                   Button(
                       modifier = Modifier
                           .height(50.dp)
                           .width(180.dp),
                       colors = buttonColors,
                       onClick = { datePickerDialog.show() }) {
                       Text(
                           text = viewModel.selectedDate,
                           fontSize = 14.sp,
                           fontWeight = FontWeight.Bold
                           )
                   }
                   CitySelectDropdown(
                       isExpanded = viewModel.isExpanded,
                       onClick = {viewModel.isExpanded = it},
                       onDismiss = { viewModel.isExpanded = false},
                       value = viewModel.selectedCity,
                       onValueClick = { city -> viewModel.selectedCity = city},
                       listOfCities = listOfCities
                   )
               }
        },
        confirmButton = {
            Button(
                modifier = Modifier
                        .border(border, shape = RoundedCornerShape(22.dp)),
                colors = filterButtonColors,
                onClick = {
                    onDismiss()
                    viewModel.filterActivitiesByLocation(viewModel.selectedCity)
                }) {
                Text(text = "Filtrér", color = secondaryColor)
            }
        }
    )
}