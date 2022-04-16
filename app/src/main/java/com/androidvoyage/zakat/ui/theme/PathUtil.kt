package com.androidvoyage.zakat.ui.theme

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import java.lang.Math.abs

/**
 * Created by Fazal on 14/02/22.
 * Copyright (c) 2022 Fazal. All rights reserved.
 */
fun Path.standardQuadFromTo(from: Offset, to: Offset) {
    quadraticBezierTo(
        from.x,
        from.y,
        abs(from.x + to.x) / 2f,
        abs(from.y + to.y) / 2f
    )
}