package project.app.sale_crm.dashboard

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SemiCircularProgressBar(progress: Float, modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        Canvas(
            modifier = Modifier
                .size(200.dp)
        ) {
            val strokeWidth = 15.dp.toPx()
            val arcBounds = Rect(
                left = 0f,
                top = size.height / 2,
                right = size.width,
                bottom = size.height
            )

            // Draw the background arc (gray semicircle)
            drawArc(
                color = Color.LightGray,
                startAngle = 180f,  // Start from the left (180 degrees)
                sweepAngle = 180f,  // Sweep 180 degrees to form a semicircle
                useCenter = false,
                style = Stroke(width = strokeWidth, cap = StrokeCap.Round),
                topLeft = arcBounds.topLeft,
                size = arcBounds.size
            )

            // Draw the progress arc (blue semicircle)
            drawArc(
                color = Color.Yellow,
                startAngle = 180f,  // Start from the left (180 degrees)
                sweepAngle = 180 * progress,  // Progress-based arc length
                useCenter = false,
                style = Stroke(width = strokeWidth, cap = StrokeCap.Round),
                topLeft = arcBounds.topLeft,
                size = arcBounds.size
            )
        }

        // Display progress percentage at the bottom
        Text(
            text = "${(progress * 100).toInt()}%",
            fontSize = 18.sp,
            color = Color.Black,
            modifier = Modifier.padding(top = 90.dp) // Position the text below the arc
        )
    }
}

@Composable
fun SemiCircularProgressBarScreen() {
    var progress by remember { mutableStateOf(0.0f) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 5.dp), // Padding only on the sides
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SemiCircularProgressBar(progress = progress, modifier = Modifier.size(200.dp))

        Spacer(modifier = Modifier.height(10.dp)) // Space after the progress bar

        // Simulate progress increment
        LaunchedEffect(Unit) {
            while (progress < 1.0f) {
                progress += 0.01f
                kotlinx.coroutines.delay(100) // Increase progress every 100 milliseconds
            }
        }
    }
}
