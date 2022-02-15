package com.androidvoyage.zakat.screens.dashboard

import androidx.lifecycle.ViewModel
import com.androidvoyage.zakat.model.Features

class DashboardViewModel : ViewModel() {
    val adapter = DashboardListAdapter(
        DashboardListAdapter.DashboardClickListener(
            { item ->
                //add click
            },
            { item ->
                //item click
            }
        )
    )

    init {
        adapter.submitList(Features.prefKeyList)
    }
}