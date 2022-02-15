package com.androidvoyage.zakat.util

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.Window
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidvoyage.zakat.databinding.DialogListSpinnerBinding


fun showListSelectionDialog(context: Context, listArea : ArrayList<String>?, onSelectListener: OnSelectListener) {
    val dialog = Dialog(context)
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
    val binding = DialogListSpinnerBinding.inflate(LayoutInflater.from(context))
    dialog.setContentView(binding.root)
    dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    dialog.window!!.setLayout(
        ViewGroup.LayoutParams.MATCH_PARENT,
        ViewGroup.LayoutParams.MATCH_PARENT
    )
    dialog.setCanceledOnTouchOutside(false)
    dialog.show()

    binding.rcvListArea.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL,false)
    binding.rcvListArea.adapter = SpinnerListAdapter(context, listArea, object : OnSelectListener{
        override fun onSelected(item: String) {
            onSelectListener.onSelected(item)
            dialog.dismiss()
        }
    })

}