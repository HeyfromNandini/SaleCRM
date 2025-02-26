package project.app.sale_crm.createlead

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import project.app.sale_crm.models.createContact.CreateContactsRequest
import project.app.sale_crm.models.createContact.CreateContactsResponse
import project.app.sale_crm.models.fetchContact.Result
import project.app.sale_crm.models.fetchContactById.FetchContactByIdResponse
import project.app.sale_crm.models.fetchContactById.toCreateContactsResponse
import project.app.sale_crm.models.voiceToCrm.VoiceToCrmRequest
import project.app.sale_crm.models.voiceToCrm.toCreateContactsResponse
import project.app.sale_crm.service.ApiService
import javax.inject.Inject

@HiltViewModel
class CreateContactViewModel @Inject constructor(
    application: Application,
    private val repository: ApiService,
) : AndroidViewModel(application) {

    private val _contactsList :MutableStateFlow<List<project.app.sale_crm.models.fetchContact.Result?>> = MutableStateFlow(emptyList())
    val contactsList :StateFlow<List<project.app.sale_crm.models.fetchContact.Result?>> = _contactsList.asStateFlow()

    private val _createContactResponse :MutableStateFlow<CreateContactsResponse?> = MutableStateFlow(null)
    val createContactResponse :StateFlow<CreateContactsResponse?> = _createContactResponse.asStateFlow()

    private val _contactsLists :MutableStateFlow<List<project.app.sale_crm.models.fetchContact.Result>> = MutableStateFlow(emptyList())
    val contactsLists :StateFlow<List<project.app.sale_crm.models.fetchContact.Result>> = _contactsLists.asStateFlow()

    private val _contactByIdResponse :MutableStateFlow<FetchContactByIdResponse?> = MutableStateFlow(null)
    val contactByIdResponse :StateFlow<FetchContactByIdResponse?> = _contactByIdResponse.asStateFlow()

    fun fetchContacts() {
        viewModelScope.launch {
            _contactsList.value = repository.fetchContacts().getOrNull()?.results ?: emptyList()
        }
    }

    fun createContact(contact: CreateContactsRequest) {
        viewModelScope.launch {
            _createContactResponse.value = repository.createContact(contact).getOrNull()
        }
    }

    fun getContacts() {
        viewModelScope.launch {
            _contactsLists.value = repository.fetchContacts().getOrNull()?.results?.filterNotNull() ?: emptyList()
        }
    }

    fun getContactById(id: String) {
        viewModelScope.launch {
            _createContactResponse.value = repository.fetchContactById(project.app.sale_crm.models.fetchContactById.FetchContactByIdRequest(id)).getOrNull()?.toCreateContactsResponse()
        }
    }

    fun voiceToCRM(text: String){
        viewModelScope.launch {
            _createContactResponse.value = repository.voiceToCRM(VoiceToCrmRequest(userInput = text)).getOrNull()?.toCreateContactsResponse()
        }
    }
}