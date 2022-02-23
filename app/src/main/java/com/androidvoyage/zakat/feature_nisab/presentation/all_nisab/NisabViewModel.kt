package com.androidvoyage.zakat.feature_nisab.presentation.all_nisab

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androidvoyage.zakat.feature_nisab.domain.model.Nisab
import com.androidvoyage.zakat.feature_nisab.domain.util.NisabOrder
import com.androidvoyage.zakat.feature_nisab.domain.use_case.NisabUseCases
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.util.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NisabViewModel @Inject constructor(
    private val nisabUseCases: NisabUseCases
) : ViewModel() {

    private val _state = mutableStateOf(NisabState())
    val state: State<NisabState> = _state

    private var recentlyDeletedNisab: Nisab? = null

    private var getNisabsJob: Job? = null

    init {
        getNisabs(NisabOrder.Date(OrderType.Descending))
    }

    fun onEvent(event: NisabEvent) {
        when (event) {
            is NisabEvent.Order -> {
                if (state.value.nisabOrder::class == event.noteOrder::class &&
                    state.value.nisabOrder.orderType == event.noteOrder.orderType
                ) {
                    return
                }
                getNisabs(event.noteOrder)
            }
            is NisabEvent.DeleteNisab -> {
                viewModelScope.launch {
                    nisabUseCases.deleteNisab(event.note)
                    recentlyDeletedNisab = event.note
                }
            }
            is NisabEvent.RestoreNisab -> {
                viewModelScope.launch {
                    nisabUseCases.addNisab(recentlyDeletedNisab ?: return@launch)
                    recentlyDeletedNisab = null
                }
            }
            is NisabEvent.ToggleOrderSection -> {
                _state.value = state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )
            }
        }
    }

    private fun getNisabs(noteOrder: NisabOrder) {
        getNisabsJob?.cancel()
        getNisabsJob = nisabUseCases.getAllNisab(noteOrder)
            .onEach { nisabs ->
                _state.value = state.value.copy(
                    nisabs = nisabs,
                    nisabOrder = noteOrder
                )
            }
            .launchIn(viewModelScope)
    }
}