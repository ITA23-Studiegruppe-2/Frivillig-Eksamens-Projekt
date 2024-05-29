package com.example.frivillig_eksamens_projekt.ui.activityScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.frivillig_eksamens_projekt.R

/**
 *
 * @author Rasmus Planteig
 * @author Christine Tofft
 *
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    onValueChange: (String) -> Unit,
    searchBarValue: String, viewModel: ActivityScreenViewModel
) {

    val secondaryColor = Color(0xFF364830)
    val tuneIcon: Painter = painterResource(id = R.drawable.tune_icon)


    Box(
        modifier = Modifier
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Row (
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically
        ){
            OutlinedTextField(
                value = searchBarValue,
                onValueChange = onValueChange,
                modifier = Modifier,
                label = {
                    Text(text = "Søg efter organisation", color = secondaryColor)
                },
                shape = RoundedCornerShape(20.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = Color.White,
                    focusedTextColor = secondaryColor,
                    focusedLabelColor = secondaryColor,
                    unfocusedBorderColor = secondaryColor,
                    focusedBorderColor = secondaryColor
                ),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Search,
                        contentDescription = "Search",
                        tint = secondaryColor,
                        modifier = Modifier.size(30.dp)
                    )

                }
            )
            Spacer(modifier = Modifier.width(20.dp))
            Box {
                Icon(
                    painter = tuneIcon,
                    contentDescription = "Tune Icon",
                    modifier = Modifier
                        .size(58.dp)
                        .clickable {
                            viewModel.showFilterDialog = true
                            println(viewModel.showFilterDialog)

                        }
                )
            }
        }
    }
}
