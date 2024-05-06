package com.example.frivillig_eksamens_projekt.ui.chooseScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.frivillig_eksamens_projekt.ui.registerScreen.CustomButton

@Composable
fun UserOrOrganisation (
    onSuccesUserSelection: () -> Unit,
    onSuccesOrgSelection: () -> Unit,
    ) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFC8D5B9)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val userOrOrgViewModel = UserOrOrgViewModel()


        //Titel & undertitel
        Text(text = "Opret dig her", fontSize = 32.sp)
        Spacer(modifier = Modifier.height(15.dp))
        Text(text = "Hvordan skal du bruge Volunify")


        Spacer(modifier = Modifier.height(45.dp))


        CustomButton(text = "Organisation",
            onClick = { onSuccesUserSelection() })

        Spacer(modifier = Modifier.height(10.dp))

        Spacer(modifier = Modifier.height(4.dp))
        Text(text = "Eller")

        Spacer(modifier = Modifier.height(10.dp))
        CustomButton(text = "Bruger",
            onClick = { onSuccesUserSelection() })
    }
}