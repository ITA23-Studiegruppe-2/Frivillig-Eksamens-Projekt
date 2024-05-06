package com.example.frivillig_eksamens_projekt.ui.OrganisationProfile

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen() {
    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = { Text("Din profil: Anders Keller", color = Color.Black) },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color.White // White color for the top bar
                )
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = Color.White, // White color for the bottom bar
                content = {}
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(Color(0xFFC8D5B9))
                .padding(16.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(20.dp))
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "Profile",
                    modifier = Modifier
                        .size(150.dp)
                        .align(Alignment.CenterHorizontally),
                    tint = Color.White
                )

                Spacer(modifier = Modifier.height(80.dp))
                InputfieldUser(label = "Brugers navn: Anders Keller", icon = Icons.Default.Person, value = "", textColor = Color.Black)
                Spacer(modifier = Modifier.height(14.dp))
                InputfieldUser(label = "Email: anders@example.com", icon = Icons.Default.Email, value = "", textColor = Color.Black)
                Spacer(modifier = Modifier.height(14.dp))
                InputfieldUser(label = "Fødselsdag: 01/01/1990", icon = Icons.Default.ArrowDropDown, value = "", textColor = Color.Black)
                Spacer(modifier = Modifier.height(14.dp))
                InputfieldUser(label = "Postnummer: 2700", icon = Icons.Default.LocationOn, value = "", textColor = Color.Black)
            }
        }
    }
}

@Composable
fun InputfieldUser(label: String, icon: ImageVector, value: String, textColor: Color) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(Color.White)
            .border(1.dp, Color.Gray, RoundedCornerShape(4.dp))  // Adds a border with rounded corners
            .padding(14.dp)  // Padding inside the border
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(24.dp),
            tint = textColor
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = label, color = textColor)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewProfileScreen() {
    ProfileScreen()
}
