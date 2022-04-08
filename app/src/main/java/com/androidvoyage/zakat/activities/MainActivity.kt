package com.androidvoyage.zakat.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.androidvoyage.zakat.R
import com.androidvoyage.zakat.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        gotoDefaultScreen()
    }

    private fun gotoDefaultScreen() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.mainNavHostFragment) as NavHostFragment
        navController = navHostFragment.navController
        val navGraph = navController.navInflater.inflate(R.navigation.navigation_entry)
        when {
            else -> {
                navGraph.setStartDestination(R.id.splashFragment)
            }
        }
        navController.graph = navGraph
    }


    fun setHomeTitle(isTitle: Boolean) {
        binding.incToolabr.ivBack.visibility = if (isTitle) {
            binding.incToolabr.ivBack.setOnClickListener {
                onBackPressed()
            }
            View.VISIBLE
        } else View.GONE
        binding.incToolabr.tvTitle.visibility = if (isTitle) View.VISIBLE else View.GONE
        binding.incToolabr.ivMenu.visibility = if (isTitle) View.VISIBLE else View.GONE

    }

    fun setScreenTitle(title: String?) {
        //sent null to make title dissappear
        val isTitle = title.isNullOrEmpty()
        setHomeTitle(isTitle = isTitle)
        title?.let {
            binding.incToolabr.tvTitle.text = it
        }
    }
}