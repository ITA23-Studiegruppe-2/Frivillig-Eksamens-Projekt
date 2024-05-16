import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class UserProfile(
    val name: String = "",
    val email: String = "",
    val birthDate: String = "",
    val zipCode: String = ""
)

class UserProfileViewModel : ViewModel() {

    private val _userProfile = MutableStateFlow(UserProfile())
    val userProfile: StateFlow<UserProfile> get() = _userProfile

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    init {
        fetchUserProfile()
    }

    private fun fetchUserProfile() {
        val userId = auth.currentUser?.uid
        Log.d("UserProfileViewModel", "Current user ID: $userId")

        if (userId == null) {
            Log.e("UserProfileViewModel", "No user is currently logged in.")
            return
        }

        viewModelScope.launch {
            firestore.collection("users").document(userId).get().addOnSuccessListener { document ->
                if (document != null) {
                    val name = document.getString("name") ?: "Anders"
                    val email = document.getString("email") ?: "anders@gmail.com"
                    val birthDate = document.getString("birthDate") ?: "06/12/2000"
                    val zipCode = document.getString("zipCode") ?: "2700"
                    _userProfile.value = UserProfile(name, email, birthDate, zipCode)
                    Log.d("UserProfileViewModel", "UserProfile fetched: $_userProfile")
                } else {
                    Log.e("UserProfileViewModel", "Document is null")
                }
            }.addOnFailureListener { exception ->
                Log.e("UserProfileViewModel", "Error fetching user profile", exception)
            }
        }
    }
}
