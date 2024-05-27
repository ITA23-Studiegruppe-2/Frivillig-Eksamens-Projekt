
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

class ConversationViewModel : ViewModel() {
    private val chatRepository = ChatRepository()
    var backgroundColor by mutableStateOf(Color(0xFFC8D5B9))


    // Mutable state to hold the list of conversations
    var conversations by mutableStateOf<List<Conversation>>(listOf())

    // Mutable state to hold the current user's ID
    var currentUserId by mutableStateOf<String?>(null)
    var currentOrgId by mutableStateOf<String?>(null)



    init {
        // Get current user
        FirebaseAuth.getInstance().addAuthStateListener { firebaseAuth ->
            currentUserId = firebaseAuth.currentUser?.uid

            currentUserId?.let { userId ->
                viewModelScope.launch {

                    currentOrgId = chatRepository.getOrgId(userId)?.orgID
                    Log.d("ConversationViewModel", "Fetched org ID: $currentOrgId")
                    fetchMessages(userId)

                    currentOrgId?.let { orgId ->
                        fetchMessagesForOrg(orgId)
                    }
                }
            }
        }
    }




    // Function to fetch messages (USERID)
    fun fetchMessages(userId: String) {
        viewModelScope.launch {
            Log.d("ChatViewModel", "Fetching messages for user ID: $userId")
            try {
                val userConversations = chatRepository.getConversationsByUserId(userId)
                conversations = conversations + userConversations
                Log.d("ChatViewModel", "Fetched messages: ${conversations.size}")
            } catch (e: Exception) {
                Log.e("ChatViewModel", "Error fetching messages: ${e.message}")
            }
        }
    }


    // Function to fetch messages (ORGID)
    fun fetchMessagesForOrg(orgId: String) {
        viewModelScope.launch {
            Log.d("ChatViewModel", "Fetching messages for org ID: $orgId")
            try {
                val orgConversations = chatRepository.getConversationsByOrgId(orgId)
                conversations = conversations + orgConversations
                Log.d("ChatViewModel", "Fetched messages: ${conversations.size}")
            } catch (e: Exception) {
                Log.e("ChatViewModel", "Error fetching messages: ${e.message}")
            }
        }
    }
}