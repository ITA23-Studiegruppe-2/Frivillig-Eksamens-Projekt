package com.example.frivillig_eksamens_projekt.ui.upcomingShiftsScreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material.icons.outlined.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.frivillig_eksamens_projekt.ui.activityScreen.RequestShiftCheckbox
import com.example.frivillig_eksamens_projekt.ui.activityScreen.RequestShiftViewModel

/**
 *
 *
 * @author Christine Tofft
 *
 */


@Composable
fun UpcomingShiftCard(
    description: String,
    location: String,
    city: String,
    title: String,
    organization: String,
    date: String,
    time: String
) {

    val viewModel: RequestShiftViewModel = RequestShiftViewModel()
    val fontStyle = MaterialTheme.typography.labelSmall.copy(
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(Color.White),
        border = BorderStroke(1.dp, Color.Black)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(text = title, fontSize = 18.sp)
                Text(text = organization, fontSize = 14.sp)
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Column(
                    modifier = Modifier,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = date, fontSize = 14.sp)
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(text = time, fontSize = 14.sp)
                }
                Spacer(modifier = Modifier.width(40.dp))
                Icon(
                    //Change Icon for dropdown when toggled
                    imageVector =
                    if (viewModel.isExpanded) {
                        Icons.Outlined.KeyboardArrowUp
                    } else {
                        Icons.Outlined.KeyboardArrowDown
                    },
                    contentDescription = "Toggle Dropdown",
                    modifier = Modifier
                        .size(40.dp)
                        .clickable {
                            viewModel.isExpanded = !viewModel.isExpanded
                        },
                    tint = Color(0xFF364830)
                )
            }
        }

        // Dropdown menu that appears when the icon is clicked
        DropdownMenu(
            expanded = viewModel.isExpanded,
            onDismissRequest = { viewModel.isExpanded = false },
            modifier = Modifier.width(382.dp)
                .border(1.5.dp, color = Color(0xFF364830), shape = RoundedCornerShape(8.dp))
                .background(Color.White)
        ) {

            DropdownMenuItem(
                text = {
                    Column {
                        Text(
                            text = description,
                            fontSize = 14.sp,
                            modifier = Modifier.padding(10.dp),
                            style = fontStyle
                        )
                        Text(
                            text = "Adresse: $location, $city",
                            fontSize = 14.sp,
                            modifier = Modifier.padding(10.dp),
                            style = fontStyle
                        )
                    }
                },
                onClick = { viewModel.isExpanded = false }
            )
        }
    }
}