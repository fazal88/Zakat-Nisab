package com.androidvoyage.zakat

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.androidvoyage.zakat.databinding.ActivityMainBinding
import com.androidvoyage.zakat.util.onClickWithAnimation

@ExperimentalFoundationApi
class MainActivity : AppCompatActivity() {

    val viewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.vm = viewModel
        binding.lifecycleOwner = this

        navController.addOnDestinationChangedListener { navController, destination, bundle ->
            when (destination.id) {
                R.id.splashFragment -> {
                    viewModel.isSplash.postValue(true)
                    viewModel.isList.postValue(false)
                }
                R.id.listFragment -> {
                    viewModel.isList.postValue(true)
                    viewModel.isSplash.postValue(false)
                }
                else -> {
                    viewModel.isSplash.postValue(false)
                    viewModel.isList.postValue(false)
                }
            }
        }


        binding.ivBtnDashboard.onClickWithAnimation {
            if (navController.currentDestination?.id != R.id.dashboardFragment) {
                navController.navigate(R.id.dashboardFragment)
            }
        }

        binding.ivBtnList.onClickWithAnimation {
            if (navController.currentDestination?.id != R.id.listFragment) {
                navController.navigate(R.id.listFragment)
            }
        }

        binding.ivBtnAdd.onClickWithAnimation {
            if (navController.currentDestination?.id != R.id.editFragment) {
                navController.navigate(R.id.editFragment)
            }
        }

    }

    val navController: NavController by lazy {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.mainNavHostFragment) as NavHostFragment
        navHostFragment.navController
    }
}