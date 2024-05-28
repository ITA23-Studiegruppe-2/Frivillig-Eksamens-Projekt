import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.frivillig_eksamens_projekt.Models.User
import com.example.frivillig_eksamens_projekt.repositories.UsersRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

// Data class to hold user profile information
data class UserProfile(
    val fullName: String = "",
    val phoneNumber: String = "",
    val zipCode: String = "",
    val birthDate: String = "",
    val gender: String = "",
    val userUID: String? = ""
)

// ViewModel class for managing user profile data
class UserProfileViewModel : ViewModel() {

    // MutableStateFlow to hold the user profile data
    private val _userProfile = MutableStateFlow(User())
    val userProfile: StateFlow<User> get() = _userProfile

    // MutableStateFlow to hold the editing state
    private val _isEditing = MutableStateFlow(false)
    val isEditing: StateFlow<Boolean> get() = _isEditing

    // Firebase Authentication and Firestore instances
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    // Instance of UsersRepository for data operations
    val usersRepository: UsersRepository = UsersRepository()

    // Mutable state properties for user profile fields
    var name by mutableStateOf("")
    var birthDate by mutableStateOf("")
    var phoneNumber by mutableStateOf("")
    var zipCode by mutableStateOf("")

    // Initialization block to fetch the user data when ViewModel is created
    init {
        getUser()
    }

    // Function to fetch the current user data
    private fun getUser() {
        viewModelScope.launch {
            val currentUserInformation: User? = usersRepository.getUser()
            if (currentUserInformation != null) {
                name = currentUserInformation.fullName
                birthDate = currentUserInformation.birthDate
                phoneNumber = currentUserInformation.phoneNumber
                zipCode = currentUserInformation.zipCode
                _userProfile.value = currentUserInformation
            }
        }
    }

    // Function to toggle the editing state
    fun toggleEdit() {
        _isEditing.value = !_isEditing.value
    }

    // Function to update the user profile
    fun updateUserProfile(updatedProfile: User) {
        val userUID = auth.currentUser?.uid
        if (userUID != null) {
            db.collection("Users").document(userUID).set(updatedProfile)
                .addOnSuccessListener {
                    _userProfile.value = updatedProfile
                    name = updatedProfile.fullName
                    birthDate = updatedProfile.birthDate
                    phoneNumber = updatedProfile.phoneNumber
                    zipCode = updatedProfile.zipCode
                    toggleEdit()
                    Log.d("UserProfileViewModel", "Updated user profile: $updatedProfile")
                }
                .addOnFailureListener { exception ->
                    Log.e("UserProfileViewModel", "Error updating user profile", exception)
                }
        }
    }
}
