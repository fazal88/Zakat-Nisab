package com.androidvoyage.zakat.screens.edit

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.androidvoyage.zakat.model.Features
import com.androidvoyage.zakat.model.NisabItem
import com.androidvoyage.zakat.pref.SharedPreferencesManager
import com.androidvoyage.zakat.util.Utils
import kotlin.math.roundToLong

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
        if(nisabItem.value?.type == Features.PREF_GOLD_SILVER){
            val grams = nisabItem.value?.weight!!
            val rate = SharedPreferencesManager.getInstance().getRate(nisabItem.value?.purity)
            val estimatedValue = grams.toFloat() * rate.toFloat()
            val roundOffEstimatedValue = estimatedValue.roundToLong()
            nisabItem.value?.estimatedValue = roundOffEstimatedValue.toString()
        }else{
            nisabItem.value?.estimatedValue = nisabItem.value?.price!!
        }
        return nisabItem.value!!
    }
}