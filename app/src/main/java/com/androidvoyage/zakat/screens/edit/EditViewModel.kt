package com.androidvoyage.zakat.screens.edit

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.androidvoyage.zakat.model.NisabItem

class EditViewModel : ViewModel() {

    val isMetal = MutableLiveData(false)


    val nisanItem = MutableLiveData(NisabItem())


    fun setType(item : String) {
        nisanItem.value?.type = item
        notifyModel()
    }

    fun setKarat(item: String) {
        nisanItem.value?.karat = item
        notifyModel()
    }

    private fun notifyModel() {
        nisanItem.postValue(nisanItem.value)
    }
}