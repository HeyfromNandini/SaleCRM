package project.app.sale_crm.model

import kotlinx.serialization.Serializable

@Serializable
data class SummaryResponse(
        val firstname: String?,
        val lastname: String?,
        val age_group: String?,
        val city: String?,
        val company: String?,
        val createdate: String?,
        val lastmodifieddate: String?,
        val cultural_affinity: String?,
        val email: String?,
        val phone: String?,
        val gender: String?,
        val hs_object_id: String?,
        val industry: String?,
        val language: String?,
        val next_activity_date: String?,
        val socio_economic_segment: String?,
        val website: String?,
        val summary: String?,
        val priority_tag: String?,
        val recent_interaction: Long?
)

@Serializable
data class ContactDetails(
        val name: String?,
        val culturalAffinity: String?,
        val region: String?,
        val industry: String?,
        val language: String?,
        val socioEconomicSegment: String?
)

@Serializable
data class DoAndDonts(
    val dos: String? = null,
    val dont: String? = null
)

@Serializable
data class PersonalizedStrategy(
        val culturalConsiderations: String?,
        val communicationApproach: String?,
        val timingRecommendations: String?,
        val businessEtiquette: String?,
        val conversationStarters: String?,
        val festivalOpportunities: String?,
        val doAndDonts: DoAndDonts?
)

@Serializable
data class BestFollowUpResponse(
        val contactDetails: ContactDetails?,
        val aiInsights: BestFollowUpInsights?
)

@Serializable
data class BestFollowUpInsights(val personalizedStrategy: PersonalizedStrategy?)

@Serializable
data class CulturalNuances(
        val keyConsiderations: String?,
        val traditions: String?,
        val etiquette: String?
)

@Serializable
data class RegionalInsights(
        val businessCulture: String?,
        val localCustoms: String?,
        val languageTips: String?,
        val festivalCalendar: String?,
        val businessProtocols: String?,
        val marketDynamics: String?,
        val industryTrends: String?,
        val culturalNuances: CulturalNuances?
)

@Serializable
data class TipsResponse(val contactDetails: ContactDetails?, val aiInsights: TipsInsights?)

@Serializable
data class TipsInsights(val regionalInsights: RegionalInsights?)
