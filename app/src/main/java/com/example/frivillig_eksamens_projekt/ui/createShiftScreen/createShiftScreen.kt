package com.example.frivillig_eksamens_projekt.ui.createShiftScreen


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.frivillig_eksamens_projekt.ui.registerScreen.BackButton

@Composable
fun CreateShift(
    onBackButtonClick: () -> Unit,
) {
    val secondaryColor = Color(0xFF364830)

    val viewModel: CreateShiftViewModel = viewModel()

    Box(
        modifier = Modifier
            .background(Color(0xFFC8D5B9))
            .fillMaxSize()
    ) {
        Column {
            TopBarCreateShift(
                onBackButtonClick = onBackButtonClick
            )
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.TopCenter
            ) {
                Column() {
                    InputFieldShift(label = "Title", value = viewModel.title, onValueChange = {viewModel.title = it})

                    DateSelector(onClick = {  }, value = viewModel.date)
                    Row {
                        
                    }

                }
            }

        }

    }
}

@Composable
@Preview
fun showScreen() {
    CreateShift(
        onBackButtonClick = {}
    )
}