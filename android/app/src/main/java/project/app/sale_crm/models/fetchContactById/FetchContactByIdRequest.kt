package project.app.sale_crm.models.fetchContactById


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FetchContactByIdRequest(
    @SerialName("id")
    val id: String? = null
)