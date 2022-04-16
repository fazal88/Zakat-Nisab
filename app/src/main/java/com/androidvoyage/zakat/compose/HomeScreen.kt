package com.androidvoyage.zakat

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
import androidx.compose.foundation.lazy.LazyRow
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.androidvoyage.zakat.compose.BottomMenuContent
import com.androidvoyage.zakat.feature_nisab.presentation.home_screen.component.Feature
import com.androidvoyage.zakat.ui.theme.*
import com.androidvoyage.zakat.util.Utils
import kotlin.random.Random

@ExperimentalFoundationApi
@Preview
@Composable
fun HomeScreen() {
    Scaffold(
        topBar = { AppTopBar() },
        bottomBar = { AppBottomBar(RoundedCornerShape(50)) },
        floatingActionButton = {
            CenterButton()
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
                CurrentMeditation()
                FeatureSection(
                    features = listOf(
                        Feature(
                            title = "Cash in Hand",
                            subtitle = Random.nextInt(0,100_00_00_00).toString(),
                            R.drawable.ic_cash,
                            BlueViolet1,
                            BlueViolet2,
                            BlueViolet3
                        ),
                        Feature(
                            title = "Gold + Silver",
                            subtitle = Random.nextInt(0,100_00_00_00).toString(),
                            R.drawable.ic_gold,
                            LightGreen1,
                            LightGreen2,
                            LightGreen3
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
                            Violet1,
                            Violet2,
                            Violet3
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
                            Blue1,
                            Blue2,
                            Blue3
                        ),
                        Feature(
                            title = "Properties + Cars",
                            subtitle = Random.nextInt(0,100_00_00_00).toString(),
                            R.drawable.ic_asset,
                            Beige1,
                            Beige2,
                            Beige3
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
fun CenterButton() {
    FloatingActionButton(
        onClick = {

        },
        shape = RoundedCornerShape(50),
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = Color.White
    ) {
        Icon(Icons.Filled.Add, "")
    }
}

@Composable
fun BottomMenu(
    items: List<BottomMenuContent>,
    modifier: Modifier = Modifier,
    activeHighlightColor: Color = ButtonBlue,
    activeTextColor: Color = Color.White,
    inactiveTextColor: Color = AquaBlue,
    initialSelectedItemIndex: Int = 0
) {
    var selectedItemIndex by remember {
        mutableStateOf(initialSelectedItemIndex)
    }
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .background(DeepBlue)
            .padding(15.dp)
    ) {
        items.forEachIndexed { index, item ->
            BottomMenuItem(
                item = item,
                isSelected = index == selectedItemIndex,
                activeHighlightColor = activeHighlightColor,
                activeTextColor = activeTextColor,
                inactiveTextColor = inactiveTextColor
            ) {
                selectedItemIndex = index
            }
        }
    }
}

@Composable
fun BottomMenuItem(
    item: BottomMenuContent,
    isSelected: Boolean = false,
    activeHighlightColor: Color = ButtonBlue,
    activeTextColor: Color = Color.White,
    inactiveTextColor: Color = AquaBlue,
    onItemClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.clickable {
            onItemClick()
        }
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(if (isSelected) activeHighlightColor else Color.Transparent)
                .padding(10.dp)
        ) {
            Icon(
                painter = painterResource(id = item.iconId),
                contentDescription = item.title,
                tint = if (isSelected) activeTextColor else inactiveTextColor,
                modifier = Modifier.size(20.dp)
            )
        }
        Text(
            text = item.title,
            color = if (isSelected) activeTextColor else inactiveTextColor
        )
    }
}

@Composable
fun ChipSection(
    chips: List<String>
) {
    var selectedChipIndex by remember {
        mutableStateOf(0)
    }
    LazyRow {
        items(chips.size) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(start = 15.dp, top = 15.dp, bottom = 15.dp)
                    .clickable {
                        selectedChipIndex = it
                    }
                    .clip(RoundedCornerShape(10.dp))
                    .background(
                        if (selectedChipIndex == it) ButtonBlue
                        else DarkerButtonBlue
                    )
                    .padding(15.dp)
            ) {
                Text(text = chips[it], color = TextWhite)
            }
        }
    }
}

@Composable
fun CurrentMeditation(
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
            .padding(7.5.dp)
            .aspectRatio(1f)
            .clip(RoundedCornerShape(10.dp))
            .background(feature.darkColor)
    ) {
        val width = constraints.maxWidth
        val height = constraints.maxHeight

        // Medium colored path
        val mediumColoredPoint1 = Offset(0f, height * 0.3f)
        val mediumColoredPoint2 = Offset(width * 0.1f, height * 0.35f)
        val mediumColoredPoint3 = Offset(width * 0.4f, height * 0.05f)
        val mediumColoredPoint4 = Offset(width * 0.75f, height * 0.7f)
        val mediumColoredPoint5 = Offset(width * 1.4f, -height.toFloat())

        val mediumColoredPath = Path().apply {
            moveTo(mediumColoredPoint1.x, mediumColoredPoint1.y)
            standardQuadFromTo(mediumColoredPoint1, mediumColoredPoint2)
            standardQuadFromTo(mediumColoredPoint2, mediumColoredPoint3)
            standardQuadFromTo(mediumColoredPoint3, mediumColoredPoint4)
            standardQuadFromTo(mediumColoredPoint4, mediumColoredPoint5)
            lineTo(width.toFloat() + 100f, height.toFloat() + 100f)
            lineTo(-100f, height.toFloat() + 100f)
            close()
        }

        // Light colored path
        val lightPoint1 = Offset(0f, height * 0.35f)
        val lightPoint2 = Offset(width * 0.1f, height * 0.4f)
        val lightPoint3 = Offset(width * 0.3f, height * 0.35f)
        val lightPoint4 = Offset(width * 0.65f, height.toFloat())
        val lightPoint5 = Offset(width * 1.4f, -height.toFloat() / 3f)

        val lightColoredPath = Path().apply {
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
            modifier = Modifier
                .fillMaxSize()
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