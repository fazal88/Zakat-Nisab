package com.androidvoyage.zakat

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.androidvoyage.zakat.databinding.ActivityMainBinding
import com.androidvoyage.zakat.model.NisabDatabase
import com.androidvoyage.zakat.screens.dashboard.DashboardFragmentDirections
import com.androidvoyage.zakat.util.onClickWithAnimation
import com.androidvoyage.zakat.util.visibleSlide
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings


@ExperimentalFoundationApi
class MainActivity : AppCompatActivity() {

    private val TAG: String = "MainActivity"
    val viewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    private lateinit var binding: ActivityMainBinding
    val database : NisabDatabase by lazy {
        NisabDatabase.getDatabase(this)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.vm = viewModel
        binding.lifecycleOwner = this

        binding.ivBtnDashboard.onClickWithAnimation {
            if (navController.currentDestination?.id != R.id.dashboardFragment) {
                onBackPressed()
            }
        }

        binding.ivBtnList.onClickWithAnimation {
            if (navController.currentDestination?.id != R.id.listFragment) {
                navController.navigate(DashboardFragmentDirections.actionDashboardFragmentToListFragment())
            }
        }

        binding.ivBtnAdd.onClickWithAnimation {
            if (navController.currentDestination?.id != R.id.editFragment) {
                navController.navigate(R.id.editFragment)
            }
        }

    }

    fun hideNavBottom(isNavVisible : Boolean, isFabVisible : Boolean = false){
        if (binding.llBottomNav.visibility != View.VISIBLE && isNavVisible
            || binding.llBottomNav.visibility == View.VISIBLE && !isNavVisible) {
            visibleSlide(binding.llBottomNav, isNavVisible)
        }
        if (binding.llFab.visibility != View.VISIBLE && isFabVisible
            || binding.llFab.visibility == View.VISIBLE && !isFabVisible) {
            visibleSlide(binding.llFab, isFabVisible)
        }
    }

    val navController: NavController by lazy {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.mainNavHostFragment) as NavHostFragment
        navHostFragment.navController
    }
}