package com.androidvoyage.zakat.screens.dashboard

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.androidvoyage.zakat.screens.main.MainActivity
import com.androidvoyage.zakat.R
import com.androidvoyage.zakat.databinding.DashboardFragmentBinding

@ExperimentalFoundationApi
class DashboardFragment : Fragment() {

    companion object {
        fun newInstance() = DashboardFragment()
    }

    private val viewModel by lazy {
        ViewModelProvider(this)[DashboardViewModel::class.java]
    }
    private lateinit var binding: DashboardFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DashboardFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as MainActivity).hideNavBottom(isNavVisible = true, isFabVisible = false)
        binding.vm = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.rvDashboard.adapter = viewModel.adapter

        viewModel.isOptionClick.observe(viewLifecycleOwner, Observer {
            it?.let {
                if(it){
                    showPopupMenu(binding.ivOption)
                    viewModel.isOptionClick.postValue(false)
                }
            }
        })

        viewModel.clickedFeature.observe(viewLifecycleOwner, Observer {
            it?.let {
                viewModel.clickedFeature.postValue(null)
                (requireActivity() as MainActivity).navController.navigate(DashboardFragmentDirections.actionDashboardFragmentToListFragment())
            }
        })

        viewModel.clickedAddFeature.observe(viewLifecycleOwner, Observer {
            it?.let {
                viewModel.clickedAddFeature.postValue(null)
                (requireActivity() as MainActivity).navController.navigate(DashboardFragmentDirections.actionDashboardFragmentToEditFragment())
            }
        })
    }

    private fun showPopupMenu(infoView: View) {
        val inflater = requireActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.layout_popup_menu, null)
        val filterPopup = PopupWindow(
            view,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        filterPopup.isOutsideTouchable = true
        filterPopup.isFocusable = true
        filterPopup.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        filterPopup.showAsDropDown(
            infoView,
            0,
            -infoView.height
        )
    }
}

