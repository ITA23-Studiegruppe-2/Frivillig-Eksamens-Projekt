package com.example.frivillig_eksamens_projekt.ui.OrganisationProfile

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrganisationProfile(viewModel: OrganisationProfileViewModel = viewModel()) {
    val organisationProfile by viewModel.organisationProfile.collectAsState()
    val profile = organisationProfile

    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = {
                    Text(
                        "Din profil: ${profile?.name ?: "Loading..."}",
                        color = Color.Black,
                        modifier = Modifier.padding(top = 20.dp)
                    )
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.White),
                modifier = Modifier
                    .height(78.dp)
                    .border(1.dp, Color.Gray),
                actions = {
                    // Settings icon button
                    IconButton(onClick = { /* Handle settings click here */ }) {
                        Icon(
                            imageVector = Icons.Filled.Settings,
                            modifier = Modifier
                                .size(100.dp)
                                .padding(top = 10.dp),
                            contentDescription = "Settings",
                            tint = Color.Black  // Specify the icon tint color if needed
                        )
                    }
                }
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = Color(0xFFC8D5B9),
                modifier = Modifier.border(1.dp, Color.Black),
                content = {}
            )
        }
    ) { innerPadding ->
        if (profile != null) {
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .background(Color(0xFFC8D5B9))
                    .padding(16.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(20.dp))
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "Profile",
                    modifier = Modifier
                        .size(180.dp)
                        .align(Alignment.CenterHorizontally),
                    tint = Color.White
                )
                Spacer(modifier = Modifier.height(50.dp))
                InputfieldUser("Organisationsen navn: ${profile.name}", Icons.Default.Person)
                Spacer(modifier = Modifier.height(14.dp))
                InputfieldUser("Email: ${profile.email}", Icons.Default.Email)
                Spacer(modifier = Modifier.height(14.dp))
                InputfieldUser("CVR: ${profile.cvrNumber}", Icons.Default.LocationOn)
            }
        } else {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
    }
}

@Composable
fun InputfieldUser(label: String, mainIcon: ImageVector, textColor: Color = Color.Black) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(Color.White, RoundedCornerShape(10.dp))
            .border(0.6.dp, Color.Black, RoundedCornerShape(10.dp))
            .padding(14.dp)
    ) {
        Icon(
            imageVector = mainIcon,
            contentDescription = null,
            modifier = Modifier.size(20.dp),
            tint = textColor
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = label,
            color = textColor
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewOrganisationProfile() {
    OrganisationProfile()
}
