package com.example.frivillig_eksamens_projekt.ui.MyProfile

import UserProfileViewModel
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen() {
    val viewModel: UserProfileViewModel = viewModel()
    val userProfile by viewModel.userProfile.collectAsState()
    val isEditing by viewModel.isEditing.collectAsState()

    Log.d("ProfileScreen", "Displaying user profile: $userProfile")

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
                    .border(1.dp, Color.Black),
                actions = {
                    IconButton(onClick = { viewModel.toggleEdit() }) {
                        Icon(
                            imageVector = if (isEditing) Icons.Filled.Check else Icons.Filled.Edit,
                            modifier = Modifier
                                .size(30.dp)
                                .padding(top = 10.dp),
                            contentDescription = if (isEditing) "Save" else "Edit",
                            tint = Color.Black
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
                    .size(160.dp)
                    .align(Alignment.CenterHorizontally),
                tint = Color.White
            )
            Spacer(modifier = Modifier.height(20.dp))
            if (isEditing) {
                val updatedProfile = remember { mutableStateOf(userProfile) }
                InputFieldUser(
                    label = "Full Name",
                    value = updatedProfile.value.fullName,
                    onValueChange = { updatedProfile.value = updatedProfile.value.copy(fullName = it) },
                    mainIcon = Icons.Default.Person
                )
                Spacer(modifier = Modifier.height(14.dp))
                InputFieldUser(
                    label = "Phone Number",
                    value = updatedProfile.value.phoneNumber,
                    onValueChange = { updatedProfile.value = updatedProfile.value.copy(phoneNumber = it) },
                    mainIcon = Icons.Default.Phone
                )
                Spacer(modifier = Modifier.height(14.dp))
                InputFieldUser(
                    label = "Birth Date",
                    value = updatedProfile.value.birthDate,
                    onValueChange = { updatedProfile.value = updatedProfile.value.copy(birthDate = it) },
                    mainIcon = Icons.Default.ArrowDropDown
                )
                Spacer(modifier = Modifier.height(14.dp))
                InputFieldUser(
                    label = "Zip Code",
                    value = updatedProfile.value.zipCode,
                    onValueChange = { updatedProfile.value = updatedProfile.value.copy(zipCode = it) },
                    mainIcon = Icons.Default.LocationOn
                )
                Spacer(modifier = Modifier.height(14.dp))
                Button(
                    onClick = {
                        viewModel.updateUserProfile(updatedProfile.value)
                    },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text("Save")
                }
            } else {
                InputFieldUser(label = viewModel.name, mainIcon = Icons.Default.Person)
                Spacer(modifier = Modifier.height(14.dp))
                InputFieldUser(label = viewModel.phoneNumber, mainIcon = Icons.Default.Phone)
                Spacer(modifier = Modifier.height(14.dp))
                InputFieldUser(label = viewModel.birthDate, mainIcon = Icons.Default.ArrowDropDown)
                Spacer(modifier = Modifier.height(14.dp))
                InputFieldUser(label = viewModel.zipCode, mainIcon = Icons.Default.LocationOn)
            }
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputFieldUser(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    mainIcon: ImageVector
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .background(Color.White, RoundedCornerShape(10.dp))
            .border(0.6.dp, Color.Black, RoundedCornerShape(10.dp))
            .padding(8.dp)
    ) {
        Icon(
            imageVector = mainIcon,
            contentDescription = null,
            modifier = Modifier.size(20.dp),
            tint = Color.Black
        )
        Spacer(modifier = Modifier.width(16.dp))
        TextField(
            value = value,
            onValueChange = onValueChange,
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen()
}
