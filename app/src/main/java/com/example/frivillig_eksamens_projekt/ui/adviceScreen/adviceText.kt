package com.example.frivillig_eksamens_projekt.ui.adviceScreen

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun AdviceText(
    title: String,
    text: String
){
    Text(
        text = title,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp
    )
    Row {
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = text,
            fontSize = 14.sp)
    }
    Spacer(modifier = Modifier.height(6.dp))

}