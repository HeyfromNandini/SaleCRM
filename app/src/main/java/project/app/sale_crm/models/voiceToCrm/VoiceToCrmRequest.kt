package project.app.sale_crm.models.voiceToCrm



import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VoiceToCrmRequest(
    @SerialName("userInput")
    val userInput: String? = null
)