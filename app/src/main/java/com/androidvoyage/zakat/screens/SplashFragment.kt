package com.androidvoyage.zakat.screens

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.androidvoyage.zakat.activities.MainActivity
import com.androidvoyage.zakat.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {


    private val TAG: String = "SplashFragment"
    private lateinit var binding: FragmentSplashBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        initialiseViews()
        return binding.root
    }

    private fun initialiseViews() {
        (requireActivity() as MainActivity).setHomeTitle(false)
        (requireActivity() as MainActivity).setScreenTitle(null)
        Handler(Looper.getMainLooper()).postDelayed({
            binding.root.findNavController()
                .navigate(SplashFragmentDirections.actionFragmentOnBoardToFragmentEnterPhone())
        }, 5000)
    }

}