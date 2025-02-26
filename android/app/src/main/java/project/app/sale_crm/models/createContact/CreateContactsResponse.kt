package project.app.sale_crm.models.createContact


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateContactsResponse(
    @SerialName("archived")
    val archived: Boolean? = null,
    @SerialName("createdAt")
    val createdAt: String? = null,
    @SerialName("id")
    val id: String? = null,
    @SerialName("properties")
    val properties: Properties? = null,
    @SerialName("updatedAt")
    val updatedAt: String? = null
)