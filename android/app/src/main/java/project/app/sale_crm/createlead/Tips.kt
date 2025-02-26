
package project.app.sale_crm.createlead

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import project.app.sale_crm.dashboard.CustomCardButton

@Composable
fun Tips(navController: NavController) {
    val cyanBlueGradient = Brush.linearGradient(
        colors = listOf(
            Color(0xFF00FFFF),  // Cyan color
            Color(0xFF0000FF)   // Blue color
        )
    )

    val purpleGradient = Brush.linearGradient(
        colors = listOf(
            Color(0xFFAB7AE6),  // Green color
            Color(0xFF33105E)
        )
    )


    var text by remember { mutableStateOf("") }
    val textToShow = "Your Summary is Getting Ready"
    var index by remember { mutableStateOf(0) }

    // Looping Typewriter Animation
    LaunchedEffect(Unit) {
        while (true) {
            if (index < textToShow.length) {
                text += textToShow[index]
                index++
            } else {
                text = ""
                index = 0
            }
            delay(100) // Typewriter speed
        }
    }

    // State to control chat input and answer visibility
    var isChatOpen by remember { mutableStateOf(false) }
    var userInput by remember { mutableStateOf("") }
    var answerText by remember { mutableStateOf("") }
    var showAnswerBox by remember { mutableStateOf(false) }
    var answerIndex by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {

        Column (modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 25.dp, vertical = 30.dp), horizontalAlignment = Alignment.CenterHorizontally){

            Card(
                modifier = Modifier
                    .width(350.dp)
                    .verticalScroll(rememberScrollState())
                    .padding(vertical = 30.dp)// Custom width
                    .height(500.dp) // Custom height
                    .clip(RoundedCornerShape(2.dp)) // Slightly rounded corners
                    .clickable {
                        // Handle click event here
                    },
                colors = CardDefaults.cardColors(containerColor = Color(0xFFBB86FC)),  // Custom color
                elevation = CardDefaults.cardElevation(4.dp)  // Optional elevation for shadow
            ) {
                AnimatedVisibility(visible = !isChatOpen) {
                    BasicTextField(
                        value = text,
                        onValueChange = {},
                        textStyle = TextStyle(color = Color.White, fontSize = 18.sp),
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                brush = purpleGradient,
                                shape = RoundedCornerShape(0.dp) // Full screen
                            )
                            .padding(16.dp)
                    )
                }

                // Question Input Area
                AnimatedVisibility(visible = isChatOpen && !showAnswerBox) {

                    Text(
                        text = "How can I help?",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    // Input Field for User Query
                    BasicTextField(
                        value = userInput,
                        onValueChange = { userInput = it },
                        textStyle = TextStyle(color = Color.White, fontSize = 16.sp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp)
                            .background(
                                Color(0xFF333333),
                                shape = MaterialTheme.shapes.small
                            )
                            .padding(10.dp),
                        decorationBox = { innerTextField ->
                            Box(
                                Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp),
                                contentAlignment = Alignment.CenterStart
                            ) {
                                if (userInput.isEmpty()) {
                                    Text(
                                        "Type your question here...",
                                        color = Color.Gray,
                                        fontSize = 16.sp
                                    )
                                }
                                innerTextField()
                            }
                        }
                    )

                    // Arrow to Submit the Answer
                    Text(
                        text = "→", // Arrow symbol
                        modifier = Modifier
                            .clickable {
                                showAnswerBox = true
                                answerText = "Your Answer is: " // Placeholder for answer
                                answerIndex = 0 // Reset answer index
                            }
                            .padding(8.dp)
                            .align(Alignment.End) // Align arrow to the right
                            .padding(top = 8.dp), // Space above the arrow
                        color = Color.Black,
                        fontSize = 24.sp
                    )

                }

                // Answer Box
                AnimatedVisibility(visible = showAnswerBox) {

                    // Typewriter animation for answer
                    LaunchedEffect(answerText) {
                        answerText = "Your Answer is: " // Reset answer text
                        while (answerIndex < userInput.length) {
                            answerText += userInput[answerIndex]
                            answerIndex++
                            delay(100) // Typewriter speed
                        }
                    }

                    BasicTextField(
                        value = answerText,
                        onValueChange = {},
                        textStyle = TextStyle(color = Color.Black, fontSize = 18.sp),
                        modifier = Modifier.fillMaxSize()
                    )

                }
            }






        CustomCardButton(
            text = "Generate Summary",
            width = 200.dp,
            height = 50.dp,
            textsize = 18.sp
        )

    }}


}

