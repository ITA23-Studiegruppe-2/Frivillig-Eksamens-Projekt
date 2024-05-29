package com.example.frivillig_eksamens_projekt.ui.badgesScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.frivillig_eksamens_projekt.Models.Badge
import com.example.frivillig_eksamens_projekt.ui.createShiftScreen.TopBarCreateShift
/**
 *
 *
 * @author Christine Tofft
 *
 */

@Composable
fun BadgesScreen(
    onBackButtonClick: () -> Unit) {

    val viewModel: BadgesViewModel = viewModel()
    val secondaryColor = Color(0xFF364830)

    val badges = viewModel.badges.observeAsState(listOf())


    // Function to handle badge clicks
    fun onBadgeClick(badge: Badge) {
        viewModel.selectedBadge = badge
        viewModel.showDialog = true
    }
    // Dialog that is shown when a badge is clicked
    if (viewModel.showDialog) {
        BadgeDialog(
            onDismiss = {
                viewModel.showDialog = false
                viewModel.selectedBadge = null
            },
            title = viewModel.selectedBadge?.title ?: "",
            badgeDescription = viewModel.selectedBadge?.description ?: ""
        )
    }
    Box(
        modifier = Modifier
            .background(Color(0xFFC8D5B9))
            .fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopBarCreateShift(
                onBackButtonClick = onBackButtonClick,
                text = "Badges"
            )
            Box(
                modifier = Modifier
                    .padding(26.dp),
                contentAlignment = Alignment.Center
            ) {
                Column {
                    Text(
                        text = "Saml mærkater i Volunify ved at tage " +
                                "forskellige vagter og se dine belønninger vokse!",
                        fontSize = 16.sp,
                        color = secondaryColor
                    )
                    
                    Spacer(modifier = Modifier.height(28.dp))

                    Box(
                        modifier = Modifier
                            .width(380.dp)
                            .background(
                                color = Color.White,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .padding(20.dp)
                    ) {

                        LazyVerticalGrid(
                            columns = GridCells.Fixed(4),
                            contentPadding = PaddingValues(8.dp),
                            horizontalArrangement = Arrangement.spacedBy(16.dp),
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        )
                        {
                            items(badges.value) { badge ->
                                BadgeIcon(badge = badge, onClick = { onBadgeClick(badge) }, size = 65.dp)
                            }
                        }
                    }
                }
            }
        }
    }
}