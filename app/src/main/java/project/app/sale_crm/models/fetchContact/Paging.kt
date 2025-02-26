package project.app.sale_crm.models.fetchContact


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Paging(
    @SerialName("next")
    val next: Next? = null
)