package com.androidvoyage.zakat.screens.rates

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.androidvoyage.zakat.databinding.EditFragmentBinding
import com.androidvoyage.zakat.databinding.RateFragmentBinding
import com.androidvoyage.zakat.model.Features
import com.androidvoyage.zakat.model.Features.PREF_GOLD_SILVER
import com.androidvoyage.zakat.model.NisabCategoryItem
import com.androidvoyage.zakat.screens.main.MainActivity
import com.androidvoyage.zakat.util.OnSelectListener
import com.androidvoyage.zakat.util.Utils
import com.androidvoyage.zakat.util.onClickWithAnimation
import com.androidvoyage.zakat.util.showListSelectionDialog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.math.roundToLong

class RateFragment : Fragment() {

    companion object {
        fun newInstance() = RateFragment()
    }

    private val viewModel: RateViewModel by lazy {
        ViewModelProvider(this)[RateViewModel::class.java]
    }
    private lateinit var binding: RateFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = RateFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    @OptIn(ExperimentalFoundationApi::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        (requireActivity() as MainActivity).hideNavBottom(false)

        binding.tvBtnSave.onClickWithAnimation {
            viewModel.save()
            Utils.showToast(requireActivity(), "Saved!", true)
            (requireActivity() as MainActivity).ratesChanged()
            requireActivity().onBackPressed()
        }
        binding.ivBack.onClickWithAnimation { requireActivity().onBackPressed() }

    }

}