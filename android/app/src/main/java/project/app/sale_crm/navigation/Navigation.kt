package project.app.sale_crm.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import project.app.sale_crm.service.ApiService
import project.app.sale_crm.screens.BestFollowUpScreen
import project.app.sale_crm.screens.QuestionScreen
import project.app.sale_crm.screens.SummaryScreen
import project.app.sale_crm.screens.TipsScreen

@Composable
fun Navigation(
    navController: NavHostController,
    apiService: ApiService
) {
    NavHost(
        navController = navController,
        startDestination = Screens.Tips.route
    ) {
        composable(
            route = Screens.Tips.route,
            arguments = listOf(navArgument("contactId") { type = NavType.StringType })
        ) { backStackEntry ->
            val contactId = backStackEntry.arguments?.getString("contactId") ?: return@composable
            TipsScreen(
                contactId = contactId,
                apiService = apiService
            )
        }

        composable(
            route = Screens.BestFollowUp.route,
            arguments = listOf(navArgument("contactId") { type = NavType.StringType })
        ) { backStackEntry ->
            val contactId = backStackEntry.arguments?.getString("contactId") ?: return@composable
            BestFollowUpScreen(
                contactId = contactId,
                apiService = apiService
            )
        }

        composable(
            route = Screens.Summary.route,
            arguments = listOf(navArgument("contactId") { type = NavType.StringType })
        ) { backStackEntry ->
            val contactId = backStackEntry.arguments?.getString("contactId") ?: return@composable
            SummaryScreen(
                navController = navController,
                contactId = contactId
            )
        }

        composable(
            route = Screens.Question.route,
            arguments = listOf(navArgument("contactId") { type = NavType.StringType })
        ) { backStackEntry ->
            val contactId = backStackEntry.arguments?.getString("contactId") ?: return@composable
            QuestionScreen(
                navController = navController,
                contactId = contactId
            )
        }
    }
} 