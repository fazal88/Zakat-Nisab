package com.plcoding.cleanarchitecturenoteapp.feature_note.domain.use_case

import com.androidvoyage.zakat.feature_nisab.domain.use_case.AddNisab
import com.androidvoyage.zakat.feature_nisab.domain.use_case.DeleteNisab
import com.androidvoyage.zakat.feature_nisab.domain.use_case.GetNisab
import com.androidvoyage.zakat.feature_nisab.domain.use_case.GetAllNisab

data class NisabUseCases(
    val getAllNisab: GetAllNisab,
    val deleteNisab: DeleteNisab,
    val addNisab: AddNisab,
    val getNisab: GetNisab
)
