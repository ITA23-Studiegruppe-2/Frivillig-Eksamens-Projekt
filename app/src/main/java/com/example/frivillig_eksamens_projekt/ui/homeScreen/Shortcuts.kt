package com.example.frivillig_eksamens_projekt.ui.homeScreen

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 *
 *
 * @author Christine Tofft
 *
 */
@Composable
fun Shortcut(
    onClick: () -> Unit,
    label: String
) {
    val secondaryColor = Color(0xFF364830)

    Card(
        modifier = Modifier
            .padding(vertical = 6.dp, horizontal = 22.dp)
            .height(50.dp)
            .width(350.dp)
            .clickable(onClick = onClick)
            .border(
                width = 2.dp,
                color = secondaryColor,
                shape = RoundedCornerShape(8.dp)),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(Color.White)
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text(text = label, fontSize = 18.sp, color = secondaryColor)
            Icon(imageVector = Icons.Outlined.KeyboardArrowRight,
                contentDescription = "Arrow",
                modifier = Modifier
                    .size(30.dp),
                tint = secondaryColor)
        }

    }
}
