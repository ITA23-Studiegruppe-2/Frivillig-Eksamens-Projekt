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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.material3.AlertDialog
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
import androidx.navigation.NavController
import com.example.frivillig_eksamens_projekt.ui.registerScreen.BackButton
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter
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
    var currentMonth by remember { mutableStateOf(YearMonth.now()) }
    val daysOfWeek = listOf("M", "T", "O", "T", "F", "L", "S")
    var showDialog by remember { mutableStateOf(false) }
    var selectedDate by remember { mutableStateOf<LocalDate?>(null)}

    // Define dialog
    if (showDialog && selectedDate != null) {
        DateDetailsDialog(selectedDate) {
            showDialog = false
            selectedDate = null
        }
    }

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = Color(0xFFC8D5B9)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .height(70.dp)
                    .width(390.dp)
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(8.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                BackButton(onClick = { navController.popBackStack() })
                androidx.compose.material3.Text(
                    text = "Kalender",
                    fontSize = 28.sp,
                    color = secondaryColor
                )
            }
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
                            currentMonth = currentMonth.minusMonths(1)
                        })
                Spacer(modifier = Modifier.width(8.dp))


                Text(
                    text = currentMonth.format(DateTimeFormatter.ofPattern("MMM yyyy").withLocale(Locale("da", "DK"))),
                    fontSize = 22.sp
                )

                Spacer(modifier = Modifier.width(8.dp))

                Icon(
                    imageVector = Icons.Outlined.KeyboardArrowRight,
                    contentDescription = "Next month",
                    modifier = Modifier
                        .size(30.dp)
                        .clickable {
                            currentMonth = currentMonth.plusMonths(1)
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
                    val daysInMonth = currentMonth.lengthOfMonth()
                    val firstOfMonth = currentMonth.atDay(1)
                    val daysOffset = (firstOfMonth.dayOfWeek.value - 1)
                    val totalDays = daysInMonth + daysOffset

                    for (week in 0 until (totalDays / 7 + if (totalDays % 7 > 0) 1 else 0)) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            for (day in 0 until 7) {
                                val dateNumber = week * 7 + day - daysOffset + 1
                                if (dateNumber > 0 && dateNumber <= daysInMonth) {
                                    Text(
                                        text = dateNumber.toString(),
                                        modifier = Modifier
                                            .weight(1f)
                                            .clickable {
                                                selectedDate = currentMonth.atDay(dateNumber)
                                                showDialog = true
                                            }
                                            .padding(vertical = 20.dp),

                                        fontSize = 19.sp,
                                        textAlign = TextAlign.Center
                                    )
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