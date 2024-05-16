package com.example.frivillig_eksamens_projekt.ui.createShiftScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.frivillig_eksamens_projekt.ui.registerScreen.BackButton

@Composable
fun CreateShift(
    navController: NavController,
    viewModel: CreateShiftViewModel) {

    val secondaryColor = Color(0xFF364830)

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = Color(0xFFC8D5B9)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .height(70.dp)
                    .width(390.dp)
                    .background(color = Color.White, shape = RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                BackButton(onClick = { navController.popBackStack() })
                Text(text = "Opret vagt", fontSize = 28.sp, color = secondaryColor)
            }

            Spacer(modifier = Modifier.height(40.dp))

            Column (
                modifier = Modifier
                    .padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(28.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                InputFieldShift(label = "Titel",
                    value = viewModel.title,
                    onValueChange = {viewModel.title = it},
                    width = 0.9f,
                    height = 0.04f)
                Row (
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    Spacer(modifier = Modifier.width(3.dp))
                    InputFieldShift(
                        label = "Antal timer",
                        value = viewModel.hours,
                        onValueChange = {viewModel.hours = it},
                        width = 0.4f,
                        height = 0.04f)
                    InputFieldShift(
                        label = "Vagtens dato",
                        value = viewModel.date,
                        onValueChange = {viewModel.date = it},
                        width = 0.8f,
                        height = 0.04f)
                }
                InputFieldShift(
                    label = "Kontakt persons e-mail",
                    value = viewModel.email,
                    onValueChange = {viewModel.email = it},
                    width = 0.9f,
                    height = 0.04f)

                InputFieldShift(
                    label = "Tilføj en beskrivelse",
                    value = viewModel.description,
                    onValueChange = {viewModel.description = it},
                    width = 0.9f,
                    height = 0.3f)
            }


            /*
            Column (
                    modifier = Modifier,
                    verticalArrangement = Arrangement.spacedBy(22.dp)
                ){

                    InputFieldShift(
                        label = "Titel",
                        value = viewModel.title,
                        onValueChange = { viewModel.title = it })
                    Row (
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ){
                        InputFieldShift(
                            label = "Antal timer",
                            value = viewModel.hours,
                            onValueChange = { viewModel.hours = it })
                        InputFieldShift(
                            label = "Vagtens dato",
                            value = viewModel.date,
                            onValueChange = { viewModel.date = it })
                    }
                    InputFieldShift(
                        label = "Kontakt persons e-mail",
                        value = viewModel.email,
                        onValueChange = { viewModel.email = it })
                    InputFieldShift(
                        label = "Beskrivelse",
                        value = viewModel.description,
                        onValueChange = { viewModel.description = it })

            }

             */
        }
    }
}