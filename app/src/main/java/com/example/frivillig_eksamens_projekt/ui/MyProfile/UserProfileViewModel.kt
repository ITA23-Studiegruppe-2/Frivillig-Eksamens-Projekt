import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class UserProfile(
    val fullName: String = "",
    val birthDate: String = "",
    val zipCode: String = "",
    val profilePictureUri: Uri? = null
)

class UserProfileViewModel : ViewModel() {

    private val _userProfile = MutableStateFlow(UserProfile())
    val userProfile: StateFlow<UserProfile> get() = _userProfile

    private val _isEditing = MutableStateFlow(false)
    val isEditing: StateFlow<Boolean> get() = _isEditing

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    init {
        fetchUserProfile()
    }

    private fun fetchUserProfile() {
        val userId = auth.currentUser?.uid
        if (userId != null) {
            db.collection("Users").document(userId).get()
                .addOnSuccessListener { document ->
                    if (document != null && document.exists()) {
                        val userProfile = document.toObject(UserProfile::class.java)
                        if (userProfile != null) {
                            _userProfile.value = userProfile
                        }
                    }
                }
                .addOnFailureListener { exception ->
                    Log.e("UserProfileViewModel", "Error fetching user profile", exception)
                }
        } else {
            Log.e("UserProfileViewModel", "User ID is null")
        }
    }

    fun toggleEdit() {
        _isEditing.value = !_isEditing.value
    }

    fun updateUserProfile(updatedProfile: UserProfile) {
        val userId = auth.currentUser?.uid
        if (userId != null) {
            db.collection("Users").document(userId).set(updatedProfile)
                .addOnSuccessListener {
                    _userProfile.value = updatedProfile
                    toggleEdit()
                }
                .addOnFailureListener { exception ->
                    Log.e("UserProfileViewModel", "Error updating user profile", exception)
                }
        }
    }
}
