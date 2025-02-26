package project.app.sale_crm.bottombar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import project.app.sale_crm.navigation.Screens

// Define Purple80 color
val Purple80 = Color(0xFFB388FF) // Example hex value for Purple80

@Composable
fun BottomBarUI(
    items: List<BottomBarClass>,
    navController: NavController,
    bottomBarState: MutableState<Boolean> = mutableStateOf(true),
) {
    AnimatedVisibility(visible = bottomBarState.value) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination

            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .background(Color.Black)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(55.dp) // Set height as needed
                        .drawBehind {
                            // Draw the upper border
                            drawLine(
                                color = Color.LightGray,
                                start = Offset(0f, 0f), // Start from the top left corner
                                end = Offset(size.width, 0f), // End at the top right corner
                                strokeWidth = 1.dp.toPx() // Set the stroke width
                            )
                        },
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    items.forEach { item ->

                        val isSelected = currentRoute?.hierarchy?.any { nav ->
                            nav.route == item.route
                        } == true

                        BottomNavigationItem(
                            icon = {
                                item.icon?.let {
                                    Icon(
                                        imageVector = it,
                                        contentDescription = "",
                                        modifier = Modifier
                                            .size(25.dp)
                                            .padding(bottom = 1.dp, top = 2.dp),
                                        tint = if (isSelected) Purple80 else Color.LightGray
                                    )
                                }
                            },
                            label = {
                                item.title?.let {
                                    Text(
                                        text = it,
                                        color = if (isSelected) Purple80 else Color.LightGray,
                                        softWrap = true,
                                        fontSize = 9.sp
                                    )
                                }
                            },
                            selected = isSelected,
                            selectedContentColor = Purple80,
                            unselectedContentColor = Color.LightGray,
                            modifier = Modifier
                                .weight(1f)
                                .background(Color.Transparent),
                            onClick = {
                                item.route?.let { route ->
                                    navController.navigate(route) {
                                        popUpTo(Screens.Dashboard.route) {
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}
