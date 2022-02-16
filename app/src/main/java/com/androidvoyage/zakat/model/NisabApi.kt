package com.androidvoyage.zakat.model

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

/**
 * Created by Fazal on 17/02/22.
 * Copyright (c) 2022 PayNearby. All rights reserved.
 */
interface NisabApi {

    @GET("/api/latest?access_key=6gbq15qxj3phen23176hypkwit3bbnf5a9yxdt5jr637z1lr0wszgk1bog0h&base=INR&symbols=XAU,XAG")
    suspend fun getRates() : Response<MetalResponse>

}