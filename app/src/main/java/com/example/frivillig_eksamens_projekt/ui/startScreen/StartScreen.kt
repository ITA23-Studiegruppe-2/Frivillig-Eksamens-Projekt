package com.example.frivillig_eksamens_projekt.ui.startScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.frivillig_eksamens_projekt.ui.registerScreen.BackButton
import com.example.frivillig_eksamens_projekt.ui.registerScreen.CustomButton
/**
 *
 * @author Rasmus Planteig
 * @author Christine Tofft
 * @author Anders Keller
 *
 */
@Composable
fun StartScreen(
    onLoginClick: () -> Unit,
    onRegisterClick: () -> Unit
) {

    val secondaryColor = Color(0xFF364830)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFC8D5B9)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = "Velkommen til",
            fontSize = 25.sp,
            color = secondaryColor)
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "Volunify", fontSize = 45.sp,
            fontWeight = FontWeight.SemiBold,
            letterSpacing = 0.12.em,
            color = secondaryColor)

        Spacer(modifier = Modifier.height(25.dp))

        Divider(
            color = secondaryColor,
            thickness = 2.5.dp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 50.dp)
        )

        Spacer(modifier = Modifier.height(40.dp))

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CustomButton(text = "Login",) {
                onLoginClick()
            }
            Spacer(modifier = Modifier.height(15.dp))
            Text(text = "Eller",
                color = secondaryColor,
                fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.height(15.dp))
            CustomButton(text = "Opret") {
                onRegisterClick()
            }
        }
    }
}
