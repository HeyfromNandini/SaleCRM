package project.app.sale_crm.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.hilt.navigation.compose.hiltViewModel
import project.app.sale_crm.viewmodel.SummaryViewModel

@Composable
fun SummaryScreen(
    navController: NavHostController,
    contactId: String,
    viewModel: SummaryViewModel = hiltViewModel()
) {
    Log.d("Summary", "SummaryScreen initialized with contactId: $contactId")

    LaunchedEffect(contactId) {
        Log.d("Summary", "LaunchedEffect triggered, calling getSummary")
        viewModel.getSummary(contactId)
    }

    val summaryResponse by viewModel.summaryResponse.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp)
    ) {
        when {
            isLoading -> {
                Log.d("Summary", "Showing loading indicator")
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = Color.White
                )
            }
            summaryResponse != null -> {
                summaryResponse?.fold(
                    onSuccess = { response ->
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .verticalScroll(rememberScrollState())
                        ) {
                            // Contact Details
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Color.DarkGray, RoundedCornerShape(10.dp))
                                    .padding(16.dp)
                            ) {
                                Column {
                                    Text(
                                        text = "${response.firstname ?: ""} ${response.lastname ?: ""}",
                                        fontSize = 24.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White
                                    )
                                    response.industry?.let { DetailText("Industry", it) }
                                    response.city?.let { DetailText("Region", it) }
                                    response.language?.let { DetailText("Language", it) }
                                    response.company?.let { DetailText("Company", it) }
                                }
                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            // Summary Section
                            response.summary?.let { summary ->
                                SummarySection("Summary") {
                                    Text(
                                        text = summary,
                                        color = Color.White,
                                        lineHeight = 24.sp
                                    )
                                }
                            }

                            // Priority Tag
                            response.priority_tag?.let { tag ->
                                SummarySection("Priority") {
                                    Text(
                                        text = tag,
                                        color = Color.White,
                                        lineHeight = 24.sp
                                    )
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
                                text = "Error loading summary: ${error.message}",
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
                        text = "No summary available",
                        color = Color.White,
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}

@Composable
private fun SummarySection(
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
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        content()
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