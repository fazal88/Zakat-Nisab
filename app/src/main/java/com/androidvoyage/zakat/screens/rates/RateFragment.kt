package com.androidvoyage.zakat.screens.rates

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.androidvoyage.zakat.databinding.RateFragmentBinding
import com.androidvoyage.zakat.model.Features
import com.androidvoyage.zakat.pref.SharedPreferencesManager
import com.androidvoyage.zakat.screens.main.MainActivity
import com.androidvoyage.zakat.util.Utils
import com.androidvoyage.zakat.util.onClickWithAnimation

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
            save()
        }
        binding.ivBack.onClickWithAnimation { requireActivity().onBackPressed() }

        viewModel.rate24.observe(viewLifecycleOwner){
            it?.let{
                if(it.isNotEmpty()){
                    viewModel.error24.value = ""
                }
            }
        }
        viewModel.rate22.observe(viewLifecycleOwner){
            it?.let{
                if(it.isNotEmpty()){
                    viewModel.error22.value = ""
                }
            }
        }
        viewModel.rate18.observe(viewLifecycleOwner){
            it?.let{
                if(it.isNotEmpty()){
                    viewModel.error18.value = ""
                }
            }
        }
        viewModel.rate14.observe(viewLifecycleOwner){
            it?.let{
                if(it.isNotEmpty()){
                    viewModel.error14.value = ""
                }
            }
        }
        viewModel.rate23.observe(viewLifecycleOwner){
            it?.let{
                if(it.isNotEmpty()){
                    viewModel.error23.value = ""
                }
            }
        }
        viewModel.rateSilver.observe(viewLifecycleOwner){
            it?.let{
                if(it.isNotEmpty()){
                    viewModel.errorSilver.value = ""
                }
            }
        }

    }

    @OptIn(ExperimentalFoundationApi::class)
    private fun save(){

        if(viewModel.rate24.value.isNullOrEmpty()){
            viewModel.error24.value = "Invalid"
            return
        }

        if(viewModel.rate22.value.isNullOrEmpty()){
            viewModel.error22.value = "Invalid"
            return
        }

        if(viewModel.rate18.value.isNullOrEmpty()){
            viewModel.error18.value = "Invalid"
            return
        }

        if(viewModel.rate14.value.isNullOrEmpty()){
            viewModel.error14.value = "Invalid"
            return
        }

        if(viewModel.rate23.value.isNullOrEmpty()){
            viewModel.error23.value = "Invalid"
            return
        }

        if(viewModel.rateSilver.value.isNullOrEmpty()){
            viewModel.errorSilver.value = "Invalid"
            return
        }

        SharedPreferencesManager.getInstance().setRate(Features.PREF_24_K,viewModel.rate24.value)
        SharedPreferencesManager.getInstance().setRate(Features.PREF_22_K,viewModel.rate22.value)
        SharedPreferencesManager.getInstance().setRate(Features.PREF_18_K,viewModel.rate18.value)
        SharedPreferencesManager.getInstance().setRate(Features.PREF_14_K,viewModel.rate14.value)
        SharedPreferencesManager.getInstance().setRate(Features.PREF_23_KDM,viewModel.rate23.value)
        SharedPreferencesManager.getInstance().setRate(Features.PREF_SILVER,viewModel.rateSilver.value)

        Utils.showToast(requireActivity(), "Saved!", true)
        (requireActivity() as MainActivity).ratesChanged()
        requireActivity().onBackPressed()
    }

}