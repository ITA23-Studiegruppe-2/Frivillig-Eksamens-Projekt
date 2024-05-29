package com.example.frivillig_eksamens_projekt.ui.adviceScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.frivillig_eksamens_projekt.ui.createShiftScreen.TopBarCreateShift
/**
 *
 *
 * @author Christine Tofft
 *
 */
@Composable
fun AdviceScreen(
    onBackButtonClick: () -> Unit
) {

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
                text = "Tips & Tricks"
            )
            Box(
                modifier = Modifier
                    .padding(horizontal = 38.dp, vertical = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Her er nogle effektive tips og tricks til, " +
                        "hvordan din organisation kan skabe et positivt og støttende miljø for dine frivillige.")

            }
            Box (
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth()
                    .border(1.5.dp, color = Color(0xFF364830), shape = RoundedCornerShape(8.dp))
                    .clip(shape = RoundedCornerShape(8.dp))
                    .background(Color.White),
                contentAlignment = Alignment.Center,

            ){
                Column(
                    modifier = Modifier
                        .padding(18.dp)
                ) {
                    AdviceText(
                        title = "1. Skab en indbydende kultur",
                        text = "Faciliter sociale arrangementer, som tillader frivillige at lære hinanden at kende og bygge relationer."
                    )
                    AdviceText(
                        title = "2. Klar kommunikation" ,
                        text = "Vær klar over, hvad der forventes af frivillige, og hvad de kan forvente af organisationen." +
                                "Tilbyd konstruktiv feedback og opmuntring, og vær åben for input fra frivillige om, hvordan tingene kan forbedres."
                    )
                    AdviceText(
                        title = "3. Værdsættelse og anerkendelse",
                        text = "Send personlige takkekort eller emails for at vise værdsættelse af den enkeltes bidrag." +
                                "Overvej at have månedlige shout-outs på møder eller på sociale medier for at fremhæve særlige bidrag fra frivillige."
                    )
                    AdviceText(
                        title = "4. Effektiv ledelse" ,
                        text = "Sørg for, at frivilligledere er tilgængelige og støttende, klar til at hjælpe med udfordringer eller spørgsmål." +
                                "Giv frivillige en vis grad af autonomi til at træffe beslutninger inden for deres rolle, hvilket kan øge deres følelse af ejerskab og engagement."
                    )
                }
            }
        }
    }
}