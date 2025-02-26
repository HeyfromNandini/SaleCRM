package project.app.sale_crm.dashboard

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.ElectricCar
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.MeetingRoom
import androidx.compose.material.icons.filled.MonetizationOn
import androidx.compose.material.icons.filled.People
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import kotlinx.coroutines.delay
import project.app.sale_crm.R
import project.app.sale_crm.navigation.Screens
import project.app.sale_crm.ui.theme.CardTextColor
import project.app.sale_crm.ui.theme.appBackground
import project.app.sale_crm.ui.theme.bluegradient
import project.app.sale_crm.ui.theme.cyanBlueGradient
import project.app.sale_crm.ui.theme.textColor
import kotlin.system.exitProcess


@SuppressLint("SuspiciousIndentation")
@Composable
fun Dashboard(
    navHostController: NavHostController,
    name: String,
    pfp: String
) {


    val activity = (LocalContext.current as? Activity)



    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(appBackground)
                .verticalScroll(rememberScrollState())

        ) {
            Card(
                modifier = Modifier
                    .clip(RoundedCornerShape(0.dp, 0.dp, 50.dp, 50.dp))
                    .fillMaxWidth(),
                colors = CardDefaults.cardColors(Color.DarkGray),
                elevation = CardDefaults.cardElevation(0.dp)
            ) {
                Column {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                top = 45.dp,
                                bottom = 20.dp,
                                end = 25.dp,
                                start = 25.dp
                            ),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = "Welcome Back",
                                color = Color.White,
                                fontSize = 15.sp,
                                modifier = Modifier.padding(bottom = 7.dp)
                            )
                            Text(
                                text = name,
                                color = CardTextColor,
                                fontSize = 25.sp,
                                modifier = Modifier.padding(bottom = 7.dp)
                            )
                            Text(
                                text = "Start making a difference today!",
                                color = Color.LightGray,
                                fontSize = 13.sp,
                                modifier = Modifier.padding(bottom = 7.dp)
                            )
                        }
                        ProfileImage(
                            imageUrl = pfp,
                            modifier = Modifier
                                .size(90.dp)
                                .border(
                                    width = 1.dp,
                                    color = textColor,
                                    shape = CircleShape
                                )
                                .padding(2.dp)
                                .clip(CircleShape)
                                .clickable {

                                }
                        )
                    }

                    Spacer(modifier = Modifier.height(15.dp))
                }
            }



            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp)
                    .clickable {

                    },
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(horizontalAlignment = Alignment.Start) {
                    Text(
                        text = "Current Progress",
                        color = textColor,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(bottom = 7.dp)
                    )
                    Text(
                        text = " more points to reach next level",
                        color = textColor,
                        fontSize = 9.sp,
                        modifier = Modifier.padding(start = 0.dp)
                    )
                }

                ArcComposable(
                    modifier = Modifier.padding(end = 25.dp),
                    text = "10%",
//                            progress = animatedProgress
                )
            }

            Spacer(modifier = Modifier.height(20.dp))


            //todo list
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Card(
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .padding(vertical = 5.dp)
                        .width(330.dp)
                        .height(120.dp),
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 10.dp, vertical = 10.dp),
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.Center
                    ) {

                        Text(text = "Upcoming Tasks", fontSize = 20.sp, color = Color.White)

                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(start = 10.dp, top = 10.dp)
                        ) {
                            Text(
                                text = "Task 1: Follow up with Client A",
                                fontSize = 14.sp,
                                color = Color.White
                            )

                            Text(
                                text = "Task 2: Prepare sales report",
                                fontSize = 14.sp,
                                color = Color.White
                            )

                            Text(
                                text = "Task 3: Meeting with team",
                                fontSize = 14.sp,
                                color = Color.White
                            )

                        }

                    }

                }

            }


            //leads overview

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 14.dp, vertical = 5.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {

                Row(modifier = Modifier.padding(top = 10.dp)) {
                    Icon(imageVector = Icons.Default.People, contentDescription = "")
                    Text(text = "Leads Overview", fontSize = 18.sp, color = Color.LightGray)

                }
                Text(text = "See all", fontSize = 12.sp, color = Color.LightGray)
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {


                Card(
                    modifier = Modifier
                        .background(Color.Transparent)
                        .border(width = 1.dp, color = Color.LightGray)
                        .clip(RoundedCornerShape(20.dp))
                        .padding(vertical = 5.dp)
                        .width(330.dp)
                        .height(90.dp),
                ) {

                    Column(
                        modifier = Modifier
                            .background(Color.Black)
                            .fillMaxSize()
                            .padding(horizontal = 20.dp, vertical = 10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceAround
                    ) {

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = "Lead 1: Kailash Sharma ")

                            CustomCardButton(
                                text = "Follow Up",
                                width = 80.dp,
                                height = 30.dp,
                                textsize = 10.sp
                            )

                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = "Lead 2: Vignesh Gadhari ")

                            CustomCardButton(
                                text = "Contact",
                                width = 80.dp,
                                height = 30.dp,
                                textsize = 10.sp
                            )

                        }

                    }


                }
            }


            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 26.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {

                Row(modifier = Modifier.padding(vertical = 10.dp)) {
                    Icon(imageVector = Icons.Default.People, contentDescription = "")
                    Text(text = "Client Categories", fontSize = 18.sp, color = Color.LightGray)

                }

            }

            //floating action button
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 25.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Card(
                    modifier = Modifier
                        .width(350.dp)
                ) {

                   Column(modifier = Modifier
                       .fillMaxSize()
                       .padding(vertical = 15.dp)) {
                       ClientCategoryRows(icon = Icons.Default.Business, text = "Buisness Clients", image = R.drawable.buisness )

                       ClientCategoryRows(icon = Icons.Default.People, text = "Family", image = R.drawable.family )

                       ClientCategoryRows(icon = Icons.Default.MonetizationOn, text = "Freelance", image = R.drawable.freelance)

                       ClientCategoryRows(icon = Icons.Default.MeetingRoom, text = "Client Meetings", image = R.drawable.client )

                   }
                }


            }




            Spacer(modifier = Modifier.height(20.dp))
            val lastTextColor = if (isSystemInDarkTheme()) Color.White else Color.Black

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp)
            ) {
                Spacer(modifier = Modifier.height(25.dp))
                Text(
                    text = "ForeFront",
                    color = lastTextColor.copy(0.75f),
                    fontSize = 33.sp,
                )
                Spacer(modifier = Modifier.height(0.dp))
                Text(
                    text = "CRM Wise",
                    color = lastTextColor.copy(0.5f),
                    fontSize = 23.sp,
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Crafted with ❤️ by The Phoenix",
                    color = lastTextColor.copy(0.75f),
                    fontSize = 10.sp,
                )
            }

            Spacer(modifier = Modifier.height(130.dp))

        }
    }

}


