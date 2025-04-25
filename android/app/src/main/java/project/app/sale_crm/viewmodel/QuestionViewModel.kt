package project.app.sale_crm.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import project.app.sale_crm.service.ApiService
import javax.inject.Inject

@HiltViewModel
class QuestionViewModel @Inject constructor(
    private val apiService: ApiService
) : ViewModel() {
    private val _questionResponse = MutableStateFlow<Result<String>?>(null)
    val questionResponse: StateFlow<Result<String>?> = _questionResponse

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun askQuestion(contactId: String, question: String) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                Log.d("Question", "Asking question for contact: $contactId")
                _questionResponse.value = apiService.askQuestion(contactId, question)
            } catch (e: Exception) {
                Log.e("Question", "Error asking question", e)
                _questionResponse.value = Result.failure(e)
            } finally {
                _isLoading.value = false
            }
        }
    }
} 