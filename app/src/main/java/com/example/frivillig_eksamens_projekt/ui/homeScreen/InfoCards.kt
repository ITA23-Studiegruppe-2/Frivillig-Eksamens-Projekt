package com.example.frivillig_eksamens_projekt.ui.homeScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
/**
 *
 *
 * @author Christine Tofft
 *
 */
@Composable
fun InfoCards(
    label: String,
    icon: Painter,
    onClick: () -> Unit
){
    val secondaryColor = Color(0xFF364830)
    Card(
        modifier = Modifier
            .padding(8.dp)
            .height(120.dp)
            .width(180.dp)
            .clickable(onClick = onClick)
            .border(
                width = 2.dp,
                color = secondaryColor,
                shape = RoundedCornerShape(8.dp)),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(Color.White)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(painter = icon,
                contentDescription = label,
                modifier = Modifier
                    .size(40.dp),
                tint = secondaryColor)
            Spacer(modifier = Modifier
                .height(8.dp))
            Text(text = label, color = secondaryColor)

        }

        }
}

