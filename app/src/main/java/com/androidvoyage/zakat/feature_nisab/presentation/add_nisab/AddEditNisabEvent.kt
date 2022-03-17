package com.androidvoyage.zakat.feature_nisab.presentation.add_nisab

import androidx.compose.ui.focus.FocusState

sealed class AddEditNisabEvent{
    data class EnteredTitle(val value: String): AddEditNisabEvent()
    data class ChangeTitleFocus(val focusState: FocusState): AddEditNisabEvent()
    data class EnteredAmount(val value: String): AddEditNisabEvent()
    data class ChangeAmountFocus(val focusState: FocusState): AddEditNisabEvent()
    data class SelectedType(val value: String): AddEditNisabEvent()
    data class EnteredContent(val value: String): AddEditNisabEvent()
    data class ChangeContentFocus(val focusState: FocusState): AddEditNisabEvent()
    data class ChangeColor(val color: Int): AddEditNisabEvent()
    object SaveNisab: AddEditNisabEvent()
}

