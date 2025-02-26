package project.app.sale_crm.service

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.client.utils.EmptyContent.headers
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import project.app.sale_crm.createlead.Contact
import project.app.sale_crm.models.createContact.CreateContactsRequest
import project.app.sale_crm.models.createContact.CreateContactsResponse
import project.app.sale_crm.models.fetchContact.FetchContactsResponse
import project.app.sale_crm.models.fetchContactById.FetchContactByIdRequest
import project.app.sale_crm.models.fetchContactById.FetchContactByIdResponse
import project.app.sale_crm.models.voiceToCrm.VoiceToCRMResponse
import project.app.sale_crm.models.voiceToCrm.VoiceToCrmRequest

class ApiServiceImpl(
    private val client: HttpClient,
) : ApiService {
    override suspend fun fetchContacts(): Result<FetchContactsResponse> {
        return try {
            client.get {
                url("${ApiRoutes.BASE_URL}/contacts")
                header(HttpHeaders.ContentType, ContentType.Application.Json)
            }.body<FetchContactsResponse>().let {
                Result.success(it)
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun createContact(contact: CreateContactsRequest): Result<CreateContactsResponse> {
        return try {
            client.post {
                url("${ApiRoutes.BASE_URL}/contacts/create")
                header(HttpHeaders.ContentType, ContentType.Application.Json)
                println("createContactsResponse56: $contact")
                println("createContactsResponse57: ${
                    Json.encodeToString(contact)
                }")
                setBody(contact)
                headers {
                    append("Content-Type", "application/json")
                }
            }.body<CreateContactsResponse>().let {
                println("createContactResponse1: $it")
                Result.success(it)
            }
        } catch (e: Exception) {
            println("createContactResponse2: $e")
            Result.failure(e)
        }
    }

    override suspend fun fetchContactById(fetchContactByIdRequest: FetchContactByIdRequest):
            Result<FetchContactByIdResponse> {
        return try {
            client.get {
                url("${ApiRoutes.BASE_URL}/contacts/getbyid/")
                header(HttpHeaders.ContentType, ContentType.Application.Json)
                setBody(fetchContactByIdRequest)
            }.body<FetchContactByIdResponse>().let {
                println("Contacts11 $it ${Json.encodeToString(it)}")
                Result.success(it)
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun voiceToCRM(voiceToCrmRequest: VoiceToCrmRequest): Result<VoiceToCRMResponse> {
        return try  {
            client.post {
                url("${ApiRoutes.BASE_URL}/ai/voice2crm")
                header(HttpHeaders.ContentType, ContentType.Application.Json)
                setBody(voiceToCrmRequest)
            }.body<VoiceToCRMResponse>().let {
                Result.success(it)
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}