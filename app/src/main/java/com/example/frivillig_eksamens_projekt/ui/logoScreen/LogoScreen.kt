package com.example.frivillig_eksamens_projekt.ui.logoScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.frivillig_eksamens_projekt.R
import kotlinx.coroutines.delay

@Composable
fun LogoScreen(navController: NavController) {

    val logo: Painter = painterResource(id = R.drawable.volunify_logo)

    // Effect to delay the logo screen
    LaunchedEffect(key1 = true) {
        delay(1200)
        navController.navigate("start_screen") {
            // Remove logoScreen from backstack
            popUpTo("logoScreen") { inclusive = true }
        }
    }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFC8D5B9)
    ) {
        Box (
            modifier = Modifier,
            contentAlignment = Alignment.Center
        ){
            Image(
                painter = logo,
                contentDescription = "Volunify logo",
                modifier = Modifier
                    .width(500.dp)
                    .height(225.dp)
            )
        }
    }
}