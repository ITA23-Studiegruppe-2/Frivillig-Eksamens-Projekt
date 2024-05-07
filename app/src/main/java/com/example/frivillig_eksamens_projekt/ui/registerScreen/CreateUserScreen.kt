package com.example.frivillig_eksamens_projekt.ui.registerScreen

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
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.frivillig_eksamens_projekt.ui.registerScreen.BackButton
import com.example.frivillig_eksamens_projekt.ui.registerScreen.CreateUserViewModel
import com.example.frivillig_eksamens_projekt.ui.registerScreen.CustomButton
import com.example.frivillig_eksamens_projekt.ui.registerScreen.InputfieldUser

@Composable
fun CreateUserScreen(
    onSuccess: () -> Unit,
    onFail: () -> Unit,
    viewModel: CreateUserViewModel,
    onClick: () -> Unit
){


    Surface(
        modifier = Modifier.fillMaxSize(),
        color = viewModel.backgroundColor
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {
            BackButton(onClick = onClick)
            Spacer(modifier = Modifier.height(50.dp))
            Text(text = "Kom i gang", fontSize = 36.sp, color = Color(0xFF364830))
            Text(text = "Opret en bruger", fontSize = 17.sp, color = Color(0xFF364830))

            Spacer(modifier = Modifier.height(30.dp))

            Column(verticalArrangement = Arrangement.spacedBy(22.dp))
            {
                InputfieldUser(
                    "Fulde navn",
                    icon = Icons.Outlined.Person,
                    value = viewModel.fullName,
                    onValueChange = { viewModel.fullName = it })
                InputfieldUser(
                    "E-mail",
                    icon = Icons.Outlined.Email,
                    value = viewModel.email,
                    onValueChange = { viewModel.email = it })
                InputfieldUser(
                    "Adgangskode",
                    icon = Icons.Outlined.Lock,
                    isPassword = true,
                    value = viewModel.password,
                    onValueChange = { viewModel.password = it })
                InputfieldUser(
                    "Gentag adgangskode",
                    icon = Icons.Outlined.Lock,
                    value = viewModel.password2,
                    isPassword = true,
                    onValueChange = { viewModel.password2 = it })

                Text(
                    text = viewModel.errorMessage,
                    style = TextStyle(
                        color = Color.Red
                    )
                )
            }
            
            Spacer(modifier = Modifier.height(30.dp))

                Box(modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                    contentAlignment = Alignment.Center) {
                    CustomButton(
                        text = "Næste",
                        onClick = {
                            viewModel.registerUserAuthentication(
                                onSuccess = onSuccess,
                                onFail = {viewModel.errorMessage = it})
                        })
                }

            Row {
                Text(text = "Har du allerede en bruger?", fontSize = 12.sp)
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = "Log ind", fontSize = 12.sp, textDecoration = TextDecoration.Underline)
            }
            }
        }
    }