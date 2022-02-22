package com.androidvoyage.zakat.feature_nisab.presentation

sealed class Screen(val route: String) {
    object AllNisabScreen: Screen("all_nisab_screen")
    object AddEditNisabScreen: Screen("add_edit_nisab_screen")
}
