package project.app.sale_crm.createlead

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import project.app.sale_crm.model.TipsResponse
import project.app.sale_crm.service.ApiService
import javax.inject.Inject

@HiltViewModel
class TipsViewModel @Inject constructor(
    val apiService: ApiService
) : ViewModel() {

    private val _tipsResponse = MutableStateFlow<Result<TipsResponse>?>(null)
    val tipsResponse: StateFlow<Result<TipsResponse>?> = _tipsResponse.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    fun getTips(contactId: String) {
        viewModelScope.launch {
            _isLoading.value = true
            Log.d("TipsViewModel", "Fetching tips for contact ID: $contactId")
            try {
                val response = apiService.getRegionalTips(contactId)
                Log.d("TipsViewModel", "Tips response received: $response")
                _tipsResponse.value = response
                Log.d("TipsViewModel", "Tips response set in state: ${_tipsResponse.value}")
            } catch (e: Exception) {
                Log.e("TipsViewModel", "Error fetching tips", e)
                _tipsResponse.value = Result.failure(e)
            } finally {
                _isLoading.value = false
            }
        }
    }
} 