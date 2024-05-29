package com.example.frivillig_eksamens_projekt.ui.loginScreen


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.frivillig_eksamens_projekt.ui.registerScreen.BackButton
import com.example.frivillig_eksamens_projekt.ui.registerScreen.CustomButton
import com.example.frivillig_eksamens_projekt.ui.registerScreen.InputfieldUser


@Composable
fun LoginScreen(
    onUserSuccessLogin: () -> Unit,
    onOrgSuccessLogin: () -> Unit,
    onBackButtonClick: () -> Unit
) {
    val loginViewModel: LoginViewModel = LoginViewModel()


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFC8D5B9))
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            Row {
                Spacer(modifier = Modifier.width(10.dp))
                BackButton(onClick = onBackButtonClick)
            }
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(top = 160.dp)
            ) {

                Text(text = "Velkommen til Volunify", fontSize = 28.sp)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "Log ind på din konto")


                Spacer(modifier = Modifier.height(40.dp))
                InputfieldUser(
                    label = "E-mail",
                    icon = Icons.Outlined.Email,
                    isPassword = false,
                    value = loginViewModel.email,
                    onValueChange = { loginViewModel.email = it }
                )

                Spacer(modifier = Modifier.height(20.dp))


                InputfieldUser(
                    label = "Adgangskode",
                    icon = Icons.Outlined.Lock,
                    isPassword = true,
                    value = loginViewModel.password,
                    onValueChange = { loginViewModel.password = it }
                )
                Text(
                    text = loginViewModel.errorMessage,
                    style = TextStyle(
                        color = Color.Red
                    )
                )

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {

                    }

                    Spacer(modifier = Modifier.height(10.dp))
                    CustomButton(text = "Login") {
                        loginViewModel.login(
                            loginViewModel.email,
                            loginViewModel.password,
                            onUserSuccessLogin = onUserSuccessLogin,
                            onOrgSuccessLogin = onOrgSuccessLogin,
                            onFailure = { loginViewModel.errorMessage = it }
                        )
                    }
                }
            }
        }
    }
}
