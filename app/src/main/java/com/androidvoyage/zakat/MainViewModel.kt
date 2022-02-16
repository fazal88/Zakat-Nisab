package com.androidvoyage.zakat

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.androidvoyage.zakat.model.Features
import com.androidvoyage.zakat.model.MetalResponse
import com.androidvoyage.zakat.model.NisabApi
import com.androidvoyage.zakat.util.LogUtils
import com.androidvoyage.zakat.util.Utils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

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