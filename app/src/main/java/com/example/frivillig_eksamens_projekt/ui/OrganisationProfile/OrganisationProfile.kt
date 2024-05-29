package com.example.frivillig_eksamens_projekt.ui.OrganisationProfile

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
/**
 *
 * @author Anders Keller
 *
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrganisationScreen() {
    val viewModel: OrganisationProfileViewModel = viewModel()

    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = {
                    Column {
                        Text(
                            "Din profil:",
                            color = Color.Black,
                            modifier = Modifier.padding(top = 20.dp)
                        )
                        Text(
                            viewModel.name,
                            color = Color.Black
                        )
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.White),
                modifier = Modifier
                    .height(90.dp)
                    .border(1.dp, Color.Black)
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
                    .size(160.dp)
                    .align(Alignment.CenterHorizontally),
                tint = Color.White
            )
            Spacer(modifier = Modifier.height(20.dp))

            InputFieldUser(label = viewModel.name, mainIcon = Icons.Default.Person)
            Spacer(modifier = Modifier.height(14.dp))
            InputFieldUser(label = viewModel.cvrNumber, mainIcon = Icons.Default.Info)
            Spacer(modifier = Modifier.height(14.dp))
            InputFieldUser(label = viewModel.email, mainIcon = Icons.Default.Email)
        }
    }
}

@Composable
fun InputFieldUser(label: String, mainIcon: ImageVector) {
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
            tint = Color.Black
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = label,
            color = Color.Black
        )
    }
}

@Preview(showBackground = true)
@Composable
fun OrganisationScreenPreview() {
    OrganisationScreen()
}