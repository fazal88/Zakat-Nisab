package com.androidvoyage.zakat.feature_nisab.presentation.add_nisab

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androidvoyage.zakat.R
import com.androidvoyage.zakat.ZakatApp
import com.androidvoyage.zakat.feature_nisab.domain.model.InvalidNisabException
import com.androidvoyage.zakat.feature_nisab.domain.model.Nisab
import com.androidvoyage.zakat.feature_nisab.domain.use_case.NisabUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditNisabViewModel @Inject constructor(
    private val nisabUseCases: NisabUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _nisabTitle = mutableStateOf(
        NisabTextFieldState(
        hint = ZakatApp.getInstance().getString(R.string.str_hint)
    )
    )
    val nisabTitle: State<NisabTextFieldState> = _nisabTitle

    private val _dropDown = mutableStateOf(
        true
    )
    val dropDown: State<Boolean> = _dropDown

    private val _nisabContent = mutableStateOf(
        NisabTextFieldState(
        hint = "Enter some content"
    )
    )
    val nisabContent: State<NisabTextFieldState> = _nisabContent

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentNoteId: Long? = null

    init {
        savedStateHandle.get<Long>("nisabId")?.let { nisabId ->
            if(nisabId > -1) {
                viewModelScope.launch {
                    nisabUseCases.getNisab(nisabId)?.also { nisab ->
                        currentNoteId = nisab.id
                        _nisabTitle.value = nisabTitle.value.copy(
                            text = nisab.name,
                            isHintVisible = false
                        )
                        _nisabContent.value = _nisabContent.value.copy(
                            text = nisab.price.toString(),
                            isHintVisible = false
                        )
                        /*_nisabColor.value = nisab.type*/
                    }
                }
            }
        }
    }

    fun onEvent(event: AddEditNisabEvent) {
        when(event) {
            is AddEditNisabEvent.EnteredTitle -> {
                _nisabTitle.value = nisabTitle.value.copy(
                    text = event.value
                )
            }
            is AddEditNisabEvent.ChangeTitleFocus -> {
                _nisabTitle.value = nisabTitle.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            nisabTitle.value.text.isBlank()
                )
            }
            is AddEditNisabEvent.EnteredContent -> {
                _nisabContent.value = _nisabContent.value.copy(
                    text = event.value
                )
            }
            is AddEditNisabEvent.ChangeContentFocus -> {
                _nisabContent.value = _nisabContent.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            _nisabContent.value.text.isBlank()
                )
            }
            is AddEditNisabEvent.ChangeColor -> {
                /*_nisabColor.value = event.color*/
            }
            is AddEditNisabEvent.SaveNisab -> {
                viewModelScope.launch {
                    try {
                        nisabUseCases.addNisab(
                            Nisab(
                                name = nisabTitle.value.text,
                                price = nisabContent.value.text.toLong(),
                                date = System.currentTimeMillis(),
                                id = currentNoteId
                            )
                        )
                        _eventFlow.emit(UiEvent.SaveNisab)
                    } catch(e: InvalidNisabException) {
                        _eventFlow.emit(
                            UiEvent.ShowSnackbar(
                                message = e.message ?: "Couldn't save nisab"
                            )
                        )
                    }
                }
            }
        }
    }

    sealed class UiEvent {
        data class ShowSnackbar(val message: String): UiEvent()
        object SaveNisab: UiEvent()
    }
}