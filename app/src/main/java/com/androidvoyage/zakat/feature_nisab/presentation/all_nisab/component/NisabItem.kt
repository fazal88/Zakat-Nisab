package com.androidvoyage.zakat.feature_nisab.presentation.all_nisab.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.core.graphics.ColorUtils
import com.androidvoyage.zakat.R
import com.androidvoyage.zakat.feature_nisab.domain.model.Nisab
import com.androidvoyage.zakat.feature_nisab.presentation.util.Features

@Composable
fun NisabItem(
    nisab: Nisab,
    modifier: Modifier = Modifier,
    cornerRadius: Dp = 10.dp,
    cutCornerSize: Dp = 30.dp,
    onDeleteClick: () -> Unit
) {
    Box(
        modifier = modifier
    ) {
        Canvas(modifier = Modifier.matchParentSize()) {
            val clipPath = Path().apply {
                lineTo(size.width - cutCornerSize.toPx(), 0f)
                lineTo(size.width, cutCornerSize.toPx())
                lineTo(size.width, size.height)
                lineTo(0f, size.height)
                close()
            }

            clipPath(clipPath) {
                drawRoundRect(
                    color = Features.getColor(nisab.type),
                    size = size,
                    cornerRadius = CornerRadius(cornerRadius.toPx())
                )
                drawRoundRect(
                    color = Color(
                        ColorUtils.blendARGB(Features.getColor(nisab.type).toArgb(), 0x000000, 0.2f)
                    ),
                    topLeft = Offset(size.width - cutCornerSize.toPx(), -100f),
                    size = Size(cutCornerSize.toPx() + 100f, cutCornerSize.toPx() + 100f),
                    cornerRadius = CornerRadius(cornerRadius.toPx())
                )
            }
        }
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            val (icon, type, title, iconRupee, amount, description, delete) = createRefs()
            Icon(
                painter = painterResource(id = Features.getIcon(nisab.type)),
                contentDescription = "nisabType",
                tint = Color.Unspecified,
                modifier = Modifier
                    .padding(16.dp)
                    .size(24.dp)
                    .constrainAs(icon) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                        width = Dimension.wrapContent
                        height = Dimension.wrapContent
                    }
            )
            Text(
                text = nisab.name,
                style = MaterialTheme.typography.h2,
                color = Color.White,
                modifier = Modifier
                    .padding(top=16.dp,end = 32.dp)
                    .constrainAs(title) {
                        top.linkTo(parent.top)
                        start.linkTo(icon.end)
                        end.linkTo(parent.end)
                        width = Dimension.fillToConstraints
                        height = Dimension.wrapContent
                    }
            )
            Text(
                text = nisab.type,
                style = MaterialTheme.typography.subtitle1,
                color = Color.White,
                fontSize = 12.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .constrainAs(type) {
                        top.linkTo(title.bottom)
                        start.linkTo(title.start)
                        end.linkTo(title.end)
                        width = Dimension.fillToConstraints
                    }
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_rupee),
                contentDescription = "Rupee",
                tint = Color.Unspecified,
                modifier = Modifier
                    .padding(16.dp)
                    .size(24.dp)
                    .constrainAs(iconRupee) {
                    top.linkTo(icon.bottom)
                    start.linkTo(icon.start)
                })
            Text(
                text = nisab.price.toString(),
                style = MaterialTheme.typography.h2,
                color = Color.White,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .wrapContentSize()
                    .constrainAs(amount) {
                        top.linkTo(iconRupee.top)
                        start.linkTo(iconRupee.end)
                        bottom.linkTo(iconRupee.bottom)
                    }
            )
            IconButton(
                onClick = onDeleteClick,
                modifier = Modifier.constrainAs(delete) {
                    end.linkTo(parent.end)
                    top.linkTo(iconRupee.top)
                    bottom.linkTo(iconRupee.bottom)
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete note",
                    tint = Color.Black
                )
            }
        }
    }
}