package com.androidvoyage.zakat.screens.about

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.ExperimentalFoundationApi
import com.androidvoyage.zakat.screens.main.MainActivity
import com.androidvoyage.zakat.R
import com.androidvoyage.zakat.screens.faq.FaqFragment
import com.androidvoyage.zakat.screens.faq.FaqViewModel

class AboutFragment : Fragment() {

    companion object {
        fun newInstance() = FaqFragment()
    }

    private lateinit var viewModel: FaqViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.about_fragment, container, false)
    }

    @OptIn(ExperimentalFoundationApi::class)
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FaqViewModel::class.java)
        (requireActivity() as MainActivity).hideNavBottom(false)
    }

}