@Composable
fun ArcComposable(
    modifier: Modifier,
    text: String,
    progress: Float = 0.5f, // Progress value between 0.0 and 1.0
    completedColor: Color = Color(0xFF48c5a3),
    remainingColor: Color = Color(0xFFe4e4e4),
) {
    val sweepAngle = 180f * progress
    Box(
        modifier = modifier.background(Color.Transparent)
    ) {
        Canvas(modifier = Modifier.size(70.dp)) {
            // Draw the remaining part of the arc
            drawArc(
                color = remainingColor,
                -180f + sweepAngle,
                180f - sweepAngle,
                useCenter = false,
                size = Size(size.width, size.height),
                style = Stroke(8.dp.toPx(), cap = StrokeCap.Round)
            )

            // Draw the completed part of the arc
            drawArc(
                color = completedColor,
                -180f,
                sweepAngle,
                useCenter = false,
                size = Size(size.width, size.height),
                style = Stroke(8.dp.toPx(), cap = StrokeCap.Round)
            )
        }
        Text(
            modifier = Modifier.align(alignment = Alignment.Center),
            text = text,
            color = textColor,
            fontSize = 20.sp
        )
    }
}


@Composable
fun CustomCardButton(text: String, width: Dp, height: Dp, textsize: TextUnit) {

    Card(
        modifier = Modifier
            .width(width) // Custom width
            .height(height) // Custom height
            .clip(RoundedCornerShape(2.dp)) // Slightly rounded corners
            .clickable {
                // Handle click event here
            },
        colors = CardDefaults.cardColors(containerColor = Color(0xFFBB86FC)),  // Custom color
        elevation = CardDefaults.cardElevation(4.dp)  // Optional elevation for shadow
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center // Center content in the card
        ) {
            Text(
                text = text,
                color = Color.White,
                fontSize = textsize
            )
        }
    }
}


@Composable
fun FloatingPlusButton(navController: NavController) {

    Card(
        modifier = Modifier
            .padding(top = 50.dp)
            .size(50.dp)
            .clip(RoundedCornerShape(2.dp)) // Slightly rounded corners
            .clickable {
                navController.navigate(Screens.CreateLead.route)
            },
        colors = CardDefaults.cardColors(containerColor = Color(0xFFBB86FC)),  // Custom color
        elevation = CardDefaults.cardElevation(4.dp),

        ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "",
                modifier = Modifier.size(30.dp)
            )
        }
    }


}


@Composable
fun ClientCategoryRows(icon: ImageVector, text:String, image: Int) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 6.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row {
            Icon(
                imageVector = icon,
                contentDescription = "",
                modifier = Modifier.size(20.dp)
            )
            Text(text = text, fontSize = 12.sp)
        }
        Image(
            painter = painterResource(image),
            contentDescription = "",
            modifier = Modifier.size(24.dp),
            contentScale = ContentScale.Crop
        )

       }
    Divider(modifier = Modifier.fillMaxWidth().height(1.dp), color = Color.DarkGray)

}


