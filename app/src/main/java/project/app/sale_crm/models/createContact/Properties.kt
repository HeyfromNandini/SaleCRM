package project.app.sale_crm.models.createContact


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
    @SerialName("gender")
    val gender: String? = null,
    @SerialName("hs_all_contact_vids")
    val hsAllContactVids: String? = null,
    @SerialName("hs_content_membership_notes")
    val hsContentMembershipNotes: String? = null,
    @SerialName("hs_currently_enrolled_in_prospecting_agent")
    val hsCurrentlyEnrolledInProspectingAgent: String? = null,
    @SerialName("hs_email_domain")
    val hsEmailDomain: String? = null,
    @SerialName("hs_is_contact")
    val hsIsContact: String? = null,
    @SerialName("hs_is_unworked")
    val hsIsUnworked: String? = null,
    @SerialName("hs_lifecyclestage_lead_date")
    val hsLifecyclestageLeadDate: String? = null,
    @SerialName("hs_membership_has_accessed_private_content")
    val hsMembershipHasAccessedPrivateContent: String? = null,
    @SerialName("hs_object_id")
    val hsObjectId: String? = null,
    @SerialName("hs_object_source")
    val hsObjectSource: String? = null,
    @SerialName("hs_object_source_id")
    val hsObjectSourceId: String? = null,
    @SerialName("hs_object_source_label")
    val hsObjectSourceLabel: String? = null,
    @SerialName("hs_pipeline")
    val hsPipeline: String? = null,
    @SerialName("hs_registered_member")
    val hsRegisteredMember: String? = null,
    @SerialName("industry")
    val industry: String? = null,
    @SerialName("language")
    val language: String? = null,
    @SerialName("lastmodifieddate")
    val lastmodifieddate: String? = null,
    @SerialName("lifecyclestage")
    val lifecyclestage: String? = null,
    @SerialName("next_activity_date")
    val nextActivityDate: Int? = null,
    @SerialName("priority_level")
    val priorityLevel: String? = null,
    @SerialName("socio_economic_segment")
    val socioEconomicSegment: String? = null,
    @SerialName("website")
    val website: String? = null,
    val lastname: String = "KKK",
    val firstname: String = "KKK",
    val phoneName: String = "KKK",
)