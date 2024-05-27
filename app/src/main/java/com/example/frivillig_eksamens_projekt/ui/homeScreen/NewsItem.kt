package com.example.frivillig_eksamens_projekt.ui.homeScreen

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NewsItem(label: String, url: String) {
    val context = LocalContext.current
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
    ) {

        Text(
            text = label,
            fontSize = 16.sp,
            modifier = Modifier.padding(8.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))

        Icon(
            imageVector = Icons.Outlined.Search,
            contentDescription = "Send user trough to new screen",
            modifier = Modifier
                .padding(end = 8.dp)
                .size(24.dp)
                .clickable {
                    // To send user to a new screen when click on icon
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    context.startActivity(intent)
                }
        )
    }
}
