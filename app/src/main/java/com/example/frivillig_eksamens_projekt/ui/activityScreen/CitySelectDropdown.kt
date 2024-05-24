package com.example.frivillig_eksamens_projekt.ui.activityScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CitySelectDropdown(
    isExpanded: Boolean,
    onClick: (Boolean) -> Unit,
    onDismiss: () -> Unit,
    value: String,
    onValueClick: (String) -> Unit,
    listOfCities: MutableList<String>,

){
    val secondaryColor = Color(0xFF364830)

    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ){
        ExposedDropdownMenuBox(
            expanded = isExpanded,
            onExpandedChange = onClick,
            modifier = Modifier

        ) {
            TextField(
                value = value,
                onValueChange = {},
                readOnly = true,
                colors = ExposedDropdownMenuDefaults.textFieldColors(
                    focusedContainerColor = secondaryColor,
                    unfocusedContainerColor = secondaryColor,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                modifier = Modifier
                    .menuAnchor()
                    .width(180.dp)
                    .height(50.dp),

                shape = RoundedCornerShape(24.dp),
                textStyle = LocalTextStyle.current.copy(
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold)
                )
            ExposedDropdownMenu(
                expanded = isExpanded,
                onDismissRequest = onDismiss
            ){
                listOfCities.forEach{city ->
                    DropdownMenuItem(
                        text = { Text(text = city) },
                        onClick = {
                            onValueClick(city)
                            onDismiss()
                        }
                    )
                }
            }
        }
    }
}