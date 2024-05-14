package com.example.frivillig_eksamens_projekt.ui.registerScreen

import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material.icons.outlined.Place
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun CreateUserSecondScreen(
    onSuccess: () -> Unit,
    onFail: () -> Unit,
    navController: NavController,
    viewModel: CreateUserViewModel
) {


    Surface(
        modifier = Modifier.fillMaxSize(),
        color = viewModel.backgroundColor
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            BackButton(onClick = {navController.popBackStack()})
            Spacer(modifier = Modifier.height(70.dp))
            Text(text = "Færdiggør din bruger", fontSize = 22.sp, color = Color(0xFF364830))

            Spacer(modifier = Modifier.height(40.dp))

            Column(
                verticalArrangement = Arrangement.spacedBy(22.dp)
            ) {
                InputfieldUser(
                    "Telefonnummer",
                    icon = Icons.Outlined.Phone,
                    value = viewModel.phoneNumber,
                    onValueChange = { viewModel.phoneNumber = it })
                InputfieldUser(
                    "Postnummer",
                    icon = Icons.Outlined.Place,
                    value = viewModel.zipCode,
                    onValueChange = { viewModel.zipCode = it })
                InputfieldUser(
                    "Fødselsdag",
                    icon = Icons.Outlined.DateRange,
                    value = viewModel.birthDate,
                    onValueChange = { viewModel.birthDate = it })
                Text(
                    text = viewModel.errorMessage,
                    style = TextStyle(
                        color = Color.Red
                    )
                )

                Row {
                    Spacer(modifier = Modifier.width(14.dp))
                    Text(text = "Køn", fontSize = 16.sp, color = Color(0xFF364830))
                }
                GenderCheckbox()

                Spacer(modifier = Modifier.height(28.dp))

                Box {
                    TermsAndConditionsCheckbox()
                }

                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    CustomButton(
                        text = "Tilmeld",
                        onClick = {
                            viewModel.registerUserToDatabase(onSuccess = onSuccess, onFail = {})
                        })
                }
            }

        }
    }
}