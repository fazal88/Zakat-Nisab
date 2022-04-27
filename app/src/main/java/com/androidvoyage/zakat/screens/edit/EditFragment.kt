package com.androidvoyage.zakat.screens.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.androidvoyage.zakat.databinding.EditFragmentBinding
import com.androidvoyage.zakat.model.Features
import com.androidvoyage.zakat.screens.main.MainActivity
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

    private val viewModel: EditViewModel by lazy {
        ViewModelProvider(this)[EditViewModel::class.java]
    }
    private lateinit var binding: EditFragmentBinding
    private lateinit var args: EditFragmentArgs

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = EditFragmentBinding.inflate(inflater, container, false)
        args = arguments?.let { EditFragmentArgs.fromBundle(it) }!!
        return binding.root
    }

    @OptIn(ExperimentalFoundationApi::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        (requireActivity() as MainActivity).hideNavBottom(false)

        binding.tvSpnType.onClickWithAnimation {
            val list = arrayListOf<String>()
            list.addAll(Features.prefTitleList)
            showListSelectionDialog(requireContext(), list, object : OnSelectListener {
                override fun onSelected(item: String) {
                    viewModel.setType(item)
                }
            })
        }
        binding.tvSpnKarat.onClickWithAnimation {
            val list = arrayListOf<String>()
            list.addAll(Features.prefKaratList)
            showListSelectionDialog(requireContext(), list, object : OnSelectListener {
                override fun onSelected(item: String) {
                    viewModel.setKarat(item)
                }
            })
        }
        binding.tvBtnSave.onClickWithAnimation {
            save()
            Utils.showToast(requireActivity(), "Saved!", true)
            requireActivity().onBackPressed()
        }
        binding.ivBack.onClickWithAnimation { requireActivity().onBackPressed() }
        viewModel.nisabItem.postValue(args.nisab)

        viewModel.nisabItem.observe(viewLifecycleOwner){
            it?.let {

            }
        }
    }

    @OptIn(ExperimentalFoundationApi::class)
    private fun save() {
        CoroutineScope(Dispatchers.Default).launch {
            val nisab = viewModel.getNisabWithEstimation()
            (requireActivity() as MainActivity).type = nisab.type
            if (args.isUpdate) {
                (requireActivity() as MainActivity).database.nisabDao().updateNisab(nisab)
            }else{
                (requireActivity() as MainActivity).database.nisabDao().insertNisab(nisab)
            }
        }
    }

}