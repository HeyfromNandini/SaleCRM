package project.app.sale_crm.createlead

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import project.app.sale_crm.screens.SummaryScreen
import project.app.sale_crm.viewmodel.SummaryViewModel

@Composable
fun Summary(
    navController: NavHostController,
    contactId: String
) {
    val viewModel: SummaryViewModel = hiltViewModel()
    
    LaunchedEffect(contactId) {
        viewModel.getSummary(contactId)
    }

    SummaryScreen(
        navController = navController,
        contactId = contactId
    )
} 