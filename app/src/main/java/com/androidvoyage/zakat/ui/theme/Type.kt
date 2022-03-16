package com.androidvoyage.zakat.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.androidvoyage.zakat.R


val monterserratA1 = FontFamily(
    listOf(
        Font(R.font.montserrat_light, FontWeight.Normal),
        Font(R.font.montserrat_regular, FontWeight.Medium),
        Font(R.font.montserrat_semibold, FontWeight.SemiBold),
        Font(R.font.montserrat_semibold, FontWeight.Bold),
    )
)

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        color = AquaBlue,
        fontFamily = monterserratA1,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    body2 = TextStyle(
        color = ColorBlack,
        fontFamily = monterserratA1,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    h1 = TextStyle(
        color = TextWhite,
        fontFamily = monterserratA1,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp
    ),
    h2 = TextStyle(
        color = TextWhite,
        fontFamily = monterserratA1,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp
    )
)