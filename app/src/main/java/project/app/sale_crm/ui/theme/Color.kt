package project.app.sale_crm.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)
val Cyan = Color(0xFF00FFFF)
val Black = Color(0xFF000000)
val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)
val bluegradient = Brush.linearGradient(
    colors = listOf(  Color(0xFF004ff9),
        Color(0xFF000000)),
    start = Offset(0f, 0f),
    end = Offset(1000f, 1000f)
)
// Define other color gradients if needed
val cyanBlueGradient = Brush.linearGradient(
    colors = listOf(
        Color(0xFF00FFFF),  // Cyan color
        Color(0xFF0000FF)   // Blue color
    )
)

val greenBlueGradient = Brush.linearGradient(
    colors = listOf(
        Color(0xFF00FF00),  // Green color
        Color(0xFF0000FF)   // Blue color
    )
)

val purpleGradient = Brush.linearGradient(
    colors = listOf(
        Color(0xFFAB7AE6),  // Green color
        Color(0xFF33105E)   // Blue color
    )
)



val textColor = Color(0xFFBBA6AD)
val appBackground = Color(0xFF030203)
val CardTextColor = Color(0xFFECC14F)


