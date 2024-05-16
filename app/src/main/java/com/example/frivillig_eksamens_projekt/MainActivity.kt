package com.example.frivillig_eksamens_projekt

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.frivillig_eksamens_projekt.ui_elements.theme.FrivilligEksamensProjektTheme
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : ComponentActivity() {
    private val firestore = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FrivilligEksamensProjektTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Minimal Firestore Access Test
                    testFirestoreAccess()
                }
            }
        }
    }

    private fun testFirestoreAccess() {
        val userId = "lsMFJJbsqsUMvHG6p3Wdn8pK9ES2"
        firestore.collection("users").document(userId).get()
            .addOnSuccessListener { document ->
                if (document != null && document.exists()) {
                    Log.d("FirestoreTest", "Document exists: ${document.data}")
                } else {
                    Log.e("FirestoreTest", "Document does not exist")
                }
            }
            .addOnFailureListener { exception ->
                Log.e("FirestoreTest", "Error fetching document: ", exception)
            }
    }
}
