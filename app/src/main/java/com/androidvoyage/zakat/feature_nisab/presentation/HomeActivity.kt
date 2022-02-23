package com.androidvoyage.zakat.feature_nisab.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.androidvoyage.zakat.feature_nisab.presentation.add_nisab.AddEditNisabScreen
import com.androidvoyage.zakat.feature_nisab.presentation.all_nisab.NisabScreen
import com.androidvoyage.zakat.ui.theme.MyZakatTheme
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalFoundationApi
@AndroidEntryPoint
class HomeActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyZakatTheme() {
                Surface(
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.AllNisabScreen.route
                    ) {
                        composable(route = Screen.AllNisabScreen.route) {
                            NisabScreen(navController = navController)
                        }
                        composable(
                            route = Screen.AddEditNisabScreen.route +
                                    "?nisabId={nisabId}",
                            arguments = listOf(
                                navArgument(
                                    name = "nisabId"
                                ) {
                                    type = NavType.IntType
                                    defaultValue = -1
                                },
                                navArgument(
                                    name = "nisabType"
                                ) {
                                    type = NavType.IntType
                                    defaultValue = -1
                                },
                            )
                        ) {
                            val color = it.arguments?.getString("nisabType") ?: ""
                            AddEditNisabScreen(
                                navController = navController,
                                nisabType = color
                            )
                        }
                    }
                }
            }
        }
    }


}