package project.app.sale_crm.createlead

import androidx.compose.animation.core.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import project.app.sale_crm.ui.theme.Black

@Composable
fun VoicetoCRM(navController: NavController) {
    var isSpeaking by remember { mutableStateOf(false) }

    // Main Column
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Black)
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AnimatedTagsWithOval()

        if (isSpeaking) {
            MicrophoneAnimation()
        }

        GlassMorphismButton(
            text = if (isSpeaking) "Stop Recording" else "Start Recording",
            onClick = { isSpeaking = !isSpeaking }
        )
    }
}

// Updated AnimatedTags function with oval shapes
@Composable
fun AnimatedTagsWithOval() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier.fillMaxWidth()
    ) {
        listOf("First Name", "Last Name", "Address", "Company Name").forEach { label ->
            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .background(Color(0xFF444240), shape = RoundedCornerShape(50.dp)) // Oval shape
                    .padding(16.dp)
            ) {
                Text(
                    text = "Say $label",
                    color = Color.Cyan, // Highlight as required with cyan color
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyMedium // Use standard text size
                )
            }
        }
    }
}

// Microphone Animation
@Composable
fun MicrophoneAnimation() {
    val currenanim by rememberLottieComposition(
        spec = LottieCompositionSpec.Asset("siri.json")
    )
    LottieAnimation(
        composition = currenanim,
        iterations = Int.MAX_VALUE,
        contentScale = ContentScale.Crop,
        speed = 1f,
        modifier = Modifier
            .size(250.dp)
    )
//    val infiniteTransition = rememberInfiniteTransition()
//
//    // Animate scaling of the microphone icon
//    val scale by infiniteTransition.animateFloat(
//        initialValue = 0.8f,
//        targetValue = 1.2f,
//        animationSpec = infiniteRepeatable(
//            animation = tween(600, easing = FastOutSlowInEasing),
//            repeatMode = RepeatMode.Reverse
//        )
//    )
//
//    // Display Microphone Icon without a background box
//    Icon(
//        imageVector = Icons.Filled.Mic,
//        contentDescription = "Microphone Icon",
//        modifier = Modifier
//            .size(100.dp)
//            .scale(scale), // Scale animation on the icon
//        tint = Color.Cyan // Icon color
//    )
}

@Composable
fun GradientButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .height(60.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent // Make the background transparent to show gradient
        ),
        shape = RoundedCornerShape(50) // Rounded corners for the button
    ) {
        Box(
            modifier = Modifier
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(Color.Cyan, Color.Blue)
                    ),
                    shape = RoundedCornerShape(50)
                )
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                color = Color.White,
                style = MaterialTheme.typography.bodyLarge // Increased text size
            )
        }
    }
}
@Composable
fun GlassMorphismButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .height(60.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent // Transparent background for glass effect
        ),
        shape = RoundedCornerShape(50), // Rounded corners for the button
        border = BorderStroke(
            2.dp,
            Brush.linearGradient(
                colors = listOf(
                    Color.Cyan.copy(alpha = 0.8f),
                    Color.Blue.copy(alpha = 0.8f)
                )
            ) // Glowing cyan and blue border
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer {
                    // Apply transparency for glass-like effect
                    alpha = 0.5f
                    // Glassy floating shadow
                    shadowElevation = 10.dp.toPx()
                    shape = RoundedCornerShape(50)
                    clip = true // Clip the content to the rounded shape
                }
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color.Cyan.copy(alpha = 0.3f),
                            Color.Blue.copy(alpha = 0.3f)
                        ),
                    ),
                    shape = RoundedCornerShape(50)
                )
                .border(
                    BorderStroke(1.dp, Color.White.copy(alpha = 0.15f)) // Inner subtle white border for glass depth
                )
                .alpha(0.8f), // Add slight opacity
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                color = Color.White,
                style = MaterialTheme.typography.bodyLarge, // Text size and style
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold

            )
        }
    }
}
