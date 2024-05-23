package com.example.frivillig_eksamens_projekt.ui.createShiftScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.frivillig_eksamens_projekt.ui.registerScreen.BackButton
import com.example.frivillig_eksamens_projekt.ui_elements.theme.title

@Composable
fun TopBarCreateShift(
    onBackButtonClick: () -> Unit,
    text: String
) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .height(70.dp)
            .width(390.dp)
            .background(color = Color.White, shape = RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.Center
    ) {
        BackButton(onClick = onBackButtonClick )
        Text(
            text = text,
            fontSize = 26.sp,
            color = Color(0xFF364830),
            style = title

        )
    }
}
