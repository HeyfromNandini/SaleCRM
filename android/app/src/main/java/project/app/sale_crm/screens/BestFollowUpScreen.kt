package project.app.sale_crm.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import project.app.sale_crm.model.BestFollowUpResponse
import project.app.sale_crm.service.ApiService

@Composable
fun BestFollowUpScreen(
    contactId: String,
    apiService: ApiService
) {
    val followUpResponse = remember { MutableStateFlow<Result<BestFollowUpResponse>?>(null) }
    val isLoading = remember { MutableStateFlow(false) }

    LaunchedEffect(contactId) {
        isLoading.value = true
        followUpResponse.value = apiService.getBestFollowUp(contactId)
        isLoading.value = false
    }

    val response by followUpResponse.collectAsState()
    val loading by isLoading.collectAsState()

    BestFollowUpContent(
        followUpResponse = followUpResponse,
        isLoading = isLoading
    )
}

@Composable
private fun BestFollowUpContent(
    followUpResponse: StateFlow<Result<BestFollowUpResponse>?>,
    isLoading: StateFlow<Boolean>
) {
    val response by followUpResponse.collectAsState()
    val loading by isLoading.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp)
    ) {
        if (loading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
                color = Color.White
            )
        } else {
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
                                        text = details.name ?: "Contact",
                                        fontSize = 24.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White
                                    )
                                    details.industry?.let { DetailText("Industry", it) }
                                    details.region?.let { DetailText("Region", it) }
                                    details.language?.let { DetailText("Language", it) }
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        // Personalized Strategy
                        response.aiInsights?.personalizedStrategy?.let { strategy ->
                            // Cultural Considerations
                            StrategySection("Cultural Considerations") {
                                Text(
                                    text = strategy.culturalConsiderations ?: "",
                                    color = Color.White,
                                    lineHeight = 24.sp
                                )
                            }

                            // Communication Approach
                            StrategySection("Communication Approach") {
                                Text(
                                    text = strategy.communicationApproach ?: "",
                                    color = Color.White,
                                    lineHeight = 24.sp
                                )
                            }

                            // Timing Recommendations
                            StrategySection("Timing Recommendations") {
                                Text(
                                    text = strategy.timingRecommendations ?: "",
                                    color = Color.White,
                                    lineHeight = 24.sp
                                )
                            }

                            // Business Etiquette
                            StrategySection("Business Etiquette") {
                                Text(
                                    text = strategy.businessEtiquette ?: "",
                                    color = Color.White,
                                    lineHeight = 24.sp
                                )
                            }

                            // Conversation Starters
                            StrategySection("Conversation Starters") {
                                Text(
                                    text = strategy.conversationStarters ?: "",
                                    color = Color.White,
                                    lineHeight = 24.sp,
                                    modifier = Modifier.padding(vertical = 4.dp)
                                )
                            }

                            // Festival Opportunities
                            StrategySection("Festival Opportunities") {
                                Text(
                                    text = strategy.festivalOpportunities ?: "",
                                    color = Color.White,
                                    lineHeight = 24.sp,
                                    modifier = Modifier.padding(vertical = 4.dp)
                                )
                            }

                            // Do's and Don'ts
                            strategy.doAndDonts?.let { doAndDonts ->
                                if (!doAndDonts.dos.isNullOrEmpty()) {
                                    StrategySection("Do's") {
                                        Text(
                                            text = doAndDonts.dos,
                                            color = Color.Green,
                                            lineHeight = 24.sp,
                                            modifier = Modifier.padding(vertical = 4.dp)
                                        )
                                    }
                                }

                                if (!doAndDonts.dont.isNullOrEmpty()) {
                                    StrategySection("Don'ts") {
                                        Text(
                                            text = doAndDonts.dont,
                                            color = Color.Red,
                                            lineHeight = 24.sp,
                                            modifier = Modifier.padding(vertical = 4.dp)
                                        )
                                    }
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
                            text = "Error loading follow-up strategy: ${error.message}",
                            color = Color.Red,
                            fontSize = 16.sp
                        )
                    }
                }
            )
        }
    }
}

@Composable
private fun DetailText(label: String, value: String) {
    Column(modifier = Modifier.padding(vertical = 4.dp)) {
        Text(
            text = label,
            fontSize = 14.sp,
            color = Color.LightGray
        )
        Text(
            text = value,
            fontSize = 16.sp,
            color = Color.White,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Composable
private fun StrategySection(
    title: String,
    content: @Composable () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(
            text = title,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
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