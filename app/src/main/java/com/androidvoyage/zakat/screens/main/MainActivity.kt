package com.androidvoyage.zakat.screens.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.androidvoyage.zakat.R
import com.androidvoyage.zakat.databinding.ActivityMainBinding
import com.androidvoyage.zakat.model.Features
import com.androidvoyage.zakat.model.NisabCategoryItem
import com.androidvoyage.zakat.model.NisabDatabase
import com.androidvoyage.zakat.pref.SharedPreferencesManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.math.roundToLong


@ExperimentalFoundationApi
class MainActivity : AppCompatActivity() {

    var type: String = Features.PREF_OVER_ALL
    private val TAG: String = "MainActivity"
    val viewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    private lateinit var binding: ActivityMainBinding
    val database: NisabDatabase by lazy {
        NisabDatabase.getDatabase(this)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.vm = viewModel
        binding.lifecycleOwner = this

        database.nisabDao().getNisabs()
            .observe(
                this
            ) {
                it?.let {
                    var totalOverAll = 0.0
                    var total = 0.0
                    var countOverAll = 0
                    var count = 0
                    for (i in it) {
                        totalOverAll += i.estimatedValue.toDouble()
                        countOverAll++
                        if (type == i.type) {
                            total += i.estimatedValue.toDouble()
                            count++
                        }
                    }
                    CoroutineScope(Dispatchers.Default).launch {
                        database.nisabDao().updateNisabCategory(
                            NisabCategoryItem(
                                type,
                                total,
                                count.toString(),
                                System.currentTimeMillis()
                            )
                        )
                        database.nisabDao().updateNisabCategory(
                            NisabCategoryItem(
                                Features.PREF_OVER_ALL,
                                totalOverAll,
                                countOverAll.toString(),
                                System.currentTimeMillis()
                            )
                        )
                    }
                }
            }

    }

    fun hideNavBottom(isNavVisible: Boolean, isFabVisible: Boolean = false) {
        /*if (binding.llBottomNav.visibility != View.VISIBLE && isNavVisible
            || binding.llBottomNav.visibility == View.VISIBLE && !isNavVisible) {
            visibleSlide(binding.llBottomNav, isNavVisible)
        }
        if (binding.llFab.visibility != View.VISIBLE && isFabVisible
            || binding.llFab.visibility == View.VISIBLE && !isFabVisible) {
            visibleSlide(binding.llFab, isFabVisible)
        }*/
    }

    fun ratesChanged() {
        type = Features.PREF_GOLD_SILVER
        val list = database.nisabDao().getNisabs(Features.PREF_GOLD_SILVER)
        list.observe(this) {
            it?.let {
                for (metalItem in it) {
                    val grams = metalItem.weight
                    val rate = SharedPreferencesManager.getInstance().getRate(metalItem.purity)
                    val estimatedValue = grams.toFloat() * rate.toFloat()/10
                    val roundOffEstimatedValue = estimatedValue.roundToLong()
                    metalItem.estimatedValue = roundOffEstimatedValue.toString()
                    CoroutineScope(Dispatchers.Default)
                        .launch {
                            database.nisabDao().updateNisab(metalItem)
                        }
                }
                list.removeObservers(this)
            }
        }
    }

    val navController: NavController by lazy {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.mainNavHostFragment) as NavHostFragment
        navHostFragment.navController
    }
}