package project.app.sale_crm.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import project.app.sale_crm.model.SummaryResponse
import project.app.sale_crm.service.ApiService
import javax.inject.Inject

@HiltViewModel
class SummaryViewModel @Inject constructor(
    private val apiService: ApiService
) : ViewModel() {
    
    private val _summaryResponse = MutableStateFlow<Result<SummaryResponse>?>(null)
    val summaryResponse: StateFlow<Result<SummaryResponse>?> = _summaryResponse

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun getSummary(contactId: String) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                Log.d("SummaryViewModel", "Fetching summary for contact: $contactId")
                _summaryResponse.value = apiService.getSummary(contactId)
            } catch (e: Exception) {
                Log.e("SummaryViewModel", "Error fetching summary", e)
                _summaryResponse.value = Result.failure(e)
            } finally {
                _isLoading.value = false
            }
        }
    }
} 