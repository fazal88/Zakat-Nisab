package com.androidvoyage.zakat.feature_nisab.presentation.home_screen

/**
 * Created by Fazal on 14/02/22.
 * Copyright (c) 2022 Fazal. All rights reserved.
 */

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.androidvoyage.zakat.R
import com.androidvoyage.zakat.feature_nisab.presentation.Screen
import com.androidvoyage.zakat.feature_nisab.presentation.home_screen.component.*
import com.androidvoyage.zakat.feature_nisab.presentation.util.Features
import com.androidvoyage.zakat.ui.theme.*
import kotlin.random.Random

@ExperimentalFoundationApi
@Composable
fun HomeScreen(navController: NavController) {
    Scaffold() {
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
                            title = Features.PREF_CASH_IN_HAND,
                            subtitle = Random.nextInt(0,100_00_00_00).toString(),
                            R.drawable.ic_cash,
                            LightGreen1,
                            LightGreen2,
                            LightGreen3
                        ),
                        Feature(
                            title = Features.PREF_GOLD_SILVER,
                            subtitle = Random.nextInt(0,100_00_00_00).toString(),
                            R.drawable.ic_gold,
                            Blue1,
                            Blue2,
                            Blue3
                        ),
                        Feature(
                            title = Features.PREF_SAVINGS_FUNDS,
                            subtitle = Random.nextInt(0,100_00_00_00).toString(),
                            R.drawable.ic_assets,
                            Pink1,
                            Pink2,
                            Pink3
                        ),
                        Feature(
                            title = Features.PREF_BUSINESS_ASSETS,
                            subtitle = Random.nextInt(0,100_00_00_00).toString(),
                            R.drawable.ic_stocks,
                            Beige1,
                            Beige2,
                            Beige3
                        ),
                        Feature(
                            title = Features.PREF_DEBT_OWNED,
                            subtitle = Random.nextInt(0,100_00_00_00).toString(),
                            R.drawable.ic_debt,
                            OrangeYellow1,
                            OrangeYellow2,
                            OrangeYellow3
                        ),
                        Feature(
                            title = Features.PREF_SHARES_STOCKS,
                            subtitle = Random.nextInt(0,100_00_00_00).toString(),
                            R.drawable.ic_invest,
                            BlueViolet1,
                            BlueViolet2,
                            BlueViolet3
                        ),
                        Feature(
                            title = Features.PREF_PROPERTIES_CARS,
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
