package project.app.sale_crm.createlead

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import project.app.sale_crm.dashboard.CustomCardButton
import project.app.sale_crm.model.TipsResponse
import project.app.sale_crm.service.ApiService
import project.app.sale_crm.service.ApiServiceImpl
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import project.app.sale_crm.screens.TipsScreen

@Composable
fun Tips(
    navController: NavController,
    contactId: String,
    viewModel: TipsViewModel = hiltViewModel()
) {
    Log.d("Tips", "Tips composable initialized with contactId: $contactId")
    
    LaunchedEffect(contactId) {
        Log.d("Tips", "LaunchedEffect triggered, calling getTips")
        viewModel.getTips(contactId)
    }

    val tipsResponse by viewModel.tipsResponse.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    
    Log.d("Tips", "Current tipsResponse: $tipsResponse")
    Log.d("Tips", "isLoading: $isLoading")

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp)
    ) {
        when {
            isLoading -> {
                Log.d("Tips", "Showing loading indicator")
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = Color.White
                )
            }
            tipsResponse != null -> {
                tipsResponse?.fold(
                    onSuccess = { response ->
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .verticalScroll(rememberScrollState())
                        ) {
                            // Contact Details
                            response.contactDetails?.let { details ->
                                Log.d("Tips", "Rendering contact details: $details")
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

                            // Regional Insights
                            response.aiInsights?.regionalInsights?.let { insights ->
                                Log.d("Tips", "Rendering regional insights")
                                // Business Culture
                                InsightSection("Business Culture") {
                                    Text(
                                        text = insights.businessCulture ?: "",
                                        color = Color.White,
                                        lineHeight = 24.sp
                                    )
                                }

                                // Local Customs
                                InsightSection("Local Customs") {
                                    Text(
                                        text = insights.localCustoms ?: "",
                                        color = Color.White,
                                        lineHeight = 24.sp
                                    )
                                }

                                // Language Tips
                                InsightSection("Language Tips") {
                                    Text(
                                        text = insights.languageTips ?: "",
                                        color = Color.White,
                                        lineHeight = 24.sp,
                                        modifier = Modifier.padding(vertical = 4.dp)
                                    )
                                }

                                // Festival Calendar
                                InsightSection("Festival Calendar") {
                                    Text(
                                        text = insights.festivalCalendar ?: "",
                                        color = Color.White,
                                        lineHeight = 24.sp,
                                        modifier = Modifier.padding(vertical = 4.dp)
                                    )
                                }

                                // Business Protocols
                                InsightSection("Business Protocols") {
                                    Text(
                                        text = insights.businessProtocols ?: "",
                                        color = Color.White,
                                        lineHeight = 24.sp
                                    )
                                }

                                // Market Dynamics
                                InsightSection("Market Dynamics") {
                                    Text(
                                        text = insights.marketDynamics ?: "",
                                        color = Color.White,
                                        lineHeight = 24.sp
                                    )
                                }

                                // Industry Trends
                                InsightSection("Industry Trends") {
                                    Text(
                                        text = insights.industryTrends ?: "",
                                        color = Color.White,
                                        lineHeight = 24.sp
                                    )
                                }

                                // Cultural Nuances
                                insights.culturalNuances?.let { nuances ->
                                    InsightSection("Key Considerations") {
                                        Text(
                                            text = nuances.keyConsiderations ?: "",
                                            color = Color.White,
                                            lineHeight = 24.sp,
                                            modifier = Modifier.padding(vertical = 4.dp)
                                        )
                                    }

                                    InsightSection("Traditions") {
                                        Text(
                                            text = nuances.traditions ?: "",
                                            color = Color.White,
                                            lineHeight = 24.sp,
                                            modifier = Modifier.padding(vertical = 4.dp)
                                        )
                                    }

                                    InsightSection("Etiquette") {
                                        Text(
                                            text = nuances.etiquette ?: "",
                                            color = Color.White,
                                            lineHeight = 24.sp,
                                            modifier = Modifier.padding(vertical = 4.dp)
                                        )
                                    }
                                }
                            }
                        }
                    },
                    onFailure = { error ->
                        Log.e("Tips", "Error displaying tips", error)
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Error loading tips: ${error.message}",
                                color = Color.Red,
                                fontSize = 16.sp
                            )
                        }
                    }
                )
            }
            else -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No tips available",
                        color = Color.White,
                        fontSize = 16.sp
                    )
                }
            }
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

