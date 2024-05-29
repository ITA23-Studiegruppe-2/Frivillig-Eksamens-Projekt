
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.frivillig_eksamens_projekt.Models.Conversation
import com.example.frivillig_eksamens_projekt.repositories.ChatRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
/**
 *
 *
 * @author Lucas Jacobsen
 *
 */
class ConversationViewModel : ViewModel() {
    private val chatRepository = ChatRepository()
    var backgroundColor by mutableStateOf(Color(0xFFC8D5B9))


    // Mutable state to hold the list of conversations
    var conversations by mutableStateOf<List<Conversation>>(listOf())

    // Mutable state to hold the current user's ID
    var currentUserId by mutableStateOf<String?>(null)




    // sets up an authentication state listener to get the current user ID and calls
    // fetchMessages to retrieve messages whenever the user changes
    init {
        // Get current user
        FirebaseAuth.getInstance().addAuthStateListener { firebaseAuth ->
            currentUserId = firebaseAuth.currentUser?.uid
            // Fetch messages when user change
            currentUserId?.let {
                fetchMessages(it)
            }
        }
    }



    // Function to fetch messages for the current user
    fun fetchMessages(userId: String) {
        viewModelScope.launch {
            Log.d("ChatViewModel", "Fetching messages for user ID: $userId")
            try {
                conversations = chatRepository.getConversationsByUserId(userId)
                Log.d("ChatViewModel", "Fetched messages: ${conversations.size}")
            } catch (e: Exception) {
                Log.e("ChatViewModel", "Error fetching messages: ${e.message}")
            }
        }
    }
}


