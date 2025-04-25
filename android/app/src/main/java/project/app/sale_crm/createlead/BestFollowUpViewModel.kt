package project.app.sale_crm.createlead

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import project.app.sale_crm.model.BestFollowUpResponse
import project.app.sale_crm.service.ApiService
import javax.inject.Inject

@HiltViewModel
class BestFollowUpViewModel @Inject constructor(
    val apiService: ApiService
) : ViewModel() {

    private val _followUpResponse = MutableStateFlow<Result<BestFollowUpResponse>?>(null)
    val followUpResponse: StateFlow<Result<BestFollowUpResponse>?> = _followUpResponse.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    fun getBestFollowUp(contactId: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _followUpResponse.value = apiService.getBestFollowUp(contactId)
            _isLoading.value = false
        }
    }
} 