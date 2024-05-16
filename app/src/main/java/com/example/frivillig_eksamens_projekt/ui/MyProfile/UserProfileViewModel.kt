import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class UserProfile(
    val fullName: String = "",
    val birthDate: String = "",
    val zipCode: String = ""
)

class UserProfileViewModel : ViewModel() {

    private val _userProfile = MutableStateFlow(UserProfile())
    val userProfile: StateFlow<UserProfile> get() = _userProfile

    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    init {
        fetchUserProfile()
    }

    private fun fetchUserProfile() {
        // Hardcoded user ID for testing purposes
        val userId = "lsMFJJbsqsUMvHG6p3Wdn8pK9ES2"

        Log.d("UserProfileViewModel", "Fetching profile for user UID: $userId")

        firestore.collection("users").document(userId).get()
            .addOnSuccessListener { document ->
                if (document != null && document.exists()) {
                    val fullName = document.getString("fullName") ?: ""
                    val birthDate = document.getString("birthDate") ?: ""
                    val zipCode = document.getString("zipCode") ?: ""
                    _userProfile.value = UserProfile(fullName, birthDate, zipCode)
                    Log.d("UserProfileViewModel", "User profile fetched: fullName=$fullName, birthDate=$birthDate, zipCode=$zipCode")
                } else {
                    Log.e("UserProfileViewModel", "Document does not exist")
                }
            }
            .addOnFailureListener { exception ->
                Log.e("UserProfileViewModel", "Error fetching document: ", exception)
            }
    }
}
