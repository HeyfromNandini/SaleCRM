package project.app.sale_crm

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import project.app.sale_crm.bottombar.BottomBarUI
import project.app.sale_crm.bottombar.items
import project.app.sale_crm.navigation.MainNavController
import project.app.sale_crm.navigation.Screens
import project.app.sale_crm.ui.theme.SaleCRMTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SaleCRMTheme {
                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {


                    val navController = rememberNavController()
                    val bottomBarState = rememberSaveable { mutableStateOf(true) }
                    val navBackStackEntry by navController.currentBackStackEntryAsState()


                    navBackStackEntry?.destination?.route?.let { route ->
                        bottomBarState.value = when (route) {
                            Screens.SplashScreen.route,
                             -> false

                            else -> true
                        }
                    }

                    Scaffold(
                        bottomBar = {
                            BottomBarUI(
                                navController = navController,
                                bottomBarState = bottomBarState,
                                items =
                              items)

                        }
                    ) {
                        val scaffoldState = rememberScaffoldState()
                        MainNavController(
                            it,
                            navController
                        )
                    }
                }
//            }
        }
    }
}