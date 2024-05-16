package com.example.frivillig_eksamens_projekt.ui.createShiftScreen

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TextFieldDefaults.textFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun InputFieldShift(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    width: Float,
    height: Float
)
{
    val textFieldColors = textFieldColors(
        containerColor = Color.White,
        unfocusedIndicatorColor = Color.Transparent, // Remove underline when unfocused
        focusedIndicatorColor = Color.Transparent // Remove underline when focused
    )

    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth(width)
            .fillMaxHeight(height),
        shape = RoundedCornerShape(12.dp),
        label = { Text(text = label) },
        colors = textFieldColors,
    )
}