package com.example.frivillig_eksamens_projekt.ui.registerScreen

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
/**
 *
 *
 * @author Christine Tofft
 *
 */
@Composable
fun GenderDropDown(viewModel: CreateUserViewModel){

    DropdownMenu(
        expanded = viewModel.dropDownVisible,
        onDismissRequest = { viewModel.dropDownVisible = false },
        modifier = Modifier
            .width(175.dp))
    {
        DropdownMenuItem(
            text = { Text(text = "Mand") },
            onClick = { viewModel.isExpanded = false
            viewModel.gender = "Mand"})
        DropdownMenuItem(
            text = { Text(text = "Kvinde") },
            onClick = { viewModel.isExpanded = false
                viewModel.gender = "Kvinde"})
        DropdownMenuItem(
            text = { Text(text = "Andet") },
            onClick = { viewModel.isExpanded = false
                viewModel.gender = "Andet"})
    }
}