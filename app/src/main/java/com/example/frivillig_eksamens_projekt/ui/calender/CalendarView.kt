package com.example.frivillig_eksamens_projekt.ui.calender

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter") // Undertrykker advarslen om ubrugte parametre
@OptIn(ExperimentalMaterial3Api::class) // Indikerer brug af eksperimentelle Material3-API'er

// Funktionen der styrer hvordan kalenderen ser ud
@Composable
fun CalendarScreen(
    onCalendarClick: () -> Unit,
    viewModel: CalendarViewModel) {
    val selectedDate = remember { mutableStateOf("") }

    // Opretter en dialogboks til valg af dato.
    val datePickerDialog = android.app.DatePickerDialog(
        androidx.compose.ui.platform.LocalContext.current,
        { _, year, month, dayOfMonth ->
            selectedDate.value = "$dayOfMonth/${month + 1}/$year" // Når brugeren vælger en dato, opdateres selectedDate
        },
        viewModel.currentYear,
        viewModel.currentMonth,
        viewModel.currentDay
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Din Kalender", color = Color.Black) }
            )
        },
        content = { CalendarBody(selectedDate, datePickerDialog) } // Kalenderens indhold
    )
}

@Composable
fun CalendarBody(selectedDate: MutableState<String>, datePickerDialog: android.app.DatePickerDialog) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                datePickerDialog.show()
            },
            modifier = Modifier.size(200.dp)
        ) {
            Text(
                text = "Åben din kalender her",
                color = Color.White,
                fontSize = 25.sp
            )
        }

        Spacer(modifier = Modifier.size(100.dp))

        // Viser den valgte dato
        Text(
            text = "Din valgte dato: ${selectedDate.value}",
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )
    }
}


