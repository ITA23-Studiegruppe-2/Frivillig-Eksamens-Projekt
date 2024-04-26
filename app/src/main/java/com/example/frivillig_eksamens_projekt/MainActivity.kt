package com.example.frivillig_eksamens_projekt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.frivillig_eksamens_projekt.navigation.Navigation
import com.example.frivillig_eksamens_projekt.ui.loginScreen.LoginScreen
import com.example.frivillig_eksamens_projekt.ui.navigationBar.BottomNavigationBar
import com.example.frivillig_eksamens_projekt.ui.registerScreen.UserOrOrganisation
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
                    LoginScreen(
                        onSuccessLogin = {}
                    )
                    BottomNavigationBar(
                        onSearchClick = { /*TODO*/ },
                        onCalenderClick = { /*TODO*/ },
                        onHomePageClick = { /*TODO*/ },
                        onEmailClick = { /*TODO*/ }) {
                        
                    }



                    Navigation()
                }
            }
        }
    }
}
