package com.androidvoyage.zakat.feature_nisab.presentation.home_screen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.androidvoyage.zakat.R
import com.androidvoyage.zakat.ui.theme.LightRed
import com.androidvoyage.zakat.ui.theme.TextWhite

/**
 * Created by Fazal on 26/02/22.
 * Copyright (c) 2022 Fazal. All rights reserved.
 */

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