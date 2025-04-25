package project.app.sale_crm.createlead

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import project.app.sale_crm.screens.BestFollowUpScreen

@Composable
fun BestFollowUp(
    navController: NavController,
    contactId: String,
    viewModel: BestFollowUpViewModel = hiltViewModel()
) {
    LaunchedEffect(contactId) {
        viewModel.getBestFollowUp(contactId)
    }

    BestFollowUpScreen(
        contactId = contactId,
        apiService = viewModel.apiService
    )
} 