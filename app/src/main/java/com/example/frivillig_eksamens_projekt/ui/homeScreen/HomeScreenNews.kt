package com.example.frivillig_eksamens_projekt.ui.homeScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun HomeScreenNews(
    title: String,
    news: String
) {

    val secondaryColor = Color(0xFF364830)
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
            .width(400.dp)
            .border(
                width = 2.dp,
                color = secondaryColor,
                shape = RoundedCornerShape(8.dp))
            .background(Color.White),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp)
        ) {
            Text(text = title, fontSize = 28.sp, fontWeight = FontWeight.Bold, color = secondaryColor)
            Text(text = news, fontSize = 16.sp, color = secondaryColor)
        }
    }
}