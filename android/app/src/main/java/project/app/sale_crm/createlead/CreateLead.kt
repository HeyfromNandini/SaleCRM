package project.app.sale_crm.createlead

import androidx.compose.runtime.Composable
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.focus.onFocusChanged
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import project.app.sale_crm.models.createContact.CreateContactsRequest
import project.app.sale_crm.navigation.Screens
import project.app.sale_crm.ui.theme.Black
import project.app.sale_crm.ui.theme.Purple80
import project.app.sale_crm.ui.theme.cyanBlueGradient
import project.app.sale_crm.ui.theme.purpleGradient

@Composable
fun CreateLead(navController: NavController, createContactViewModel: CreateContactViewModel) {
    var firstName by remember { mutableStateOf(TextFieldValue("")) }
    var lastName by remember { mutableStateOf(TextFieldValue("")) }
    var ageGroup by remember { mutableStateOf(TextFieldValue("")) }
    var gender by remember { mutableStateOf(TextFieldValue("")) }
    var website by remember { mutableStateOf(TextFieldValue("")) }
    var phoneNumber by remember { mutableStateOf(TextFieldValue("")) }
    var emailAddress by remember { mutableStateOf(TextFieldValue("")) }
    var companyName by remember { mutableStateOf(TextFieldValue("")) }
    var city by remember { mutableStateOf(TextFieldValue("")) }
    var industry by remember { mutableStateOf(TextFieldValue("")) }
    var cultural_affinity by remember { mutableStateOf(TextFieldValue("")) }
    var socioEconomicStatus by remember { mutableStateOf(TextFieldValue("")) }
    var language by remember { mutableStateOf(TextFieldValue("")) }
    var notes by remember { mutableStateOf(TextFieldValue("")) }

    val createContactsResponse = createContactViewModel.createContactResponse.collectAsState()

//    var importantEvents by remember { mutableStateOf(TextFieldValue("")) }
//    var segmentTags by remember { mutableStateOf(TextFieldValue("")) }
//    var customTags by remember { mutableStateOf(TextFieldValue("")) }

    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Black),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .padding(24.dp)
                .verticalScroll(scrollState)
        ) {
            InputField(label = "First Name", value = firstName) { firstName = it }
            InputField(label = "Last Name", value = lastName) { lastName = it }
            InputField(label = "Age Group", value = ageGroup) { ageGroup = it }
            InputField(label = "Gender", value = gender) { gender = it }
            InputField(label = "Website", value = website) { website = it }
            InputField(label = "Phone Number", value = phoneNumber) { phoneNumber = it }
            InputField(label = "Email Address", value = emailAddress) { emailAddress = it }
            InputField(label = "Company Name", value = companyName) { companyName = it }
            InputField(label = "City", value = city) { city = it }
            InputField(label = "Industry", value = industry) { industry = it }
            InputField(label = "cultural_affinity", value = cultural_affinity) { cultural_affinity = it }
            InputField(label = "Socio-Economic Status", value = socioEconomicStatus) { socioEconomicStatus = it }
            InputField(label = "Language", value = language) { language = it }
            InputField(label = "Caste/Community", value = language) { language = it }
            InputField(label = "Notes", value = notes) { notes = it }
//            InputField(label = "Important Events", value = importantEvents) { importantEvents = it }
//            InputField(label = "Segment Tags", value = segmentTags) { segmentTags = it }
//            InputField(label = "Custom Tags", value = customTags) { customTags = it }

            Spacer(modifier = Modifier.height(16.dp))
            VoiceToTextButton(navController)
            Spacer(modifier = Modifier.height(5.dp))
            AddToHubSpotButton(navController) {
                println("createContactsResponse33: ${firstName.text} ${lastName.text}")
                createContactViewModel.createContact(
                    CreateContactsRequest(
                        firstname = firstName.text,
                        lastname = lastName.text,
                        ageGroup = ageGroup.text,
                        gender = gender.text,
                        website = website.text,
                        phone = phoneNumber.text,
                        email = emailAddress.text,
                        company = companyName.text,
                        city = city.text,
                        industry = industry.text,
                        culturalAffinity = cultural_affinity.text,
                        socioEconomicSegment = socioEconomicStatus.text,
                        language = language.text,
                        hsContentMembershipNotes = notes.text,
                        nextActivityDate = "",
                        priorityLevel = "Low Priority"
                    )
                )
                navController.navigate(Screens.ClientDetails.route)
            }
            Spacer(modifier = Modifier.height(70.dp))
        }
    }
}

