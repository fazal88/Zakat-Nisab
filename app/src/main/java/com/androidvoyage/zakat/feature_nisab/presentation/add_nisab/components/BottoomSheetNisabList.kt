package com.androidvoyage.zakat.feature_nisab.presentation.add_nisab.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.androidvoyage.zakat.model.Features

/**
 * Created by Fazal on 17/03/22.
 * Copyright (c) 2022 PayNearby. All rights reserved.
 */



@Composable
fun BottomSheetNisabType(typeSelected: String, callbackFun: (nisabType: String) -> Unit) {
    Column() {
        for (nisabType in Features.prefTitleList) {
            NisabDropdownItem(nisabType = nisabType, typeSelected, callbackFun)
        }
    }

}

@Composable
fun NisabDropdownItem(
    nisabType: String,
    selected: String,
    callbackFun: (nisabType: String) -> Unit
) {
    Box(
        modifier = Modifier
            .wrapContentSize()
            .padding(16.dp)
            .clickable {
                callbackFun(nisabType)
            }
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            val (title, icon) = createRefs()
            Icon(
                painter = painterResource(id = Features.getIcon(nisabType)),
                contentDescription = nisabType,
                tint = Color.Unspecified,
                modifier = Modifier
                    .constrainAs(icon) {
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                        width = Dimension.wrapContent
                        height = Dimension.wrapContent
                    }
            )
            Text(
                text = nisabType,
                style = MaterialTheme.typography.h2,
                color = Color.Black,
                modifier = Modifier
                    .padding(16.dp)
                    .constrainAs(title) {
                        top.linkTo(parent.top)
                        start.linkTo(icon.end)
                        bottom.linkTo(parent.bottom)
                        end.linkTo(parent.end)
                        width = Dimension.fillToConstraints
                        height = Dimension.wrapContent
                    }
            )
        }
    }
}