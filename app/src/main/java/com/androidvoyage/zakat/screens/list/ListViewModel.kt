package com.androidvoyage.zakat.screens.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.androidvoyage.zakat.model.NisabItem

class ListViewModel : ViewModel() {
    fun setNisabType(type: String) {
        typeNisab.value = type
    }

    lateinit var listNisab : LiveData<List<NisabItem>>

    val editNisab = MutableLiveData<NisabItem>(null)
    val deleteNisab = MutableLiveData<NisabItem>(null)
    val typeNisab = MutableLiveData<String>(null)

    val adapter = NisabListAdapter(
        NisabListAdapter.NisabClickListener(
            { item ->
                editNisab.postValue(item)
            },
            { item ->
                deleteNisab.postValue(item)
            }
        )
    )

}