package com.androidvoyage.zakat.feature_nisab.presentation.home_screen.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.androidvoyage.zakat.R
import com.androidvoyage.zakat.ui.theme.PrimaryDarkColor
import com.androidvoyage.zakat.ui.theme.PrimaryLightBack

/**
 * Created by Fazal on 26/02/22.
 * Copyright (c) 2022 PayNearby. All rights reserved.
 */

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