package com.androidvoyage.zakat.feature_nisab.presentation.home_screen

/**
 * Created by Fazal on 14/02/22.
 * Copyright (c) 2022 Fazal. All rights reserved.
 */

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.androidvoyage.zakat.R
import com.androidvoyage.zakat.feature_nisab.presentation.Screen
import com.androidvoyage.zakat.feature_nisab.presentation.home_screen.component.*
import com.androidvoyage.zakat.ui.theme.*
import kotlin.random.Random

@ExperimentalFoundationApi
@Preview
@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        topBar = { AppTopBar() },
        bottomBar = {
            BottomAppBar(
                elevation = 10.dp,
                cutoutShape = RoundedCornerShape(50)
            ) {

                IconButton(
                    onClick = {
                        navController.navigate(Screen.HomeScreen.route)
                    }
                ) {
                    Icon(Icons.Filled.Home, "Home Tab")
                }
                IconButton(
                    onClick = {
                        navController.navigate(Screen.AllNisabScreen.route)
                    }
                ) {
                    Icon(Icons.Filled.List, "List Tab")
                }

            }},
        floatingActionButton = {
            CenterButton {
                navController.navigate(Screen.AddEditNisabScreen.route)
            }
        },
        isFloatingActionButtonDocked = true,
        floatingActionButtonPosition = FabPosition.Center,

        ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(PrimaryLightBack)
        ) {
            Column {
                CurrentStatus()
                FeatureSection(
                    features = listOf(
                        Feature(
                            title = "Cash in Hand",
                            subtitle = Random.nextInt(0,100_00_00_00).toString(),
                            R.drawable.ic_cash,
                            LightGreen1,
                            LightGreen2,
                            LightGreen3
                        ),
                        Feature(
                            title = "Gold + Silver",
                            subtitle = Random.nextInt(0,100_00_00_00).toString(),
                            R.drawable.ic_gold,
                            Blue1,
                            Blue2,
                            Blue3
                        ),
                        Feature(
                            title = "Savings & Funds",
                            subtitle = Random.nextInt(0,100_00_00_00).toString(),
                            R.drawable.ic_assets,
                            Pink1,
                            Pink2,
                            Pink3
                        ),
                        Feature(
                            title = "Business Assets",
                            subtitle = Random.nextInt(0,100_00_00_00).toString(),
                            R.drawable.ic_stocks,
                            Beige1,
                            Beige2,
                            Beige3
                        ),
                        Feature(
                            title = "Debt Owed",
                            subtitle = Random.nextInt(0,100_00_00_00).toString(),
                            R.drawable.ic_debt,
                            OrangeYellow1,
                            OrangeYellow2,
                            OrangeYellow3
                        ),
                        Feature(
                            title = "Shares & Stocks",
                            subtitle = Random.nextInt(0,100_00_00_00).toString(),
                            R.drawable.ic_invest,
                            BlueViolet1,
                            BlueViolet2,
                            BlueViolet3
                        ),
                        Feature(
                            title = "Properties + Cars",
                            subtitle = Random.nextInt(0,100_00_00_00).toString(),
                            R.drawable.ic_asset,
                            Violet1,
                            Violet2,
                            Violet3
                        )
                    )
                )
            }
        }
    }

}


@Composable
fun AppBottomBar(fabShape: RoundedCornerShape) {



}
