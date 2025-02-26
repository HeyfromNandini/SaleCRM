package project.app.sale_crm.navigation


sealed class Screens(val route: String) {

    object SplashScreen : Screens("splash")
    object Dashboard: Screens("dashboard")
    object CreateLead : Screens("createlead")
    object VoicetoCRM : Screens("voice2crm")
    object ListDetail : Screens("listdetails")
    object AnalyticsPage : Screens("analytics")

    object SpeechtoText : Screens("speechtotext")
    object ClientDetails : Screens("clientdetails")
    object Tips : Screens("tips")

}