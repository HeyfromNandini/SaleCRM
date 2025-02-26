package project.app.sale_crm.models.fetchContact


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Next(
    @SerialName("after")
    val after: String? = null,
    @SerialName("link")
    val link: String? = null
)