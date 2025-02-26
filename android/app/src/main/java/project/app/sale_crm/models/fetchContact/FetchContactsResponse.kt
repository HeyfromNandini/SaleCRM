package project.app.sale_crm.models.fetchContact


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FetchContactsResponse(
    @SerialName("paging")
    val paging: Paging? = null,
    @SerialName("results")
    val results: List<Result?>? = null
)