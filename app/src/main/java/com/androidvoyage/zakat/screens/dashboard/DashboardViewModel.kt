package com.androidvoyage.zakat.screens.dashboard

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.androidvoyage.zakat.model.Features

class DashboardViewModel : ViewModel() {

    val isOptionClick = MutableLiveData(false)

    val clickedFeature = MutableLiveData<String>(null)
    val clickedAddFeature = MutableLiveData<String>(null)

    val adapter = DashboardListAdapter(
        DashboardListAdapter.DashboardClickListener(
            { item ->
                clickedAddFeature.postValue(item)
            },
            { item ->
                clickedFeature.postValue(item)
            }
        )
    )

    init {
        adapter.submitList(Features.prefKeyList)
    }

    fun onClickOption() {
        if (!isOptionClick.value!!) {
            isOptionClick.value = true
        }
    }
}