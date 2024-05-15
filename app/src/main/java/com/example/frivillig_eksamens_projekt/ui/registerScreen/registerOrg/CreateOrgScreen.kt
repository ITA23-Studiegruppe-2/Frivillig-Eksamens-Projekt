package com.example.frivillig_eksamens_projekt.ui.registerScreen.registerOrg

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
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
import com.example.frivillig_eksamens_projekt.ui.registerScreen.CustomButton
import com.example.frivillig_eksamens_projekt.ui.registerScreen.InputfieldUser
import com.example.frivillig_eksamens_projekt.ui.registerScreen.TermsAndConditionsCheckbox

@Composable
fun CreateOrgScreen(
    onSuccess: () -> Unit,
    onFail: () -> Unit,
    navController: NavController
) {

    val viewModel = CreateOrgViewModel()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = viewModel.backgroundColor
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {
            BackButton(onClick = {navController.popBackStack()})
            Spacer(modifier = Modifier.height(50.dp))
            Text(text = "Kom i gang", fontSize = 36.sp, color = Color(0xFF364830))
            Text(text = "Opret organisation", fontSize = 17.sp, color = Color(0xFF364830))

            Spacer(modifier = Modifier.height(30.dp))

            Column(verticalArrangement = Arrangement.spacedBy(22.dp))
            {
                InputfieldUser(
                    label = "Organisationens navn",
                    icon = Icons.Outlined.CheckCircle,
                    value = viewModel.orgName,
                    onValueChange = { viewModel.orgName = it})
                InputfieldUser(
                    label = "E-mail",
                    icon = Icons.Outlined.Email,
                    value = viewModel.email,
                    onValueChange = { viewModel.email = it})
                InputfieldUser(
                    label = "Adgangskode",
                    icon = Icons.Outlined.Lock,
                    value = viewModel.password,
                    onValueChange = { viewModel.password = it})
                InputfieldUser(
                    label = "CVR-nummer",
                    icon = Icons.Outlined.Add,
                    value = viewModel.cvrNumber,
                    onValueChange = { viewModel.cvrNumber = it})
            }
            Spacer(modifier = Modifier.height(28.dp))

            //TermsAndConditionsCheckbox(checkboxViewModel = viewModel)

            Spacer(modifier = Modifier.height(4.dp))

            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                CustomButton(
                    text = "Tilmeld",
                    onClick = {
                        viewModel.registerOrgToDatabase(
                            onSuccess = onSuccess, onFail = {}
                        )
                    })
            }
    }
    }
}