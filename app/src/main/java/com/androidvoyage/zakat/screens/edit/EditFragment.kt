package com.androidvoyage.zakat.screens.edit

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.androidvoyage.zakat.databinding.EditFragmentBinding
import com.androidvoyage.zakat.model.Features
import com.androidvoyage.zakat.screens.main.MainActivity
import com.androidvoyage.zakat.util.*
import com.github.dhaval2404.imagepicker.ImagePicker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.internal.notify

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
        args.nisab.type = if (args.nisab.type == Features.PREF_OVER_ALL) "" else args.nisab.type
        viewModel.nisabItem.postValue(args.nisab.copy())

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
        binding.flAdd.onClickWithAnimation {
            ImagePicker.with(this)
                .compress(1024) //Final image size will be less than 1 MB(Optional)
                .maxResultSize(
                    1080,
                    1080
                )  //Final image resolution will be less than 1080 x 1080(Optional)
                .createIntent { intent ->
                    startForProfileImageResult.launch(intent)
                }
        }
    }

    @OptIn(ExperimentalFoundationApi::class)
    private fun save() {
        if (viewModel.nisabItem.value?.type?.isEmpty() == true) {
            viewModel.errorType.value = "Please select"
            return
        }
        if (viewModel.nisabItem.value?.type == Features.PREF_GOLD_SILVER && viewModel.nisabItem.value?.purity?.isEmpty() == true) {
            viewModel.errorPurity.value = "Please select"
            return
        }
        if (viewModel.nisabItem.value?.type == Features.PREF_GOLD_SILVER && viewModel.nisabItem.value?.weight?.isEmpty() == true) {
            viewModel.errorGram.value = "Invalid"
            return
        }
        if (viewModel.nisabItem.value?.name?.isEmpty() == true) {
            viewModel.errorName.value = "Cannot be empty"
            return
        }
        if (viewModel.nisabItem.value?.type != Features.PREF_GOLD_SILVER && viewModel.nisabItem.value?.price?.isEmpty() == true) {
            viewModel.errorCost.value = "Cannot be empty"
            return
        }
        val nisab = viewModel.getNisabWithEstimation()
        CoroutineScope(Dispatchers.Default).launch {
            (requireActivity() as MainActivity).type = nisab.type
            (requireActivity() as MainActivity).database.nisabDao().insertNisab(nisab)
        }
        Utils.showToast(requireActivity(), "Saved!", true)
        if (nisab.type == args.nisab.type || args.nisab.type.isEmpty()) {
            requireActivity().onBackPressed()
        } else {
            (requireActivity() as MainActivity).navController.navigate(
                EditFragmentDirections.actionEditFragmentToListFragment(
                    nisab.type
                )
            )
        }
    }

    private val startForProfileImageResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            val resultCode = result.resultCode
            val data = result.data

            if (resultCode == Activity.RESULT_OK) {
                //Image Uri will not be null for RESULT_OK
                val fileUri = data?.data!!

                /*mProfileUri = fileUri
                imgProfile.setImageURI(fileUri)*/
                LogUtils.d("Image Picker URI", fileUri.toString())
                val list = ArrayList<String>()
                val nisabList = viewModel.nisabItem.value?.listImages
                if (nisabList?.isNotEmpty()==true) {
                    list.addAll(nisabList)
                }
                list.add(fileUri.toString())
                viewModel.nisabItem.value?.listImages = list
                viewModel.notifyModel()
            } else if (resultCode == ImagePicker.RESULT_ERROR) {
                Toast.makeText(requireContext(), ImagePicker.getError(data), Toast.LENGTH_SHORT)
                    .show()
            } else {
                Toast.makeText(requireContext(), "Task Cancelled", Toast.LENGTH_SHORT).show()
            }
        }

}