@Composable
fun InputField(label: String, value: TextFieldValue, onValueChange: (TextFieldValue) -> Unit) {
    val focusElevation = remember { Animatable(0f) }
    val isFocused = remember { mutableStateOf(false) }
    val isFilled = remember { mutableStateOf(value.text.isNotEmpty() ) }

    val borderColor by remember { derivedStateOf { if (isFocused.value || isFilled.value) Purple80 else Color.Gray } }
    val elevation by remember { derivedStateOf { if (isFocused.value || isFilled.value) 16.dp else 4.dp } }

    LaunchedEffect(isFocused.value) {
        focusElevation.animateTo(
            if (isFocused.value || isFilled.value) 16f else 0f,
            animationSpec = tween(durationMillis = 500)
        )
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .shadow(elevation = elevation, shape = RoundedCornerShape(16.dp))
            .background(
                color = Color(0xFF1C1C1C),
                shape = RoundedCornerShape(16.dp)
            )
            .border(
                width = 2.dp,
                brush = Brush.linearGradient(
                    colors = if (isFocused.value || isFilled.value) listOf(  Color(0xFFAB7AE6),  // Green color
                        Color(0xFF33105E)) else listOf(Color.DarkGray, Color.Gray) // Updated gradient colors
                ),
                shape = RoundedCornerShape(16.dp)
            )
            .padding(4.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFF1C1C1C))
            .padding(horizontal = 8.dp)
    ) {
        BasicTextField(
            value = value,
            onValueChange = {
                onValueChange(it)
                isFilled.value = it.text.isNotEmpty()
            },
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged {
                    isFocused.value = it.isFocused
                },
            decorationBox = { innerTextField ->
                if (!isFocused.value && value.text.isEmpty()) {
                    Text(text = label, color = Color.Gray)
                }
                innerTextField()
            },
            textStyle = TextStyle(
                color = Color.White,
                fontSize = 18.sp
            ),
            cursorBrush = Brush.linearGradient(
                colors = listOf(  Color(0xFFAB7AE6),
                    Color(0xFF33105E))
            )
        )
    }
}


@Composable
fun VoiceToTextButton(navController: NavController) {
    val coroutineScope = rememberCoroutineScope()
    val scale = remember { Animatable(1f) }

    Button(
        onClick = {
           navController.navigate(Screens.SpeechtoText.route)
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(
                brush = purpleGradient,
                shape = RoundedCornerShape(12.dp)
            ),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = Color.White
        )
    ) {
        Text(
            text = "\uD83D\uDD0A Voice to CRM",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )
    }
}

@Composable
fun AddToHubSpotButton(navController: NavController, onClick: () -> Unit = { }) {
    val coroutineScope = rememberCoroutineScope()
    val scale = remember { Animatable(1f) }

    Button(
        onClick = {
            onClick()
            coroutineScope.launch {
                scale.animateTo(
                    targetValue = 1.1f,
                    animationSpec = tween(durationMillis = 300)
                )
                scale.animateTo(
                    targetValue = 1f,
                    animationSpec = tween(durationMillis = 300)
                )
            }
            navController.navigate(Screens.ClientDetails.route)
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(
                brush = purpleGradient,
                shape = RoundedCornerShape(12.dp)
            ),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = Color.White
        )
    ) {
        Text(
            text = "\uD83D\uDCC4 Add to HubSpot Company",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )
    }
}


