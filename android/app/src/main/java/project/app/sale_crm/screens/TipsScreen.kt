package project.app.sale_crm.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import project.app.sale_crm.model.TipsResponse
import project.app.sale_crm.service.ApiService

@Composable
fun TipsScreen(
    contactId: String,
    apiService: ApiService
) {
    val tipsResponse = remember { MutableStateFlow<Result<TipsResponse>?>(null) }
    val isLoading = remember { MutableStateFlow(false) }

    LaunchedEffect(contactId) {
        isLoading.value = true
        tipsResponse.value = apiService.getRegionalTips(contactId)
        isLoading.value = false
    }

    TipsContent(
        tipsResponse = tipsResponse,
        isLoading = isLoading
    )
}

@Composable
private fun TipsContent(
    tipsResponse: StateFlow<Result<TipsResponse>?>,
    isLoading: StateFlow<Boolean>
) {
    val response by tipsResponse.collectAsState()
    val loading by isLoading.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp)
    ) {
        when {
            loading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = Color.White
                )
            }
            else -> {
                response?.fold(
                    onSuccess = { response ->
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .verticalScroll(rememberScrollState())
                        ) {
                            // Contact Details
                            response.contactDetails?.let { details ->
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(Color.DarkGray, RoundedCornerShape(10.dp))
                                        .padding(16.dp)
                                ) {
                                    Column {
                                        Text(
                                            text = (details.name ?: "Contact").toString()
                                        )
                                        details.industry?.let { DetailText("Industry", it.toString()) }
                                        details.region?.let { DetailText("Region", it.toString()) }
                                        details.language?.let { DetailText("Language", it.toString()) }
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            // Regional Insights
                            response.aiInsights?.regionalInsights?.let { insights ->
                                // Business Culture
                                InsightSection("Business Culture") {
                                    Text(
                                        text = (insights.businessCulture ?: "").toString()
                                    )
                                }

                                // Local Customs
                                InsightSection("Local Customs") {
                                    Text(
                                        text = (insights.localCustoms ?: "").toString()
                                    )
                                }

                                // Language Tips
                                InsightSection("Language Tips") {
                                    Text(
                                        text = (insights.languageTips ?: "").toString()
                                    )
                                }

                                // Festival Calendar
                                InsightSection("Festival Calendar") {
                                    Text(
                                        text = (insights.festivalCalendar ?: "").toString()
                                    )
                                }

                                // Business Protocols
                                InsightSection("Business Protocols") {
                                    Text(
                                        text = (insights.businessProtocols ?: "").toString()
                                    )
                                }

                                // Market Dynamics
                                InsightSection("Market Dynamics") {
                                    Text(
                                        text = (insights.marketDynamics ?: "").toString()
                                    )
                                }

                                // Industry Trends
                                InsightSection("Industry Trends") {
                                    Text(
                                        text = (insights.industryTrends ?: "").toString()
                                    )
                                }

                                // Cultural Nuances
                                insights.culturalNuances?.let { nuances ->
                                    InsightSection("Key Considerations") {
                                        Text(
                                            text = (nuances.keyConsiderations ?: "").toString()
                                        )
                                    }

                                    InsightSection("Traditions") {
                                        Text(
                                            text = (nuances.traditions ?: "").toString()
                                        )
                                    }

                                    InsightSection("Etiquette") {
                                        Text(
                                            text = (nuances.etiquette ?: "").toString()
                                        )
                                    }
                                }
                            }
                        }
                    },
                    onFailure = { error ->
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Error loading tips: ${error.message}".toString()
                            )
                        }
                    }
                )
            }
        }
    }
}

@Composable
private fun DetailText(label: String, value: String) {
    Column(modifier = Modifier.padding(vertical = 4.dp)) {
        Text(
            text = label.toString()
        )
        Text(
            text = value.toString()
        )
    }
}

@Composable
private fun InsightSection(
    title: String,
    content: @Composable () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(
            text = title.toString(),
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.DarkGray, RoundedCornerShape(10.dp))
                .padding(16.dp)
        ) {
            content()
        }
        Divider(
            color = Color.DarkGray,
            thickness = 1.dp,
            modifier = Modifier.padding(vertical = 8.dp)
        )
    }
} 