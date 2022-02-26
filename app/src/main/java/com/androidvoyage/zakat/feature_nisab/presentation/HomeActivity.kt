package com.androidvoyage.zakat.feature_nisab.presentation

import android.os.Bundle
import android.transition.Slide
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.AnimationState
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.androidvoyage.zakat.feature_nisab.presentation.add_nisab.AddEditNisabScreen
import com.androidvoyage.zakat.feature_nisab.presentation.all_nisab.NisabScreen
import com.androidvoyage.zakat.feature_nisab.presentation.home_screen.HomeScreen
import com.androidvoyage.zakat.feature_nisab.presentation.home_screen.component.AppTopBar
import com.androidvoyage.zakat.feature_nisab.presentation.home_screen.component.CenterButton
import com.androidvoyage.zakat.feature_nisab.presentation.util.Features
import com.androidvoyage.zakat.ui.theme.ColorWhite
import com.androidvoyage.zakat.ui.theme.MyZakatTheme
import com.androidvoyage.zakat.ui.theme.PrimaryColor
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalFoundationApi
@AndroidEntryPoint
class HomeActivity : ComponentActivity() {

    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyZakatTheme() {
                val navController = rememberNavController()
                val stateNavRoute = remember {
                    mutableStateOf(Screen.HomeScreen.route)
                }
                val bottomBarState = remember {
                    mutableStateOf(true)
                }
                navController.addOnDestinationChangedListener { controller, destination, arguments ->
                    stateNavRoute.value = destination.route!!
                    bottomBarState.value = ( stateNavRoute.value.equals(Screen.HomeScreen.route) || stateNavRoute.value.equals(Screen.AllNisabScreen.route))
                    Log.d("bottomBarState", "onCreate: ${bottomBarState.value}")
                }
                Scaffold(
                    topBar = { AppTopBar() },
                    bottomBar = {
                        AnimatedVisibility(visible = bottomBarState.value,
                        enter = slideInVertically { it },
                        exit = slideOutVertically { it }) {
                            BottomAppBar(
                                elevation = 8.dp,
                                cutoutShape = RoundedCornerShape(64),
                                modifier = Modifier
                                    .height(if(bottomBarState.value)64.dp else 0.dp)

                            ) {
                                IconButton(
                                    onClick = {
                                        if (!navController.currentDestination?.equals(Screen.HomeScreen.route)!!) {
                                            navController.navigateUp()
                                        }
                                    },
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(end = 64.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Filled.Home,
                                        contentDescription = "Home Tab",
                                        tint = if (stateNavRoute.value == Screen.HomeScreen.route) PrimaryColor else ColorWhite,
                                        modifier = Modifier
                                            .background(
                                                color = if (stateNavRoute.value == Screen.HomeScreen.route) ColorWhite else PrimaryColor,
                                                shape = CircleShape
                                            )
                                            .padding(8.dp)
                                    )
                                }
                                IconButton(
                                    onClick = {
                                        if (!navController.currentDestination?.equals(Screen.AllNisabScreen.route)!!) {
                                            navController.navigate(Screen.AllNisabScreen.route)
                                        }
                                    },
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(start = 64.dp)
                                ) {
                                    Icon(
                                        Icons.Filled.List, "List Tab",
                                        tint = if (stateNavRoute.value == Screen.AllNisabScreen.route) PrimaryColor else ColorWhite,
                                        modifier = Modifier
                                            .background(
                                                color = if (stateNavRoute.value == Screen.AllNisabScreen.route) ColorWhite else PrimaryColor,
                                                shape = CircleShape
                                            )
                                            .padding(8.dp)
                                    )
                                }

                            }
                        }
                    },
                    floatingActionButton = {
                        AnimatedVisibility(visible = bottomBarState.value,
                            enter = slideInVertically { it },
                            exit = slideOutVertically { it }){
                            CenterButton {
                                navController.navigate(Screen.AddEditNisabScreen.route)
                            }
                        }
                    },
                    isFloatingActionButtonDocked = true,
                    floatingActionButtonPosition = FabPosition.Center,
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = Screen.HomeScreen.route
                    ) {
                        composable(route = Screen.HomeScreen.route) {
                            HomeScreen(navController = navController)
                        }
                        composable(route = Screen.AllNisabScreen.route) {
                            NisabScreen(navController = navController)
                        }
                        composable(
                            route = Screen.AddEditNisabScreen.route +
                                    "?nisabId={nisabId}&nisabType={nisabType}",
                            arguments = listOf(
                                navArgument(
                                    name = "nisabId"
                                ) {
                                    type = NavType.LongType
                                    defaultValue = -1
                                },
                                navArgument(
                                    name = "nisabType"
                                ) {
                                    type = NavType.StringType
                                    defaultValue = Features.PREF_CASH_IN_HAND
                                },
                            )
                        ) {
                            val nisabId = it.arguments?.getLong("nisabId") ?: -1
                            val nisabType = it.arguments?.getString("nisabType") ?: ""
                            AddEditNisabScreen(
                                navController = navController,
                                nisabId = nisabId,
                                nisabType = nisabType
                            )
                        }
                    }
                }
            }
        }
    }


}