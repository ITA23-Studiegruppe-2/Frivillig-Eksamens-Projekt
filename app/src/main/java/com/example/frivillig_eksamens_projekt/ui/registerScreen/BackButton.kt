package com.example.frivillig_eksamens_projekt.ui.registerScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun BackButton(
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.TopStart

    ) {
        Icon(
            imageVector = Icons.Outlined.KeyboardArrowLeft,
            contentDescription = "Go back button",
            modifier = Modifier
                .size(50.dp)
                .clickable { onClick()},
            tint = Color(0xFF364830),
        )
    }
}