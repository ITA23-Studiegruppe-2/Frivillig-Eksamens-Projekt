package com.example.frivillig_eksamens_projekt.ui.navigationBar

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
/**
 *
 * @author Rasmus Planteig
 *
 *
 */
@Composable
fun NavigationButton(
    iconType: ImageVector,
    description: String,
    onClick: () -> Unit,
    currentRoute: MutableState<String>,
    route: String
) {
    //Maybe not have it here?
    val buttonColors = if (currentRoute.value == route) ButtonDefaults.elevatedButtonColors(containerColor = Color(0xFFC8D5B9)) else ButtonDefaults.elevatedButtonColors(containerColor = Color.White)
    val buttonBorder = if (currentRoute.value == route) BorderStroke(2.dp,Color.Black) else null
    ElevatedButton(
        onClick = onClick,
        colors = buttonColors,
        border = buttonBorder

    ) {
        Icon(
            imageVector = iconType,
            contentDescription = description,
            tint = Color(0xFF364830)
        )
    }
}
