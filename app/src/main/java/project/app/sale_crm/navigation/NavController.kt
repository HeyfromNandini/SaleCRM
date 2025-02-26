package project.app.sale_crm.navigation


import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import project.app.sale_crm.analytics.AnalyticsPage
import project.app.sale_crm.createlead.ClientDetail
import project.app.sale_crm.createlead.CreateContactViewModel
import project.app.sale_crm.createlead.CreateLead
import project.app.sale_crm.createlead.ListDetail
import project.app.sale_crm.createlead.Tips
import project.app.sale_crm.createlead.VoicetoCRM
import project.app.sale_crm.dashboard.Dashboard
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
        startDestination = Screens.SplashScreen.route,
    ) {

        composable(Screens.Dashboard.route) {
            Dashboard(navHostController = navController, name = "Nandini",
                pfp = "https://firebasestorage.googleapis.com/v0/b/ai-travel-manager.appspot.com/o/Nandini.png?alt=media&token=5e151259-ce49-479d-93ed-ecf91deaba38")
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

        composable(Screens.Tips.route) {
            Tips(navController = navController)
        }



    }
}