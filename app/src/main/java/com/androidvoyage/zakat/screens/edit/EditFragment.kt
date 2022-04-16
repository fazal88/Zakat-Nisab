package com.androidvoyage.zakat.screens.edit

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.ExperimentalFoundationApi
import com.androidvoyage.zakat.screens.main.MainActivity
import com.androidvoyage.zakat.databinding.EditFragmentBinding
import com.androidvoyage.zakat.feature_nisab.presentation.util.Features
import com.androidvoyage.zakat.feature_nisab.presentation.util.Features.PREF_GOLD_SILVER
import com.androidvoyage.zakat.util.OnSelectListener
import com.androidvoyage.zakat.util.Utils
import com.androidvoyage.zakat.util.onClickWithAnimation
import com.androidvoyage.zakat.util.showListSelectionDialog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditFragment : Fragment() {

    companion object {
        fun newInstance() = EditFragment()
    }

    private val viewModel: EditViewModel  by lazy {
        ViewModelProvider(this)[EditViewModel::class.java]
    }
    private lateinit var binding: EditFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = EditFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }

    @OptIn(ExperimentalFoundationApi::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        (requireActivity() as MainActivity).hideNavBottom(false)


        binding.tvSpnType.setOnClickListener {
            val list = arrayListOf<String>()
            list.addAll(Features.prefTitleList)
            showListSelectionDialog(requireContext(),list ,object : OnSelectListener{
                override fun onSelected(item: String) {
                    binding.tvSpnType.text = item
                    viewModel.isMetal.postValue(item == PREF_GOLD_SILVER)
                    viewModel.setType(item)
                }
            })
        }


        binding.tvSpnKarat.setOnClickListener {
            val list = arrayListOf<String>()
            list.addAll(Features.prefKaratList)
            showListSelectionDialog(requireContext(),list ,object : OnSelectListener{
                override fun onSelected(item: String) {
                    binding.tvSpnKarat.text = item
                    viewModel.setKarat(item)
                }
            })
        }

        binding.tvBtnSave.onClickWithAnimation {
            save()
            Utils.showToast(requireActivity(),"Saved!",true)
            requireActivity().onBackPressed()
        }
    }

    @OptIn(ExperimentalFoundationApi::class)
    private fun save() {
        CoroutineScope(Dispatchers.Default).launch {
            (requireActivity() as MainActivity).database.nisabDao().insertNisab(viewModel.nisanItem.value!!)
        }
    }

}