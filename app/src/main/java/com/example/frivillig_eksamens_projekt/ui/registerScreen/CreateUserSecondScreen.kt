package com.example.frivillig_eksamens_projekt.ui.registerScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material.icons.outlined.Place
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.frivillig_eksamens_projekt.R
import com.example.frivillig_eksamens_projekt.ui.calender.CalendarViewModel

@Composable
fun CreateUserSecondScreen(
    onSuccess: () -> Unit,
    onBackButtonClick: () -> Unit,
    viewModel: CreateUserViewModel
) {


    val calendarViewModel = CalendarViewModel()

    // Opretter en dialogboks til valg af dato.
    val datePickerDialog = android.app.DatePickerDialog(
        LocalContext.current,
        R.style.CustomDatePickerDialog,
        { _, year, month, dayOfMonth ->
            calendarViewModel.selectedDate = "$dayOfMonth/${month + 1}/$year" // Når brugeren vælger en dato, opdateres selectedDate
        },
        calendarViewModel.currentYear,
        calendarViewModel.currentMonth,
        calendarViewModel.currentDay
    )



    Surface(
        modifier = Modifier.fillMaxSize(),
        color = viewModel.backgroundColor
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            BackButton(onClick = onBackButtonClick)
            Spacer(modifier = Modifier.height(70.dp))
            Text(text = "Færdiggør din bruger", fontSize = 22.sp, color = Color(0xFF364830))

            Spacer(modifier = Modifier.height(40.dp))

            Column(
                verticalArrangement = Arrangement.spacedBy(22.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                InputfieldUser(
                    "Telefonnummer",
                    icon = Icons.Outlined.Phone,
                    value = viewModel.phoneNumber,
                    onValueChange = { viewModel.phoneNumber = it })
                InputfieldUser(
                    "Postnummer",
                    icon = Icons.Outlined.Place,
                    value = viewModel.zipCode,
                    onValueChange = { viewModel.zipCode = it })
                Row (
                    horizontalArrangement = Arrangement.spacedBy(17.dp)
                ) {
                    RegisterButton(
                        text = calendarViewModel.selectedDate,
                        icon = Icons.Outlined.DateRange,
                        onClick = {
                            datePickerDialog.show()
                        }
                    )
                    Box(
                        contentAlignment = Alignment.Center
                    ) {
                        RegisterButton(
                            text = if (viewModel.gender.isNotEmpty()) {
                                viewModel.gender
                            } else {
                                "Køn"
                            },
                            icon = Icons.Outlined.AccountCircle,
                            onClick = { viewModel.dropDownVisible = true })
                        if (viewModel.dropDownVisible) {
                            GenderDropDown(viewModel = viewModel)
                        }
                    }
                }
                
                Spacer(modifier = Modifier.height(28.dp))
                
                Text(text = viewModel.errorMessage)
                Box {
                    TermsAndConditionsCheckbox(checkboxViewModel = viewModel)
                }

                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    CustomButton(
                        text = "Tilmeld",
                        onClick = {
                            viewModel.birthDate = calendarViewModel.selectedDate
                            viewModel.registerUserToDatabase(onSuccess = onSuccess, onFail = {})

                        })
                }
            }

        }
    }
}