package com.example.frivillig_eksamens_projekt.ui.OrgAllActivities

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.frivillig_eksamens_projekt.Models.Activity

@Composable
fun ActivityCardAdmin(
    activity: Activity,
    counter: Int,
    onClick: () -> Unit
) {

    Box(
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .border(1.5.dp, color = Color(0xFF364830), shape = RoundedCornerShape(9.dp))
            .height(100.dp)
            .clip(RoundedCornerShape(9.dp))
            .background(Color.White)
            .padding(10.dp)

    ) {
        Row {
            //Activity data
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.35f),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(text = activity.title)
                Text(text = activity.date)
                Text(text = activity.timeStamp)
            }
            // Count and choose applicants
            Column(
                modifier = Modifier
                    .fillMaxWidth(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp),
                    contentAlignment = Alignment.Center
                ) {
                        Text(text = "Frivillige ansøgere: $counter")
                }
                Box(
                    modifier = Modifier
                        .fillMaxHeight(1f)
                ) {
                    Button(
                        onClick = onClick,
                        modifier = Modifier
                            .width(150.dp)
                            .height(40.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF364830)
                        )
                    ) {
                        Text(text = "Vælg frivillige", color = Color.White)
                    }
                }

            }
        }
        
    }
}