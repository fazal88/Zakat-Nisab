package com.androidvoyage.zakat.screens.splash

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.ExperimentalFoundationApi
import com.androidvoyage.zakat.MainActivity
import com.androidvoyage.zakat.R

@ExperimentalFoundationApi
class SplashFragment : Fragment() {


    companion object {
        fun newInstance() = SplashFragment()
        private const val SPLASH_DISPLAY_LENGTH = 3000L
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.splash_fragment, container, false)
    }

    override fun onResume() {
        super.onResume()
        Handler(Looper.myLooper()!!).postDelayed({
            gotoHome()
        }, SPLASH_DISPLAY_LENGTH)
    }

    private fun gotoHome() {
        if(requireActivity() is MainActivity){
            (requireActivity() as MainActivity).navController
                .navigate(SplashFragmentDirections.actionSplashFragmentToDashboardFragment())
        }
    }

}