package com.example.frivillig_eksamens_projekt.ui.MyProfile
import android.graphics.drawable.Icon
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
fun ProfileScreen() {
    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = {
                    Column {
                        Text("Din profil:",
                            color = Color.Black,
                            modifier = Modifier.padding(top = 20.dp))  // Add some spacing between the lines
                        Text("Anders Keller",
                            color = Color.Black)
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.White),
                modifier = Modifier
                    .height(90.dp)
                    .border(1.dp, Color.Black),
                actions = {
                    // Settings icon button
                    IconButton(onClick = { /* Handle settings click here */ }) {
                        Icon(
                            imageVector = Icons.Filled.Settings,
                            modifier = Modifier
                                .size(50.dp)
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
            Spacer(modifier = Modifier.height(30.dp))
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = "editer",
                modifier = Modifier
                    .size(30.dp)
                    .align(Alignment.End)
            )
            Spacer(modifier = Modifier.height(15.dp))
            InputfieldUser("Anders Keller", Icons.Default.Person)
            Spacer(modifier =    Modifier.height(14.dp))
            InputfieldUser("anders@example.com", Icons.Default.Email,)
            Spacer(modifier = Modifier.height(14.dp))
            InputfieldUser(" 01/01/1990", Icons.Default.ArrowDropDown)
            Spacer(modifier = Modifier.height(14.dp))
            InputfieldUser("2700", Icons.Default.LocationOn)
        }
    }
}

@Composable
fun InputfieldUser(label: String, mainIcon: ImageVector, value: String = "", textColor: Color = Color.Black) {
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
        Text(text = label,
            color = textColor
        )
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewProfileScreen() {
    ProfileScreen()
}
