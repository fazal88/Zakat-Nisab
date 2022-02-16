package com.androidvoyage.zakat.screens.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.androidvoyage.zakat.model.NisabItem

class ListViewModel : ViewModel() {

    lateinit var listNisab : LiveData<List<NisabItem>>

    val editNisab = MutableLiveData<NisabItem>(null)
    val deleteNisab = MutableLiveData<NisabItem>(null)

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