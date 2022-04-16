package com.androidvoyage.zakat.feature_nisab.presentation.add_nisab.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun TransparentHintTextField(
    text: String,
    hint: String,
    modifier: Modifier = Modifier,
    isHintVisible: Boolean = true,
    onValueChange: (String) -> Unit,
    textStyle: TextStyle = TextStyle(),
    singleLine: Boolean = false,
    drawableStart: Int? = null,
    keyboardOptions : KeyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.Text
    ),
    textAlignment : Alignment.Vertical = Alignment.CenterVertically,
    onFocusChange: (FocusState) -> Unit
) {
    Row(modifier = modifier) {
        if (drawableStart != null) {
            Icon(
                painter = painterResource(id = drawableStart),
                contentDescription = text,
                tint = Color.Unspecified,
                modifier = Modifier
                    .size(36.dp)
                    .padding(end = 16.dp)
                    .align(Alignment.CenterVertically)
            )
        }
        Box(modifier = Modifier
            .wrapContentHeight()
            .align(textAlignment)){
            BasicTextField(
                value = text,
                onValueChange = onValueChange,
                singleLine = singleLine,
                keyboardOptions = keyboardOptions,
                textStyle = textStyle,
                modifier = Modifier
                    .fillMaxWidth()
                    .onFocusChanged {
                        onFocusChange(it)
                    }
            )
            if(isHintVisible) {
                Text(text = hint, style = textStyle, color = Color.LightGray)
            }
        }
    }
}