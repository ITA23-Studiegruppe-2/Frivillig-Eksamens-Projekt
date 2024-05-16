package com.example.frivillig_eksamens_projekt.ui.loginScreen


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.frivillig_eksamens_projekt.R
import com.example.frivillig_eksamens_projekt.ui.registerScreen.BackButton
import com.example.frivillig_eksamens_projekt.ui.registerScreen.CustomButton
import com.example.frivillig_eksamens_projekt.ui.registerScreen.InputfieldUser


@Composable
fun LoginScreen(
    onUserSuccessLogin: () -> Unit,
    onClick: () -> Unit,
    onOrgSuccessLogin: () -> Unit
) {
    val loginViewModel: LoginViewModel = remember {
        LoginViewModel()
    }



    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFC8D5B9)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {
        BackButton(onClick = onClick)


        //Titel & undertitel
        Text(text = "Velkommen til Volunify", fontSize = 28.sp)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = "Log ind på din konto")

        //Email Input
        Spacer(modifier = Modifier.height(40.dp))
        InputfieldUser(
            label = "E-mail",
            icon = Icons.Outlined.Email,
            isPassword = false,
            value = loginViewModel.email,
            onValueChange = { loginViewModel.email = it }
        )

        Spacer(modifier = Modifier.height(20.dp))

        //Password Input
        InputfieldUser(
            label = "Adgangskode",
            icon = Icons.Outlined.Lock,
            isPassword = true,
            value = loginViewModel.password,
            onValueChange = { loginViewModel.password = it },
        )
        Text(
            text = loginViewModel.errorMessage,
            style = TextStyle(
                color = Color.Red
            )
        )


        //Checkbox & glemt adgangskode
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.weight(0.2f))
            Checkbox(
                checked = loginViewModel.rememberMe,
                onCheckedChange = { loginViewModel.rememberMe = it }
            )
            Spacer(modifier = Modifier.width(1.dp))
            Text(
                text = "Husk mig",
                fontSize = 10.sp
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "Glemt adgangskode?",
                fontSize = 10.sp,
                modifier = Modifier
                    .clickable { /* Handle click */ }
                    .padding(end = 15.dp)
            )
        }


        //Login via noget andet
        Spacer(modifier = Modifier.height(15.dp))
        Text(text = "Eller login med")


        // Måske rykke over til en anden side.
        val faceBookIcon: Painter = painterResource(id = R.drawable.facebook_logo)
        val googleIcon: Painter = painterResource(id = R.drawable.google_logo)
        val twitterIcon: Painter = painterResource(id = R.drawable.twitter_logo)

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer (modifier = Modifier.height(80.dp))


                Spacer(modifier = Modifier.width(25.dp))
                Icon(
                    painter = faceBookIcon,
                    contentDescription = "Facebook logo",
                    tint = Color.Unspecified,
                    modifier = Modifier
                        .size(20.dp)
                        .clickable { /* Facebook icon clicked */ }
                )

                Spacer(modifier = Modifier.width(25.dp))
                Icon(
                    painter = googleIcon,
                    contentDescription = "Google logo",
                    tint = Color.Unspecified,
                    modifier = Modifier
                        .size(20.dp)
                        .clickable { /* Google icon clicked */ }
                )

                Spacer(modifier = Modifier.width(25.dp))
                Icon(
                    painter = twitterIcon,
                    contentDescription = "Twitter logo",
                    tint = Color.Unspecified,
                    modifier = Modifier
                        .size(20.dp)
                        .clickable { /* Twitter icon clicked */ }
                )
            }
        //Login knappen
        Spacer(modifier = Modifier.height(20.dp))
        CustomButton(text = "Login") {
            loginViewModel.login(
                loginViewModel.email,
                loginViewModel.password,
                onUserSuccessLogin = onUserSuccessLogin,
                onOrgSuccessLogin = onOrgSuccessLogin,
                onFailure = { loginViewModel.errorMessage = it}
            )
        }
    }
}





