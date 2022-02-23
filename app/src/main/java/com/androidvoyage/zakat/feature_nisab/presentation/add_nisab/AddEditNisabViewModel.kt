package com.androidvoyage.zakat.feature_nisab.presentation.add_nisab

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androidvoyage.zakat.feature_nisab.domain.model.InvalidNisabException
import com.androidvoyage.zakat.feature_nisab.domain.model.Nisab
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.use_case.NisabUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditNisabViewModel @Inject constructor(
    private val noteUseCases: NisabUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _noteTitle = mutableStateOf(
        NisabTextFieldState(
        hint = "Enter title..."
    )
    )
    val nisabTitle: State<NisabTextFieldState> = _noteTitle

    private val _noteContent = mutableStateOf(
        NisabTextFieldState(
        hint = "Enter some content"
    )
    )
    val nisabContent: State<NisabTextFieldState> = _noteContent

    /*private val _noteColor = mutableStateOf(Nisab.noteColors.random().toArgb())
    val noteColor: State<Int> = _noteColor*/

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentNoteId: Long? = null

    init {
        savedStateHandle.get<Int>("noteId")?.let { noteId ->
            if(noteId != -1) {
                viewModelScope.launch {
                    noteUseCases.getNisab(noteId)?.also { note ->
                        currentNoteId = note.id
                        _noteTitle.value = nisabTitle.value.copy(
                            text = note.name,
                            isHintVisible = false
                        )
                        _noteContent.value = _noteContent.value.copy(
                            text = note.price.toString(),
                            isHintVisible = false
                        )
                        /*_noteColor.value = note.type*/
                    }
                }
            }
        }
    }

    fun onEvent(event: AddEditNisabEvent) {
        when(event) {
            is AddEditNisabEvent.EnteredTitle -> {
                _noteTitle.value = nisabTitle.value.copy(
                    text = event.value
                )
            }
            is AddEditNisabEvent.ChangeTitleFocus -> {
                _noteTitle.value = nisabTitle.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            nisabTitle.value.text.isBlank()
                )
            }
            is AddEditNisabEvent.EnteredContent -> {
                _noteContent.value = _noteContent.value.copy(
                    text = event.value
                )
            }
            is AddEditNisabEvent.ChangeContentFocus -> {
                _noteContent.value = _noteContent.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            _noteContent.value.text.isBlank()
                )
            }
            is AddEditNisabEvent.ChangeColor -> {
                /*_noteColor.value = event.color*/
            }
            is AddEditNisabEvent.SaveNisab -> {
                viewModelScope.launch {
                    try {
                        noteUseCases.addNisab(
                            Nisab(
                                name = nisabTitle.value.text,
                                price = nisabContent.value.text.toLong(),
                                date = System.currentTimeMillis(),
                                id = currentNoteId
                            )
                        )
                        _eventFlow.emit(UiEvent.SaveNote)
                    } catch(e: InvalidNisabException) {
                        _eventFlow.emit(
                            UiEvent.ShowSnackbar(
                                message = e.message ?: "Couldn't save note"
                            )
                        )
                    }
                }
            }
        }
    }

    sealed class UiEvent {
        data class ShowSnackbar(val message: String): UiEvent()
        object SaveNote: UiEvent()
    }
}