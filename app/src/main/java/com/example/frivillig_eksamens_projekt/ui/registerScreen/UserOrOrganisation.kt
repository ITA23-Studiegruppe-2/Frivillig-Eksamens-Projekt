package com.example.frivillig_eksamens_projekt.ui.registerScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.frivillig_eksamens_projekt.services.AccountService

@Composable
fun UserOrOrganisation (navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        //Titel & undertitel
        Text(text = "Opret dig her", fontSize = 28.sp)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = "Hvordan skal du bruge Volunify")


        Spacer(modifier = Modifier.height(20.dp))


        Button(onClick = { navController.navigate(" ") }) {
            Text(text = "Organisation")
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = "Eller")
        Spacer(modifier = Modifier.height(4.dp))

        Button(onClick = { navController.navigate(" ") }) {
            Text(text = "Bruger")
        }

    }

}