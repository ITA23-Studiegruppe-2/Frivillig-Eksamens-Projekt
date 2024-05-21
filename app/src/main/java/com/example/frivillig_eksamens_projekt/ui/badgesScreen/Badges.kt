package com.example.frivillig_eksamens_projekt.ui.badgesScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.frivillig_eksamens_projekt.Models.Badge
import com.example.frivillig_eksamens_projekt.R

@Composable
fun BadgeIcon(badge: Badge) {

    val context = LocalContext.current
    val resourceName = if (badge.path.contains(".")) badge.path.substringBeforeLast('.') else badge.path
    val drawableId = context.resources.getIdentifier(resourceName, "drawable", context.packageName)


    if (drawableId != 0) {

        Image(
            painter = painterResource(id = drawableId),
            contentDescription = "Badge icon for ${badge.name}",
            modifier = Modifier.size(65.dp)
        )
    } else {
        Text(text = badge.name)
    }
}


/*
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

 */