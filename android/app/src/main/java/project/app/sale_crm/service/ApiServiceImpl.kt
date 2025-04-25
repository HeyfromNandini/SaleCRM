package project.app.sale_crm.service

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import project.app.sale_crm.model.*
import project.app.sale_crm.models.createContact.CreateContactsRequest
import project.app.sale_crm.models.createContact.CreateContactsResponse
import project.app.sale_crm.models.fetchContact.FetchContactsResponse
import project.app.sale_crm.models.fetchContactById.FetchContactByIdRequest
import project.app.sale_crm.models.fetchContactById.FetchContactByIdResponse
import project.app.sale_crm.models.voiceToCrm.VoiceToCRMResponse
import project.app.sale_crm.models.voiceToCrm.VoiceToCrmRequest

class ApiServiceImpl(private val client: HttpClient) : ApiService {
    override suspend fun fetchContacts(): Result<FetchContactsResponse> = runCatching {
        client.get("${ApiRoutes.BASE_URL}/contacts").body()
    }

    override suspend fun createContact(contact: CreateContactsRequest): Result<CreateContactsResponse> = runCatching {
        client.post("${ApiRoutes.BASE_URL}/contacts") {
            contentType(ContentType.Application.Json)
            setBody(contact)
        }.body()
    }

    override suspend fun fetchContactById(
        fetchContactByIdRequest: FetchContactByIdRequest
    ): Result<FetchContactByIdResponse> = runCatching {
        client.get("${ApiRoutes.BASE_URL}/contacts/getbyid") {
            contentType(ContentType.Application.Json)
            setBody(fetchContactByIdRequest)
        }.body()
    }

    override suspend fun voiceToCRM(voiceToCrmRequest: VoiceToCrmRequest): Result<VoiceToCRMResponse> = runCatching {
        client.post("${ApiRoutes.BASE_URL}/voice-to-crm") {
            contentType(ContentType.Application.Json)
            setBody(voiceToCrmRequest)
        }.body()
    }

    override suspend fun getRegionalTips(id: String): Result<TipsResponse> = runCatching {
        client.post("${ApiRoutes.BASE_URL}/ai/regional-tips") {
            contentType(ContentType.Application.Json)
            setBody(mapOf("id" to id))
        }.body()
    }

    override suspend fun getBestFollowUp(id: String): Result<BestFollowUpResponse> = runCatching {
        client.post("${ApiRoutes.BASE_URL}/ai/best-followup") {
            contentType(ContentType.Application.Json)
            setBody(mapOf("id" to id))
        }.body()
    }

    override suspend fun getSummary(id: String): Result<SummaryResponse> = runCatching {
        client.post("${ApiRoutes.BASE_URL}/ai/summary") {
            contentType(ContentType.Application.Json)
            setBody(mapOf("id" to id))
        }.body()
    }

    override suspend fun deleteContact(contactId: String): Result<Boolean> = runCatching {
        client.delete("${ApiRoutes.BASE_URL}/contacts/$contactId").body()
    }

    override suspend fun askQuestion(contactId: String, question: String): Result<String> = runCatching {
        val response = client.post("${ApiRoutes.BASE_URL}/ai/question") {
            contentType(ContentType.Application.Json)
            setBody(mapOf(
                "id" to contactId,
                "question" to question
            ))
        }.body<Map<String, String>>()
        response["response"] ?: throw IllegalStateException("No response received")
    }
}