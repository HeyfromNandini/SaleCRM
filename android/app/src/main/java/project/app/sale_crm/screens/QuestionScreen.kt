package project.app.sale_crm.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.hilt.navigation.compose.hiltViewModel
import project.app.sale_crm.viewmodel.QuestionViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuestionScreen(
    navController: NavHostController,
    contactId: String,
    viewModel: QuestionViewModel = hiltViewModel()
) {
    var question by remember { mutableStateOf("") }
    val questionResponse by viewModel.questionResponse.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            // Question Input Section
            OutlinedTextField(
                value = question,
                onValueChange = { question = it },
                label = { Text("Ask a question", color = Color.LightGray) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    focusedBorderColor = Color.White,
                    unfocusedBorderColor = Color.Gray,
                    cursorColor = Color.White,
                    focusedLabelColor = Color.LightGray,
                    unfocusedLabelColor = Color.LightGray
                )
            )

            // Ask Button
            Button(
                onClick = { 
                    if (question.isNotBlank()) {
                        viewModel.askQuestion(contactId, question)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black
                )
            ) {
                Text("Ask Question")
            }

            // Loading Indicator
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    color = Color.White
                )
            }

            // Response Section
            questionResponse?.fold(
                onSuccess = { response ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.DarkGray, RoundedCornerShape(10.dp))
                            .padding(16.dp)
                    ) {
                        Text(
                            text = response,
                            color = Color.White,
                            lineHeight = 24.sp
                        )
                    }
                },
                onFailure = { error ->
                    Text(
                        text = "Error: ${error.message}",
                        color = Color.Red,
                        modifier = Modifier.padding(top = 16.dp)
                    )
                }
            )
        }
    }
} 