package com.androidvoyage.zakat.screens.dashboard

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.androidvoyage.zakat.model.Features
import com.androidvoyage.zakat.model.NisabCategoryItem

class DashboardViewModel : ViewModel() {

    val isOptionClick = MutableLiveData(false)

    val clickedFeature = MutableLiveData<String>(null)
    val clickedAddFeature = MutableLiveData<String>(null)
    val overview = MutableLiveData<NisabCategoryItem>()
    val clickListener = DashboardListAdapter.DashboardClickListener(
        { item ->
            clickedFeature.postValue(item)
        },
        { item ->
            clickedAddFeature.postValue(item)
        }
    )

    val adapter = DashboardListAdapter(clickListener)

    init {
        overview.value = NisabCategoryItem(
            "Over All",
            0,
            0,
            System.currentTimeMillis()
        )

        val list = mutableListOf<NisabCategoryItem>()
        for (i in Features.prefTitleList){
            list.add(NisabCategoryItem(i,0,0,System.currentTimeMillis()))
        }
        adapter.submitList(list)
    }

    fun onClickOption() {
        if (!isOptionClick.value!!) {
            isOptionClick.value = true
        }
    }
}