package project.app.sale_crm.models.fetchContactById


import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable
import project.app.sale_crm.models.createContact.CreateContactsResponse
import project.app.sale_crm.models.createContact.Properties

@Serializable
data class FetchContactByIdResponse(
    @SerializedName("archived")
    val archived: Boolean? = null,
    @SerializedName("createdAt")
    val createdAt: String? = null,
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("properties")
    val properties: FetchContactProperties? = null,
    @SerializedName("updatedAt")
    val updatedAt: String? = null
)


fun FetchContactByIdResponse.toCreateContactsResponse(): CreateContactsResponse {
    return CreateContactsResponse(
        archived = this.archived,
        createdAt = this.createdAt,
        id = this.id,
        properties = this.properties?.let { props ->
            Properties(
                ageGroup = props.ageGroup,
                city = props.city,
                company = props.company,
                createdate = props.createdate,
                culturalAffinity = props.culturalAffinity,
                email = props.email,
                gender = props.gender,
                hsContentMembershipNotes = props.hsContentMembershipNotes,
                hsObjectId = props.hsObjectId,
                industry = props.industry,
                language = props.language,
                lastmodifieddate = props.lastmodifieddate,
                priorityLevel = props.priorityLevel,
                socioEconomicSegment = props.socioEconomicSegment,
                website = props.website,
                lastname = props.lastname ?: "",
                firstname = props.firstname ?: "",
                phoneName = props.phone ?: ""
            )
        },
        updatedAt = this.updatedAt
    )
}