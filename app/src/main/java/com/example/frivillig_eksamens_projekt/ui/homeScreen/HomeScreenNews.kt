package com.example.frivillig_eksamens_projekt.ui.homeScreen

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun HomeScreenNews(
    viewModel: UserViewModel = viewModel()
) {
    val news = viewModel.news



    val secondaryColor = Color(0xFF364830)
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(6.dp)
            .width(400.dp)
            .border(
                width = 2.dp,
                color = secondaryColor,
                shape = RoundedCornerShape(8.dp)
            ),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp)
        ) {
            Text(
                text = "Dagens Nyhed",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = secondaryColor
            )
            news?.let {
                Text(
                    text = it.newsText,
                    fontSize = 20.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(vertical = 4.dp)
                )
            }
        }
    }
}
