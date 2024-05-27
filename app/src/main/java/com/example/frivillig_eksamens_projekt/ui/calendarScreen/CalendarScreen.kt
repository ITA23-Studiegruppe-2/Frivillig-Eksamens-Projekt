package com.example.frivillig_eksamens_projekt.ui.calendarScreen

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.frivillig_eksamens_projekt.ui.createShiftScreen.TopBarCreateShift
import com.example.frivillig_eksamens_projekt.ui.registerScreen.BackButton
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun CalendarScreen2 (
    onBackButtonClick: () -> Unit
) {
    val secondaryColor = Color(0xFF364830)

    val daysOfWeek = listOf("M", "T", "O", "T", "F", "L", "S")
    val calendarViewModel: CalendarViewModel = viewModel()

    //Current days
    var dayFormat: String
    var monthFormat: String
    var yearFormat: String

    // Define dialog
    if (calendarViewModel.showDialog && calendarViewModel.selectedDate != null) {
        val activity = calendarViewModel.getActivityForDate(calendarViewModel.selectedDate!!)
        DateDetailsDialog(calendarViewModel.selectedDate, activity) {
            calendarViewModel.showDialog = false
            calendarViewModel.selectedDate = null
        }
    }

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
                text = "Kalender"
            )
            Spacer(modifier = Modifier.height(50.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
            ) {

                Icon(
                    imageVector = Icons.Outlined.KeyboardArrowLeft,
                    contentDescription = "Previous month",
                    modifier = Modifier
                        .size(30.dp)
                        .clickable {
                            calendarViewModel.currentMonth = calendarViewModel.currentMonth.minusMonths(1)
                        })
                Spacer(modifier = Modifier.width(8.dp))


                Text(
                    text = calendarViewModel.currentMonth.format(
                        DateTimeFormatter.ofPattern("MMM yyyy").withLocale(Locale("da", "DK"))
                    ),
                    fontSize = 22.sp
                )

                Spacer(modifier = Modifier.width(8.dp))

                Icon(
                    imageVector = Icons.Outlined.KeyboardArrowRight,
                    contentDescription = "Next month",
                    modifier = Modifier
                        .size(30.dp)
                        .clickable {
                            calendarViewModel.currentMonth = calendarViewModel.currentMonth.plusMonths(1)
                        })
            }
            Spacer(modifier = Modifier.height(32.dp))

            Box(
                modifier = Modifier
                    .background(
                        Color.White,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .width(390.dp)
                    .border(
                        width = 2.dp,
                        color = secondaryColor,
                        shape = RoundedCornerShape(8.dp),
                    )
            )
            {
                Column(
                    modifier = Modifier
                        .padding(18.dp)

                ) {
                    // Day headers
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        for (day in daysOfWeek) {
                            Text(
                                text = day,
                                modifier = Modifier.weight(1f),
                                fontSize = 22.sp,
                                fontStyle = FontStyle.Italic,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                    // Calendar days
                    val daysInMonth = calendarViewModel.currentMonth.lengthOfMonth()
                    val firstOfMonth = calendarViewModel.currentMonth.atDay(1)
                    val daysOffset = (firstOfMonth.dayOfWeek.value - 1)
                    val totalDays = daysInMonth + daysOffset

                    for (week in 0 until (totalDays / 7 + if (totalDays % 7 > 0) 1 else 0)) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.Bottom,

                            ) {
                            for (day in 0 until 7) {
                                val dateNumber = week * 7 + day - daysOffset + 1
                                if (dateNumber > 0 && dateNumber <= daysInMonth) {


                                    val dateToBeFormatted = calendarViewModel.currentMonth.atDay(dateNumber).toString()

                                    dayFormat = dateToBeFormatted.substring(8,10)
                                    monthFormat = dateToBeFormatted.substring(6,7)
                                    yearFormat = dateToBeFormatted.substring(0,4)

                                    val currentDateFormatted: String  = "$dayFormat/$monthFormat/$yearFormat"


                                    Column(
                                        modifier = Modifier
                                            .weight(1f)
                                            .clickable {
                                                calendarViewModel.selectedDate = calendarViewModel.currentMonth.atDay(dateNumber)
                                                calendarViewModel.showDialog = true
                                            },
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Text(
                                            text = dateNumber.toString(),
                                            modifier = Modifier.padding(top = 30.dp),
                                            fontSize = 19.sp,
                                            textAlign = TextAlign.Center,
                                        )

                                        calendarViewModel.userActivities.forEach { activity ->

                                            if (activity.date == currentDateFormatted) {
                                                Box(modifier = Modifier
                                                    .background(Color.Black)
                                                    .width(26.dp)
                                                    .height(1.dp)
                                                    )

                                            }
                                        }
                                    }

                                } else {
                                    Spacer(modifier = Modifier.weight(1f))
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
