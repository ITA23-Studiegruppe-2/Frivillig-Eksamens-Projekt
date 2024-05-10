package com.example.frivillig_eksamens_projekt.ui.MyProfile
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrganistionProfile() {
    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = {
                    Text(
                        "Din profil: Roskilde Festival",
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
            InputfieldUser("Roskilde Festival", Icons.Default.Person)
            Spacer(modifier = Modifier.height(14.dp))
            InputfieldUser("roskildefestival@example.com", Icons.Default.Email)
            Spacer(modifier = Modifier.height(14.dp))
            InputfieldUser("2100", Icons.Default.ArrowDropDown)
            Spacer(modifier = Modifier.height(14.dp))
            InputfieldUser("27823199231", Icons.Default.LocationOn)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewOrganistionProfile() {
    OrganistionProfile()
}
