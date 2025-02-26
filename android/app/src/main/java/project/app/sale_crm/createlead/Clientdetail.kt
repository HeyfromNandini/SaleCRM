package project.app.sale_crm.createlead

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import project.app.sale_crm.navigation.Screens

val cyanBlueGradient = listOf(Color.Cyan, Color.Blue)

val purpleGradient = listOf(
    Color(0xFFAB7AE6),  // Green color
    Color(0xFF33105E)
)

@Composable
fun ClientDetail(navController: NavController, createContactViewModel: CreateContactViewModel) {
    var showActionOptions by remember { mutableStateOf(false) }

    val createContactsResponse = createContactViewModel.createContactResponse.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            // Main Section Card for Name, Company, Location
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .border(
                        2.dp,
                        Brush.horizontalGradient(purpleGradient),
                        RoundedCornerShape(10.dp)
                    )
                    .background(Color.DarkGray, RoundedCornerShape(10.dp))
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "${createContactsResponse.value?.properties?.firstname} " +
                                "${createContactsResponse.value?.properties?.lastname}",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(text = "${createContactsResponse.value?.properties?.website}", fontSize = 18.sp, color = Color.LightGray)
                    Text(text = "${createContactsResponse.value?.properties?.phoneName}", fontSize = 16.sp, color = Color.LightGray)
                }
            }

            // Additional Client Details
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .background(Color.DarkGray, RoundedCornerShape(10.dp))
                    .padding(16.dp)
            ) {
                if (createContactsResponse.value != null) {
                    Column {
                        ClientDetailSection(
                            label = "First Name",
                            value = createContactsResponse.value?.properties?.firstname ?: ""
                        )
                        ClientDetailSection(
                            label = "Last Name",
                            value = createContactsResponse.value?.properties?.lastname ?: ""
                        )
                        ClientDetailSection(
                            label = "Website",
                            value = createContactsResponse.value?.properties?.website ?: ""
                        )
                        ClientDetailSection(
                            label = "Phone Number",
                            value = createContactsResponse.value?.properties?.phoneName ?: ""
                        )
                        ClientDetailSection(
                            label = "Email Address",
                            value = createContactsResponse.value?.properties?.email ?: ""
                        )
                        ClientDetailSection(
                            label = "Company",
                            value = createContactsResponse.value?.properties?.company ?: ""
                        )
                        ClientDetailSection(
                            label = "Age Group",
                            value = createContactsResponse.value?.properties?.ageGroup ?: ""
                        )
                        ClientDetailSection(
                            label = "City",
                            value = createContactsResponse.value?.properties?.city ?: ""
                        )
                        ClientDetailSection(
                            label = "Cultural Affinity",
                            value = createContactsResponse.value?.properties?.culturalAffinity ?: ""
                        )
                        ClientDetailSection(
                            label = "Socio-Economic Status",
                            value = createContactsResponse.value?.properties?.socioEconomicSegment
                                ?: ""
                        )
                        ClientDetailSection(
                            label = "Language",
                            value = createContactsResponse.value?.properties?.language ?: ""
                        )
                        ClientDetailSection(
                            label = "Notes",
                            value = createContactsResponse.value?.properties?.hsContentMembershipNotes
                                ?: ""
                        )

                    }
                } else {
                    CircularProgressIndicator()
                }
            }


            Spacer(modifier = Modifier.height(80.dp))
        }

        // Edit Button at the top-right corner
        IconButton(
            onClick = { /* Handle edit action */ },
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp)
        ) {
            Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit", tint = Color.White)
        }

        // Floating Action Button with gradient and "Actions" text
        FloatingActionButton(
            onClick = { showActionOptions = !showActionOptions },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            backgroundColor = Color.Transparent
        ) {
            Box(
                modifier = Modifier
                    .background(Brush.horizontalGradient(purpleGradient), CircleShape)
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text("Actions", color = Color.White)
            }
        }

        // Action Options, overlaying and matching the size of the "Actions" button
        if (showActionOptions) {
            Column(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(end = 16.dp, bottom = 80.dp)
                    .background(Color.DarkGray, RoundedCornerShape(10.dp))
                    .width(180.dp)
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Button for "Show Client Summary"
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .background(
                            Brush.horizontalGradient(purpleGradient),
                            RoundedCornerShape(50)
                        )
                        .padding(vertical = 8.dp)
                        .clickable { navController.navigate(Screens.Tips.route) },
                    contentAlignment = Alignment.Center
                ) {
                    Text("Tips", color = Color.White)
                }

                // Button for "Best Follow-Up"
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .background(
                            Brush.horizontalGradient(purpleGradient),
                            RoundedCornerShape(50)
                        )
                        .padding(vertical = 8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Best Follow-Up", color = Color.White)
                }

                // Button for "Delete" with a red color background
                Button(
                    onClick = { /* Delete action */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red),
                    shape = RoundedCornerShape(50)
                ) {
                    Text("Delete", color = Color.White)
                }
            }
        }
    }
}


@Composable
fun ClientDetailSection(label: String, value: String) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Text(text = label, fontSize = 14.sp, color = Color.LightGray)
        Text(text = value, fontSize = 16.sp, color = Color.White, fontWeight = FontWeight.SemiBold)
        Divider(color = Color.Gray, thickness = 0.5.dp)
    }
}

