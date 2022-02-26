package com.androidvoyage.zakat.feature_nisab.presentation.home_screen

/**
 * Created by Fazal on 14/02/22.
 * Copyright (c) 2022 Fazal. All rights reserved.
 */

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import com.androidvoyage.zakat.R
import com.androidvoyage.zakat.feature_nisab.presentation.Screen
import com.androidvoyage.zakat.feature_nisab.presentation.home_screen.component.Feature
import com.androidvoyage.zakat.ui.theme.*
import com.androidvoyage.zakat.util.Utils
import kotlin.random.Random

@ExperimentalFoundationApi
@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        topBar = { AppTopBar() },
        bottomBar = { AppBottomBar(RoundedCornerShape(50)) },
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
fun AppTopBar() {
    TopAppBar(
        elevation = 0.dp,
        backgroundColor = PrimaryLightBack
    ) {
        ConstraintLayout(
            modifier = Modifier.fillMaxWidth()
        ) {
            val (title, search) = createRefs()
            Text(
                text = stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.h1,
                color = PrimaryDarkColor,
                modifier = Modifier
                    .constrainAs(title) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(search.start)
                        width = Dimension.fillToConstraints
                    }
                    .padding(16.dp, 0.dp)
            )
            IconButton(
                onClick = {
                    /* doSomething() */
                },
                modifier = Modifier
                    .constrainAs(search) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        end.linkTo(parent.end)
                    }
            ) {
                Icon(Icons.Filled.MoreVert, "More", tint = PrimaryDarkColor)
            }
        }
    }
}

@Composable
fun AppBottomBar(fabShape: RoundedCornerShape) {

    BottomAppBar(
        elevation = 10.dp,
        cutoutShape = fabShape
    ) {

        IconButton(
            onClick = {
                /* doSomething() */
            }
        ) {
            Icon(Icons.Filled.Home, "")
        }
        // The actions should be at the end of the BottomAppBar
        Spacer(Modifier.weight(1f, true))
        IconButton(
            onClick = {
                /* doSomething() */
            }
        ) {
            Icon(Icons.Filled.List, "")
        }

    }

}

@Composable
fun CenterButton(onClick : ()->Unit) {
    FloatingActionButton(
        onClick = onClick,
        shape = RoundedCornerShape(50),
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = Color.White
    ) {
        Icon(Icons.Filled.Add, "")
    }
}

@Composable
fun CurrentStatus(
    color: Color = LightRed
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(15.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(color)
            .padding(horizontal = 15.dp, vertical = 20.dp)
            .fillMaxWidth()
    ) {
        Column {
            Text(
                text = "Monday, 14th Feb",
                style = MaterialTheme.typography.h2
            )
            Text(
                text = "13 Sha'ban, 1444 AH",
                style = MaterialTheme.typography.body1,
                color = TextWhite
            )
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(48.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_zakat),
                contentDescription = "Play",
                tint = Color.Unspecified,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun FeatureSection(features: List<Feature>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Nisab",
            style = MaterialTheme.typography.h1,
            color = PrimaryDarkColor,
            modifier = Modifier.padding(15.dp)
        )
        LazyVerticalGrid(
            cells = GridCells.Fixed(2),
            contentPadding = PaddingValues(start = 7.5.dp, end = 7.5.dp, bottom = 100.dp),
            modifier = Modifier.fillMaxHeight()
        ) {
            items(features.size) {
                FeatureItem(feature = features[it])
            }
        }
    }
}

@Composable
fun FeatureItem(
    feature: Feature
) {
    BoxWithConstraints(
        modifier = Modifier
            .padding(8.dp)
            .aspectRatio(1f)
            .clip(RoundedCornerShape(16.dp))
            .background(feature.darkColor)
    ) {
        val width = constraints.maxWidth
        val height = constraints.maxHeight

        val mediumColoredPath = Path().apply {
            // Medium colored path
            val mediumColoredPoint1 = Offset(0f, height * 0.3f)
            val mediumColoredPoint2 = Offset(width * 0.1f, height * 0.35f)
            val mediumColoredPoint3 = Offset(width * 0.4f, height * 0.05f)
            val mediumColoredPoint4 = Offset(width * 0.75f, height * 0.7f)
            val mediumColoredPoint5 = Offset(width * 1.4f, -height.toFloat())

            moveTo(mediumColoredPoint1.x, mediumColoredPoint1.y)
            standardQuadFromTo(mediumColoredPoint1, mediumColoredPoint2)
            standardQuadFromTo(mediumColoredPoint2, mediumColoredPoint3)
            standardQuadFromTo(mediumColoredPoint3, mediumColoredPoint4)
            standardQuadFromTo(mediumColoredPoint4, mediumColoredPoint5)
            lineTo(width.toFloat() + 100f, height.toFloat() + 100f)
            lineTo(-100f, height.toFloat() + 100f)
            close()
        }
        val lightColoredPath = Path().apply {
            // Light colored path
            val lightPoint1 = Offset(0f, height * 0.35f)
            val lightPoint2 = Offset(width * 0.1f, height * 0.4f)
            val lightPoint3 = Offset(width * 0.3f, height * 0.35f)
            val lightPoint4 = Offset(width * 0.65f, height.toFloat())
            val lightPoint5 = Offset(width * 1.4f, -height.toFloat() / 3f)
            moveTo(lightPoint1.x, lightPoint1.y)
            standardQuadFromTo(lightPoint1, lightPoint2)
            standardQuadFromTo(lightPoint2, lightPoint3)
            standardQuadFromTo(lightPoint3, lightPoint4)
            standardQuadFromTo(lightPoint4, lightPoint5)
            lineTo(width.toFloat() + 100f, height.toFloat() + 100f)
            lineTo(-100f, height.toFloat() + 100f)
            close()
        }

        Canvas(
            modifier = Modifier.fillMaxSize()
        ) {
            drawPath(
                path = mediumColoredPath,
                color = feature.mediumColor
            )
            drawPath(
                path = lightColoredPath,
                color = feature.lightColor
            )
        }
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)) {
            ConstraintLayout(modifier = Modifier.fillMaxSize()) {
                val(title,subtitle,icon,button) = createRefs()
                Text(
                    text = feature.title,
                    style = MaterialTheme.typography.h2,
                    lineHeight = 26.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .constrainAs(title) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }


                )
                Text(
                    text = Utils.getCurrencySymbol()+ Utils.getAmountWithCommas(feature.subtitle),
                    style = MaterialTheme.typography.h2,
                    color = TextWhite,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(0.dp,8.dp)
                        .constrainAs(subtitle) {
                            top.linkTo(title.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                )
                Icon(
                    painter = painterResource(id = feature.iconId),
                    contentDescription = feature.title,
                    tint = Color.Unspecified,
                    modifier = Modifier.constrainAs(icon){
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                    }
                )
                Text(
                    text = "Add",
                    color = TextWhite,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .clickable {
                            // Handle the click
                        }
                        .clip(RoundedCornerShape(4.dp))
                        .background(ButtonBlue)
                        .padding(vertical = 6.dp, horizontal = 15.dp)
                        .constrainAs(button) {
                            bottom.linkTo(parent.bottom)
                            end.linkTo(parent.end)
                        }
                )
            }
        }
    }
}