package com.example.frivillig_eksamens_projekt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.frivillig_eksamens_projekt.navigation.Navigation
import com.example.frivillig_eksamens_projekt.ui.registerScreen.registerOrg.CreateOrgScreen
import com.example.frivillig_eksamens_projekt.ui_elements.theme.FrivilligEksamensProjektTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FrivilligEksamensProjektTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    Navigation()


                    /*BottomNavigationBar(
                        onSearchClick = { /*TODO*/ },
                        onCalenderClick = { /*TODO*/ },
                        onHomePageClick = { /*TODO*/ },
                        onEmailClick = { /*TODO*/ }) {
                        
                    }*/

                }
            }
        }
    }
}
