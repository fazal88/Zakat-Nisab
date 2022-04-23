package com.androidvoyage.zakat.screens.dashboard

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.androidvoyage.zakat.model.Features
import com.androidvoyage.zakat.pref.SharedPreferencesManager

class DashboardViewModel : ViewModel() {

    val rate24  = MutableLiveData(SharedPreferencesManager.getInstance().getRate(Features.PREF_24_K))
    val rate23  = MutableLiveData(SharedPreferencesManager.getInstance().getRate(Features.PREF_22_K))
    val rate22  = MutableLiveData(SharedPreferencesManager.getInstance().getRate(Features.PREF_18_K))
    val rate18  = MutableLiveData(SharedPreferencesManager.getInstance().getRate(Features.PREF_14_K))
    val rate14  = MutableLiveData(SharedPreferencesManager.getInstance().getRate(Features.PREF_23_KDM))
    val rateSilver  = MutableLiveData(SharedPreferencesManager.getInstance().getRate(Features.PREF_SILVER))

    val isOptionClick = MutableLiveData(false)

    val clickedFeature = MutableLiveData<String>(null)
    val clickedAddFeature = MutableLiveData<String>(null)
    val clickListener = DashboardListAdapter.DashboardClickListener(
        { item ->
            clickedFeature.postValue(item)
        },
        { item ->
            clickedAddFeature.postValue(item)
        }
    )

    val adapter = DashboardListAdapter(clickListener)

    fun onClickOption() {
        if (!isOptionClick.value!!) {
            isOptionClick.value = true
        }
    }
}