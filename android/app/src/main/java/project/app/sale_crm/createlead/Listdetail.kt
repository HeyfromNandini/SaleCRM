package project.app.sale_crm.createlead

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import project.app.sale_crm.navigation.Screens
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import project.app.sale_crm.ui.theme.Purple40
import project.app.sale_crm.ui.theme.Purple80

data class Contact(
    val id: String = "",
    val firstName: String,
    val lastName: String,
    val industry: String,
    val priorityLevel: String,
    val date: String = ""
)

val sampleContacts = listOf(
    Contact("John", "Doe", "Technology", "High", "October 1, 2023"),
    Contact("Jane", "Smith", "Finance", "Medium", "October 1, 2023"),
    Contact("Amit", "Patel", "Retail", "Low", "October 2, 2023")
)

val sampleDates = listOf("October 1, 2023", "October 2, 2023", "October 3, 2023")

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListDetail(navController: NavController, createContactViewModel: CreateContactViewModel) {
    var searchQuery by remember { mutableStateOf(TextFieldValue("")) }
    val cyanBlueGradient = Brush.linearGradient(
        colors = listOf(
            Color(0xFF00FFFF),  // Cyan color
            Color(0xFF0000FF)   // Blue color
        )
    )


    val contactsLists = createContactViewModel.contactsLists.collectAsState()

    println("Contacts: ${contactsLists.value}")

    LaunchedEffect(Unit, contactsLists.value) {
        createContactViewModel.getContacts()
    }

    println("Contacts: ${contactsLists.value}")

    val purpleGradient = listOf(
        Color(0xFFAB7AE6),  // Green color
        Color(0xFF33105E)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp)
    ) {
        Text(
            text = "HubSpot Contact Integration",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .background(Purple40)
                .padding(8.dp),
            color = Color.White
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Search Bar
        OutlinedTextField(value = searchQuery,
            onValueChange = { searchQuery = it },
            placeholder = { Text("Search Contact", color = Color.Gray) },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.DarkGray, shape = RoundedCornerShape(8.dp))
                .border(
                    width = 2.dp,
                    brush = if (searchQuery.text.isNotEmpty()) {
                        Brush.linearGradient(
                            colors = listOf(
                                Color(0xFFAB7AE6),
                                Color(0xFF33105E)
                            )
                        )
                    } else {
                        SolidColor(Color.Transparent)
                    },
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(4.dp),
            textStyle = LocalTextStyle.current.copy(color = Color.White),
            colors = TextFieldDefaults.colors(

                cursorColor = Color.White
            )
        )

        val compnotify by rememberLottieComposition(
            spec = LottieCompositionSpec.Asset("maps.json")
        )
        val progress by animateLottieCompositionAsState(compnotify)
        LottieAnimation(
            composition = compnotify,
            iterations = Int.MAX_VALUE,
            isPlaying = true,
            contentScale = ContentScale.Crop,
            speed = 1.45f,
            modifier = Modifier
                .fillMaxWidth()
                .size(220.dp).background(color = Color.Black)
                .padding(vertical = 15.dp)
                .clickable {
                    // Action when profile image clicked
                })

        Spacer(modifier = Modifier.height(16.dp))

        // Date Dropdown Buttons with Contact Lists
        contactsLists.value.toGroupedContacts().forEach { date ->
            DateDropdownButton(
                date = date.date,
                contacts = date.contacts,
                searchQuery = searchQuery.text,
                navController
            ) {
               createContactViewModel.getContactById(it)
                navController.navigate(Screens.ClientDetails.route)
            }
        }
    }
}

@Composable
fun DateDropdownButton(
    date: String,
    contacts: List<Contact>,
    searchQuery: String,
    navController: NavController,
    onClick: (id: String) -> Unit = {}
) {
    var isExpanded by remember { mutableStateOf(false) }
    val dateContacts = contacts.filter { it.date == date }
        .filter {
            it.firstName.contains(searchQuery, ignoreCase = true) || it.lastName.contains(
                searchQuery,
                ignoreCase = true
            )
        }

    Column(modifier = Modifier.clickable { navController.navigate(Screens.ClientDetails.route) }) {
        // Date Button
        Text(
            text = date,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .background(
                    brush = if (isExpanded) Brush.linearGradient(
                        listOf(

                            Color(0xFFAB7AE6),  // Green color
                            Color(0xFF33105E)

                        )
                    ) else SolidColor(Color.DarkGray), shape = RoundedCornerShape(4.dp)
                )
                .clickable { isExpanded = !isExpanded }
                .padding(8.dp),
            color = Color.White,
            fontWeight = FontWeight.Bold)

        // Contact List shown only when expanded
        if (isExpanded) {
            LazyColumn {
                items(dateContacts) { contact ->
                    ContactItem(contact) {
                        onClick(contact.id)
                    }
                    Divider(color = Color.Gray)
                }
            }
        }
    }
}

@Composable
fun ContactItem(contact: Contact, onClick: (id: String) -> Unit = {}) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable {
                onClick(contact.id)
            },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = "${contact.firstName} ${contact.lastName}",
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Text(text = "Industry: ${contact.industry}", color = Color.Gray)
        }
        Text(text = "Priority: ${contact.priorityLevel}", color = Color.Gray)
    }
}

fun String.toCustomDateFormat(): String {
    // Define the input and output date formats
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
    val outputFormat = SimpleDateFormat("MMMM dd, yyyy : hh:mm a", Locale.getDefault())

    return try {
        // Parse the string to a Date object, then format it to the desired string
        val date = inputFormat.parse(this)
        outputFormat.format(date ?: Date())
    } catch (e: Exception) {
        // Return an error message or handle the exception as needed
        "Invalid date format"
    }
}

data class GroupedContact(
    val date: String,
    val contacts: List<Contact>
)


// Extension function to group Result list by date
fun List<project.app.sale_crm.models.fetchContact.Result>.toGroupedContacts(): List<GroupedContact> {
    return this
        .mapNotNull { result ->
            result.properties?.let { properties ->
                Contact(
                    id = result.id ?: "",
                    firstName = properties.firstname ?: "",
                    lastName = properties.lastname ?: "",
                    industry = properties.industry ?: "",
                    priorityLevel = properties.priorityLevel ?: "",
                    date = (properties.createdate ?: "").toCustomDateFormat()
                )
            }
        }
        .groupBy { it.date }
        .map { (date, contacts) ->
            GroupedContact(date = date, contacts = contacts)
        }
        // Sort GroupedContacts by the date string
        .sortedBy { dateString ->
            val inputFormat = SimpleDateFormat("MMMM dd, yyyy : hh:mm a", Locale.getDefault())
            inputFormat.parse(dateString.date) ?: Date(Long.MAX_VALUE) // Fallback to max date if parsing fails
        }
}
