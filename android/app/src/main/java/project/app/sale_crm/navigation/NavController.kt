package project.app.sale_crm.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import project.app.sale_crm.analytics.AnalyticsPage
import project.app.sale_crm.createlead.BestFollowUp
import project.app.sale_crm.createlead.ClientDetail
import project.app.sale_crm.createlead.CreateContactViewModel
import project.app.sale_crm.createlead.CreateLead
import project.app.sale_crm.createlead.ListDetail
import project.app.sale_crm.createlead.Summary
import project.app.sale_crm.createlead.Tips
import project.app.sale_crm.createlead.VoicetoCRM
import project.app.sale_crm.dashboard.Dashboard
import project.app.sale_crm.screens.QuestionScreen
import project.app.sale_crm.voiceToText.SpeechToText


@Composable
fun MainNavController(
    paddingValues: PaddingValues,
    navController: NavHostController,
) {
    val context = LocalContext.current
    val createContactViewModel: CreateContactViewModel = hiltViewModel()

    NavHost(
        navController = navController,
        startDestination = Screens.SplashScreen.route
    ) {
        composable(Screens.Dashboard.route) {
            Dashboard(navHostController = navController, name = "Vignesh Gadhari",
                pfp = "https://avatars.githubusercontent.com/u/124498042?v=4")
        }
        composable(Screens.SplashScreen.route) {
            SplashScreen(navController = navController)
        }

        composable(Screens.CreateLead.route) {
            CreateLead(navController = navController, createContactViewModel = createContactViewModel)
        }

        composable(Screens.ListDetail.route) {
            ListDetail(navController = navController, createContactViewModel = createContactViewModel)
        }
        composable(Screens.VoicetoCRM.route) {
            VoicetoCRM(navController = navController)
        }
        composable(Screens.AnalyticsPage.route) {
            AnalyticsPage(navController = navController)
        }

        composable(Screens.SpeechtoText.route) {
            SpeechToText(navController = navController, createContactViewModel)
        }
        composable(Screens.ClientDetails.route) {
            ClientDetail(navController = navController, createContactViewModel = createContactViewModel)
        }

        composable(Screens.Tips.route) { backStackEntry ->
            val contactId = backStackEntry.arguments?.getString("contactId")
            Tips(navController = navController, contactId = contactId ?: "")
        }

        composable(Screens.BestFollowUp.route) { backStackEntry ->
            val contactId = backStackEntry.arguments?.getString("contactId")
            BestFollowUp(navController = navController, contactId = contactId ?: "")
        }

        composable(Screens.Summary.route) { backStackEntry ->
            val contactId = backStackEntry.arguments?.getString("contactId")
            Summary(navController = navController, contactId = contactId ?: "")
        }

        composable(
            route = Screens.Question.route,
            arguments = listOf(
                navArgument("contactId") { 
                    type = NavType.StringType
                    nullable = false
                }
            )
        ) { backStackEntry ->
            val contactId = backStackEntry.arguments?.getString("contactId")
            QuestionScreen(
                navController = navController,
                contactId = contactId ?: ""
            )
        }
    }
}