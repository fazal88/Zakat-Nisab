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
import com.androidvoyage.zakat.screens.main.MainActivity
import com.androidvoyage.zakat.util.onClickWithAnimation

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
            viewModel.listNisab = if (it == Features.PREF_OVER_ALL)
                (requireActivity() as MainActivity).database.nisabDao()
                    .getNisabs()
            else
                (requireActivity() as MainActivity).database.nisabDao()
                    .getNisabs(args.nisabType)
        }
        viewModel.listNisab.observe(viewLifecycleOwner, Observer {
            it?.let {
                viewModel.adapter.submitList(it)
            }
        })

        binding.ivAdd.onClickWithAnimation {
            binding.root.findNavController()
                .navigate(ListFragmentDirections.actionListFragmentToEditFragment(args.nisabType))
        }

        binding.ivBack.onClickWithAnimation {
            requireActivity().onBackPressed()
        }

    }

}