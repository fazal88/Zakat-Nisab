package com.androidvoyage.zakat.model

/**
 * Created by Fazal on 16/04/22.
 * Copyright (c) 2022 PayNearby. All rights reserved.
 */
data class NisabCategoryItem(
    var type : String,
    val totalValue : Long,
    val zakatValue : Long,
    val lastUpdated : Long,
)
