package project.app.sale_crm.service

import project.app.sale_crm.createlead.Contact
import project.app.sale_crm.models.createContact.CreateContactsRequest
import project.app.sale_crm.models.createContact.CreateContactsResponse
import project.app.sale_crm.models.fetchContact.FetchContactsResponse
import project.app.sale_crm.models.fetchContactById.FetchContactByIdRequest
import project.app.sale_crm.models.fetchContactById.FetchContactByIdResponse
import project.app.sale_crm.models.voiceToCrm.VoiceToCRMResponse
import project.app.sale_crm.models.voiceToCrm.VoiceToCrmRequest

interface ApiService {
    suspend fun fetchContacts(): Result<FetchContactsResponse>

    suspend fun createContact(contact: CreateContactsRequest): Result<CreateContactsResponse>

    suspend fun fetchContactById(fetchContactByIdRequest: FetchContactByIdRequest): Result<FetchContactByIdResponse>

    suspend fun voiceToCRM(voiceToCrmRequest: VoiceToCrmRequest) : Result<VoiceToCRMResponse>
}