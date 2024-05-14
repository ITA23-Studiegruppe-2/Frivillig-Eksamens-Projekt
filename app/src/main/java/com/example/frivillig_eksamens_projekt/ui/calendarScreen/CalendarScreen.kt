package com.example.frivillig_eksamens_projekt.ui.calendarScreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.frivillig_eksamens_projekt.ui.registerScreen.BackButton
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.TextStyle
import java.time.temporal.TemporalAdjuster
import java.time.temporal.TemporalAdjusters
import java.time.temporal.WeekFields
import java.util.Locale

@Composable
fun CalendarScreen2 (
    navController: NavController
) {
    val secondaryColor = Color(0xFF364830)

    Surface (
        modifier = Modifier
            .fillMaxSize(),
        color = Color(0xFFC8D5B9)
    ){
        Column (
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .height(70.dp)
                    .width(390.dp)
                    .background(color = Color.White, shape = RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                BackButton(onClick = { navController.popBackStack() })
                androidx.compose.material3.Text(
                    text = "Kalender",
                    fontSize = 28.sp,
                    color = secondaryColor
                )
            }

        }
    }
}







/*
fun CalendarScreen2(shifts: Map<LocalDate, List<Shift>>) {
    val currentMonth = remember {mutableStateOf(YearMonth.now())}
    val selectedDate = remember {mutableStateOf<LocalDate?>(null)}

    Column {
        Calendar(month = currentMonth.value, onDayClicked = {date ->
            selectedDate.value = date
        }, shifts = shifts)
        selectedDate.value?.let { date ->
            AlertDialog(
                onDismissRequest = { selectedDate.value = null },
                title = { Text(text = "Vagter for $date")},
                text = { Text(shifts[date]?.joinToString(separator = "\n") { it.description } ?: "Ingen vagter")},
                confirmButton = {
                    Button(onClick = { selectedDate.value = null }) {
                        Text(text = "OK")
                    }
                }
            )
        }
    }
}

@Composable
fun Calendar(month: YearMonth, onDayClicked: (LocalDate) -> Unit, shifts: Map<LocalDate, List<Shift>>) {

    val firstDayOfMonth = month.atDay(1)
    val firstDayOfCalendar = firstDayOfMonth.with(TemporalAdjusters.previousOrSame(java.time.DayOfWeek.MONDAY))

    var currentDay = firstDayOfCalendar
    val lastDayOfCalendar = month.atEndOfMonth().with(TemporalAdjusters.nextOrSame(java.time.DayOfWeek.SUNDAY))

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            listOf("M", "T", "O", "T", "F", "L", "S").forEach { day -> 
                Text(
                    text = day,
                    modifier = Modifier
                        .padding(8.dp),
                    fontSize = 12.sp)
            }
        }
        while (currentDay <= lastDayOfCalendar) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                for (i in 1..7) {
                    if (currentDay.month == month.month) {
                        DayView(
                            date = currentDay,
                            shifts = shifts,
                            onDayClicked = onDayClicked)
                        currentDay = currentDay.plusDays(1)
                    } else {
                        Text(text = "", modifier = Modifier
                            .weight(1f)
                            .padding(8.dp))
                        currentDay = currentDay.plusDays(1)
                    }
                }
            }
        }
    }
}

@Composable
fun DayView(date: LocalDate,
            shifts: Map<LocalDate, List<Shift>>,
            onDayClicked: (LocalDate) -> Unit) {
    Box(modifier = Modifier
        .padding(4.dp)
        .clickable { onDayClicked(date) }
        .border(BorderStroke(1.dp, Color.Black), shape = CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Text(text = date.dayOfMonth.toString())
        if(shifts.containsKey(date)) {
            Box(
                modifier = Modifier
                    .size(5.dp)
                    .background(Color.Red, shape = CircleShape)
                    .align(Alignment.BottomCenter)
            )
        }
    }
}
*/
