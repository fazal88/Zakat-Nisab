package com.androidvoyage.zakat.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.androidvoyage.zakat.HomeScreen
import com.androidvoyage.zakat.ui.theme.MyZakatTheme

@ExperimentalFoundationApi
class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyZakatTheme() {
                DefaultPreview()
            }
        }
    }


}

@ExperimentalFoundationApi
@Preview
@Composable
fun DefaultPreview() {
    HomeScreen()
}