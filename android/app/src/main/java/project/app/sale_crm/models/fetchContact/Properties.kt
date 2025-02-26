package project.app.sale_crm.models.fetchContact


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Properties(
    @SerialName("age_group")
    val ageGroup: String? = null,
    @SerialName("city")
    val city: String? = null,
    @SerialName("company")
    val company: String? = null,
    @SerialName("createdate")
    val createdate: String? = null,
    @SerialName("cultural_affinity")
    val culturalAffinity: String? = null,
    @SerialName("email")
    val email: String? = null,
    @SerialName("firstname")
    val firstname: String? = null,
    @SerialName("gender")
    val gender: String? = null,
    @SerialName("hs_content_membership_notes")
    val hsContentMembershipNotes: String? = null,
    @SerialName("hs_object_id")
    val hsObjectId: String? = null,
    @SerialName("industry")
    val industry: String? = null,
    @SerialName("language")
    val language: String? = null,
    @SerialName("lastmodifieddate")
    val lastmodifieddate: String? = null,
    @SerialName("lastname")
    val lastname: String? = null,
    @SerialName("next_activity_date")
    val nextActivityDate: String? = null,
    @SerialName("phone")
    val phone: String? = null,
    @SerialName("priority_level")
    val priorityLevel: String? = null,
    @SerialName("socio_economic_segment")
    val socioEconomicSegment: String? = null,
    @SerialName("website")
    val website: String? = null
)