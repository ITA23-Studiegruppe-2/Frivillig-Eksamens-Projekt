package com.example.frivillig_eksamens_projekt.ui.badgesScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.frivillig_eksamens_projekt.R

@Composable
fun Badges(
    label: String,
    icon: Painter,
    onClick: () -> Unit
){
    Row(
        modifier = Modifier
            .padding(12.dp)
    ) {
        Image(painter = icon, contentDescription = label, Modifier.size(60.dp))
    }

}