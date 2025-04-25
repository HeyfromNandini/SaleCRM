package project.app.sale_crm.navigation


sealed class Screens(val route: String) {

    object SplashScreen : Screens("splash")
    object Dashboard: Screens("dashboard")
    object CreateLead : Screens("create_lead")
    object VoicetoCRM : Screens("voice2crm")
    object ListDetail : Screens("list_detail")
    object AnalyticsPage : Screens("analytics")

    object SpeechtoText : Screens("speechtotext")
    object ClientDetails : Screens("clientdetails")
    object Tips : Screens("tips/{contactId}") {
        fun createRoute(contactId: String) = "tips/$contactId"
    }
    object BestFollowUp : Screens("best_followup/{contactId}") {
        fun createRoute(contactId: String) = "best_followup/$contactId"
    }
    object Summary : Screens("summary/{contactId}") {
        fun createRoute(contactId: String) = "summary/$contactId"
    }
    
    object Question : Screens("question/{contactId}") {
        fun createRoute(contactId: String) = "question/$contactId"
    }

}