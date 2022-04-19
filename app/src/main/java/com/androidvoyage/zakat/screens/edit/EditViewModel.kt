package com.androidvoyage.zakat.screens.edit

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.androidvoyage.zakat.model.NisabItem

class EditViewModel : ViewModel() {

    val isMetal = MutableLiveData(false)


    val nisabItem  = MutableLiveData(NisabItem())


    fun setType(item : String) {
        nisabItem.value?.type = item
        notifyModel()
    }

    fun setKarat(item: String) {
        nisabItem.value?.purity = item
        notifyModel()
    }

    private fun notifyModel() {
        nisabItem.postValue(nisabItem.value)
    }

    fun getNisab() : NisabItem {
        nisabItem.value?.estimatedValue = nisabItem.value?.price!!
        return nisabItem.value!!
    }
}