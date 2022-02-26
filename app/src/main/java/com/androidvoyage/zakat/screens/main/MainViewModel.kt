package com.androidvoyage.zakat.screens.main

import androidx.lifecycle.ViewModel
import com.androidvoyage.zakat.api.RetrofitClient
import com.androidvoyage.zakat.model.NisabApi
import com.androidvoyage.zakat.util.LogUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val TAG: String = "MainViewModel"

    init {
        callMetalsApi()
    }

    private fun callMetalsApi() {

        val api = RetrofitClient.restAdapter.create(NisabApi::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            val response = api.getRates()
            if(response.isSuccessful){
            LogUtils.d(TAG,"Success : ${response.body()}")

            }
        }

    }

}