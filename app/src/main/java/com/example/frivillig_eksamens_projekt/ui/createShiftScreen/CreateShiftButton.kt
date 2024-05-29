package com.example.frivillig_eksamens_projekt.ui.createShiftScreen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
/**
 *
 * @author Rasmus Planteig
 * @author Christine Tofft
 *
 */
@Composable
fun CreateShiftButton(
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth(0.35f)
            .height(55.dp)
            .shadow(6.dp, shape = RoundedCornerShape(23.dp)),
        colors = ButtonDefaults.buttonColors(Color(0xFF364830))
    ) {
        Text(
            text = "Opret vagt",
            style = MaterialTheme.typography.labelSmall.copy(
                fontWeight = FontWeight.Medium,
                fontSize = 17.sp)
        )
    }
}