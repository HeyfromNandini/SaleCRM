package project.app.sale_crm.bottombar


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AcUnit
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AddShoppingCart
import androidx.compose.material.icons.filled.FormatListNumberedRtl
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import project.app.sale_crm.navigation.Screens


sealed class BottomBarClass(val route: String?, val title: String?, val icon: ImageVector) {


    object Dashboard : BottomBarClass(
        Screens.Dashboard.route,
        "Dashboard",
        Icons.Default.AccountBalance
    )

    object CreateLead : BottomBarClass(
        Screens.CreateLead.route,
        "CreateLead",
        Icons.Default.AddShoppingCart
    )


    object Lists : BottomBarClass(
        Screens.ListDetail.route,
        "Lists",
        Icons.Default.FormatListNumberedRtl
    )

    object Analytics : BottomBarClass(
        Screens.AnalyticsPage.route,
        "Analytics",
        Icons.Default.AcUnit
    )

}

val items = listOf(
    BottomBarClass.Dashboard,
    BottomBarClass.CreateLead,
    BottomBarClass.Lists,
    BottomBarClass.Analytics,
)

