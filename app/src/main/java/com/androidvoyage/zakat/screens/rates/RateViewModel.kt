package com.androidvoyage.zakat.screens.rates

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.androidvoyage.zakat.model.Features
import com.androidvoyage.zakat.model.NisabItem
import com.androidvoyage.zakat.pref.SharedPreferencesManager

class RateViewModel : ViewModel() {

    val rate24  = MutableLiveData(SharedPreferencesManager.getInstance().getRate(Features.PREF_24_K))
    val rate22  = MutableLiveData(SharedPreferencesManager.getInstance().getRate(Features.PREF_22_K))
    val rate18  = MutableLiveData(SharedPreferencesManager.getInstance().getRate(Features.PREF_18_K))
    val rate14  = MutableLiveData(SharedPreferencesManager.getInstance().getRate(Features.PREF_14_K))
    val rate23  = MutableLiveData(SharedPreferencesManager.getInstance().getRate(Features.PREF_23_KDM))
    val rateSilver  = MutableLiveData(SharedPreferencesManager.getInstance().getRate(Features.PREF_SILVER))


    val error24 = MutableLiveData("")
    val error22 = MutableLiveData("")
    val error18 = MutableLiveData("")
    val error14 = MutableLiveData("")
    val error23 = MutableLiveData("")
    val errorSilver = MutableLiveData("")


}