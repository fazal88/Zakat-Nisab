package com.androidvoyage.zakat.screens.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.core.widget.doOnTextChanged
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
        }
        binding.ivBack.onClickWithAnimation { requireActivity().onBackPressed() }
        viewModel.nisabItem.postValue(args.nisab)

        binding.etName.doOnTextChanged { text, start, before, count ->
            if (count > 0) {
                viewModel.errorName.value = ""
            }
        }
        binding.etCost.doOnTextChanged { text, start, before, count ->
            if (count > 0) {
                viewModel.errorCost.value = ""
            }
        }
        binding.etWeight.doOnTextChanged { text, start, before, count ->
            if (count > 0) {
                viewModel.errorGram.value = ""
            }
        }
        binding.tvSpnType.doOnTextChanged { text, start, before, count ->
            if (count > 0) {
                viewModel.errorType.value = ""
            }
        }
        binding.tvSpnKarat.doOnTextChanged { text, start, before, count ->
            if (count > 0) {
                viewModel.errorPurity.value = ""
            }
        }
    }

    @OptIn(ExperimentalFoundationApi::class)
    private fun save() {
        if(viewModel.nisabItem.value?.type?.isEmpty() == true){
            viewModel.errorType.value = "Please select"
            return
        }
        if (viewModel.nisabItem.value?.type == Features.PREF_GOLD_SILVER && viewModel.nisabItem.value?.purity?.isEmpty() == true) {
            viewModel.errorPurity.value = "Please select"
            return
        }
        if(viewModel.nisabItem.value?.type == Features.PREF_GOLD_SILVER &&viewModel.nisabItem.value?.weight?.isEmpty() == true){
            viewModel.errorGram.value = "Invalid"
            return
        }
        if(viewModel.nisabItem.value?.name?.isEmpty() == true){
            viewModel.errorName.value = "Cannot be empty"
            return
        }
        if(viewModel.nisabItem.value?.type != Features.PREF_GOLD_SILVER && viewModel.nisabItem.value?.price?.isEmpty() == true){
            viewModel.errorCost.value = "Cannot be empty"
            return
        }
        val nisab = viewModel.getNisabWithEstimation()
        CoroutineScope(Dispatchers.Default).launch {
            (requireActivity() as MainActivity).type = nisab.type
            (requireActivity() as MainActivity).database.nisabDao().insertNisab(nisab)
        }
        Utils.showToast(requireActivity(), "Saved!", true)
        (requireActivity() as MainActivity).navController.navigate(EditFragmentDirections.actionEditFragmentToListFragment(nisab.type))
    }

}