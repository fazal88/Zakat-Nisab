package com.androidvoyage.zakat.feature_nisab.presentation

sealed class Screen(val route: String) {
    object HomeScreen: Screen("home")
    object AddEditNisabScreen: Screen("add_edit_nisab_screen")
}
