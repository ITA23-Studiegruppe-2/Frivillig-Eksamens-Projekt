package com.example.frivillig_eksamens_projekt.ui.chooseScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.frivillig_eksamens_projekt.ui.registerScreen.BackButton
import com.example.frivillig_eksamens_projekt.ui.registerScreen.CustomButton

@Composable
fun UserOrOrganisation (
    onSuccesUserSelection: () -> Unit,
    onSuccesOrgSelection: () -> Unit,
    onBackButtonClick: () -> Unit
    ) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFC8D5B9)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Row {
            Spacer(modifier = Modifier.width(10.dp))
            BackButton(onClick = onBackButtonClick)
        }
        Spacer(modifier = Modifier.height(220.dp))

        //Titel & undertitel
        Text(text = "Opret dig her", fontSize = 32.sp)
        Spacer(modifier = Modifier.height(15.dp))
        Text(text = "Hvordan skal du bruge Volunify?")


        Spacer(modifier = Modifier.height(25.dp))


        CustomButton(text = "Organisation",
            onClick = { onSuccesOrgSelection() })

        Spacer(modifier = Modifier.height(10.dp))

        Spacer(modifier = Modifier.height(4.dp))
        Text(text = "Eller")

        Spacer(modifier = Modifier.height(10.dp))
        CustomButton(text = "Bruger",
            onClick = { onSuccesUserSelection() })
    }
}