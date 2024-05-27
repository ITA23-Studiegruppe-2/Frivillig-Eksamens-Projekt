package com.example.frivillig_eksamens_projekt.ui.badgesScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.frivillig_eksamens_projekt.Models.Badge

@Composable
fun BadgeIcon(badge: Badge, onClick: () -> Unit, size: Dp) {

    val context = LocalContext.current
    val resourceName = if (badge.path.contains(".")) badge.path.substringBeforeLast('.') else badge.path
    val drawableId = context.resources.getIdentifier(resourceName, "drawable", context.packageName)

    //Dialog
    if (drawableId != 0) {

        Image(
            painter = painterResource(id = drawableId),
            contentDescription = "Badge icon for ${badge.name}",
            modifier = Modifier
                .clickable(onClick = onClick)
                .size(size)

        )
    } else {
        Text(text = badge.name)
    }
}

