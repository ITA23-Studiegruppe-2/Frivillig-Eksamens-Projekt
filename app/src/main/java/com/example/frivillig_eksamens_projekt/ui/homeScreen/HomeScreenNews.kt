package com.example.frivillig_eksamens_projekt.ui.homeScreen

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
/**
 *
 * @author Rasmus Planteig
 * @author Christine Tofft
 * @author Lucas Jacobsen
 *
 */

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
                fontSize = 19.sp,
                fontWeight = FontWeight.Bold,
                color = secondaryColor
            )
           Spacer(modifier = Modifier.height(20.dp))

            news?.let {
                Text(
                    text = it.newsText,
                    fontSize = 15.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(vertical = 4.dp)
                )
            }
        }
    }
}
