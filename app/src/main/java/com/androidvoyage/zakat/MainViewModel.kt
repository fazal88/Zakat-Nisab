package com.androidvoyage.zakat

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.androidvoyage.zakat.model.Features

class MainViewModel : ViewModel() {

    val isSplash = MutableLiveData(true)
    val isList = MutableLiveData(false)

}