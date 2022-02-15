package com.androidvoyage.zakat

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.androidvoyage.zakat.databinding.ActivityMainBinding

@ExperimentalFoundationApi
class MainActivity : AppCompatActivity() {

    val viewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    private lateinit var binding : ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.vm = viewModel
        binding.lifecycleOwner = this

        navController.addOnDestinationChangedListener { navController, destination, bundle ->
            when (destination.id) {
                R.id.splashFragment -> {
                    viewModel.isSplash.postValue(true)
                }
                else->{
                    viewModel.isSplash.postValue(false)
                }
            }
        }

    }

    val navController : NavController by lazy {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.mainNavHostFragment) as NavHostFragment
        navHostFragment.navController
    }
}