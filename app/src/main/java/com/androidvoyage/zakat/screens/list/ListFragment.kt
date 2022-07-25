package com.androidvoyage.zakat.screens.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.androidvoyage.zakat.databinding.ListFragmentBinding
import com.androidvoyage.zakat.model.Features
import com.androidvoyage.zakat.model.NisabItem
import com.androidvoyage.zakat.screens.main.MainActivity
import com.androidvoyage.zakat.util.onClickWithAnimation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@ExperimentalFoundationApi
class ListFragment : Fragment() {

    companion object {
        fun newInstance() = ListFragment()
    }

    private val viewModel: ListViewModel by lazy {
        ViewModelProvider(this)[ListViewModel::class.java]
    }
    private lateinit var binding: ListFragmentBinding
    private lateinit var args: ListFragmentArgs

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ListFragmentBinding.inflate(inflater, container, false)
        args = arguments?.let { ListFragmentArgs.fromBundle(it) }!!
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as MainActivity).hideNavBottom(isNavVisible = true, isFabVisible = true)
        binding.vm = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.rvNisab.adapter = viewModel.adapter
        args.nisabType.let {
            viewModel.setNisabType(it)
            viewModel.listNisab = if (it == Features.PREF_OVER_ALL)
                (requireActivity() as MainActivity).database.nisabDao().getNisabs()
            else
                (requireActivity() as MainActivity).database.nisabDao().getNisabs(args.nisabType)
        }
        viewModel.listNisab.observe(viewLifecycleOwner) {
            it?.let {
                viewModel.adapter.submitList(it)
            }
        }

        viewModel.editNisab.observe(viewLifecycleOwner) {
            it?.let {
                (requireActivity() as MainActivity).navController.navigate(ListFragmentDirections.actionListFragmentToEditFragment(it))
                viewModel.editNisab.postValue(null)
            }
        }

        viewModel.deleteNisab.observe(viewLifecycleOwner) {
            it?.let {
                CoroutineScope(Dispatchers.Default).launch {
                    (requireActivity() as MainActivity).database.nisabDao().deleteNisab(it)
                }
            }
        }

        binding.ivAdd.onClickWithAnimation {
            binding.root.findNavController()
                .navigate(ListFragmentDirections.actionListFragmentToEditFragment(NisabItem(type = args.nisabType)))
        }

        binding.ivBack.onClickWithAnimation {
            requireActivity().onBackPressed()
        }

    }

}