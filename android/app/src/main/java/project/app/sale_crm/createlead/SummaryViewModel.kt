package project.app.sale_crm.createlead

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import project.app.sale_crm.model.SummaryResponse
import project.app.sale_crm.service.ApiService
import javax.inject.Inject

@HiltViewModel
class SummaryViewModel @Inject constructor(
    val apiService: ApiService
) : ViewModel() {

    private val _summaryResponse = MutableStateFlow<Result<SummaryResponse>?>(null)
    val summaryResponse: StateFlow<Result<SummaryResponse>?> = _summaryResponse.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    fun getSummary(contactId: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _summaryResponse.value = apiService.getSummary(contactId)
            _isLoading.value = false
        }
    }
} 