package project.app.sale_crm.voiceToText

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import androidx.navigation.NavController
import project.app.sale_crm.createlead.CreateContactViewModel
import project.app.sale_crm.createlead.MicrophoneAnimation
import project.app.sale_crm.navigation.Screens
import java.util.*

@Composable
fun SpeechToText(navController: NavController, createContactViewModel: CreateContactViewModel) {
    val context = LocalContext.current
    var outputTxt by remember { mutableStateOf("Click button for Speech text") }
    var isRecording = remember { mutableStateOf(false) } // State to track if recording is in progress

    // Initialize the launcher for activity result
    val speechRecognizerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val resultData = result.data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            outputTxt = resultData?.get(0).toString()
            isRecording.value = false // Stop recording after getting the result
            createContactViewModel.voiceToCRM(outputTxt)
            navController.navigate(Screens.ClientDetails.route)
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Speech to Text Example",
            modifier = Modifier.fillMaxWidth().padding(15.dp),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = { if (!isRecording.value) startRecording(context, speechRecognizerLauncher, isRecording) else stopRecording() },
            modifier = Modifier.padding(8.dp)
        ) {
            Text(if (isRecording.value) "Stop Recording" else "Start Recording")
        }

        // Show Lottie animation only when recording
        if (isRecording.value) {
            MicrophoneAnimation() // Show Lottie animation when recording
        }

        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = outputTxt,
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            textAlign = TextAlign.Center
        )
    }
}

private fun startRecording(context: Context, launcher: ActivityResultLauncher<Intent>, isRecording: MutableState<Boolean>) {
    if (!SpeechRecognizer.isRecognitionAvailable(context)) {
        Toast.makeText(context, "Speech not Available", Toast.LENGTH_SHORT).show()
    } else {
        isRecording.value = true // Update state to indicate recording has started
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_WEB_SEARCH)
            putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
            putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak Something")
        }
        launcher.launch(intent)
    }
}

private fun stopRecording() {
    // Logic to stop recording, e.g., update the state variable if needed
    // This can be more involved based on your actual implementation
}

private fun onRecordingCompleted(text: String) {

}