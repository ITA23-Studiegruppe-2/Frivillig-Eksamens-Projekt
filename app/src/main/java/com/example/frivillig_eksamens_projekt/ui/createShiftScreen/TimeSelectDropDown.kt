package com.example.frivillig_eksamens_projekt.ui.createShiftScreen

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun TimeSelectDropDown(
    onClick: () -> Unit,
    value: String
) {

    val viewmodel: TimeSelectViewmodel = TimeSelectViewmodel()

}



@Preview
@Composable
fun show() {
    TimeSelectDropDown(onClick = { /*TODO*/ }, value = "Select Time")
}