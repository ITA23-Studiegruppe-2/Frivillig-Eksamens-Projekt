package com.example.frivillig_eksamens_projekt.ui.registerScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
/**
 *
 *
 * @author Christine Tofft
 *
 */
@Composable
fun TermsAndConditionsCheckbox(checkboxViewModel: CreateUserViewModel) {
    val isTermsAndConditionsChecked = checkboxViewModel.isTermsAndConditionsChecked
    Box(
        modifier = Modifier
            .fillMaxWidth(),
    )
    {
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Spacer(modifier = Modifier.width(40.dp))
            Checkbox(
                checked = isTermsAndConditionsChecked,
                onCheckedChange = { checkboxViewModel.isTermsAndConditionsChecked = !checkboxViewModel.isTermsAndConditionsChecked},
                modifier = Modifier.graphicsLayer(scaleX = 1.1f, scaleY = 1.1f)
            )
            Column(
                modifier = Modifier,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Ved at klikke her, accepterer du vores", fontSize = 12.sp)
                Text(
                    text = "vilkår & betingelser",
                    fontSize = 12.sp,
                    textDecoration = TextDecoration.Underline
                )
            }
        }
    }
}