package project.app.sale_crm.models.voiceToCrm


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import project.app.sale_crm.models.createContact.CreateContactsResponse
import project.app.sale_crm.models.createContact.Properties

@Serializable
data class VoiceToCRMResponse(
    @SerialName("age_group")
    val ageGroup: String? = null,
    @SerialName("city")
    val city: String? = null,
    @SerialName("company")
    val company: String? = null,
    @SerialName("contactorcompany")
    val contactorcompany: String? = null,
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
    @SerialName("industry")
    val industry: String? = null,
    @SerialName("language")
    val language: String? = null,
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

fun VoiceToCRMResponse.toCreateContactsResponse(): CreateContactsResponse {
    return CreateContactsResponse(
        archived = null,
        createdAt = null,
        id = null,
        properties = Properties(
            ageGroup = this.ageGroup,
            city = this.city,
            company = this.company ?: this.contactorcompany,
            createdate = null,
            culturalAffinity = this.culturalAffinity,
            email = this.email,
            gender = this.gender,
            hsAllContactVids = null,
            hsContentMembershipNotes = this.hsContentMembershipNotes,
            hsCurrentlyEnrolledInProspectingAgent = null,
            hsEmailDomain = null,
            hsIsContact = null,
            hsIsUnworked = null,
            hsLifecyclestageLeadDate = null,
            hsMembershipHasAccessedPrivateContent = null,
            hsObjectId = null,
            hsObjectSource = null,
            hsObjectSourceId = null,
            hsObjectSourceLabel = null,
            hsPipeline = null,
            hsRegisteredMember = null,
            industry = this.industry,
            language = this.language,
            lastmodifieddate = null,
            lifecyclestage = null,
            nextActivityDate = this.nextActivityDate?.toInt(),
            priorityLevel = this.priorityLevel,
            socioEconomicSegment = this.socioEconomicSegment,
            website = this.website,
            lastname = this.lastname ?: "KKK",
            firstname = this.firstname ?: "KKK",
            phoneName = this.phone ?: "KKK"
        ),
        updatedAt = null
    )
}
