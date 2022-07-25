package com.androidvoyage.zakat.util

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.Window
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidvoyage.zakat.R
import com.androidvoyage.zakat.databinding.DialogInfoBinding
import com.androidvoyage.zakat.databinding.DialogListImagesBinding
import com.androidvoyage.zakat.databinding.DialogListSpinnerBinding
import com.androidvoyage.zakat.databinding.DialogTwoButtonBinding
import com.androidvoyage.zakat.model.NisabItem


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

    binding.parent.setOnClickListener{ dialog.dismiss()}
    binding.rcvListArea.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL,false)
    binding.rcvListArea.adapter = SpinnerListAdapter(context, listArea, object : OnSelectListener{
        override fun onSelected(item: String) {
            onSelectListener.onSelected(item)
            dialog.dismiss()
        }
    })

}



fun showExitDialog(context: Context, onDismissListener: DialogInterface.OnClickListener) {

    showTwoButtonDialog(
        context,
        context.getString(R.string.exit),
        context.getString(R.string.you_want_to_exit),
        context.getString(R.string.str_no),
        context.getString(R.string.str_yes),
        onDismissListener
    )
}

fun showTwoButtonDialog(
    context: Context,
    title: String,
    msg: String,
    txtPrimary: String,
    txtSecondary: String,
    onDismissListener: DialogInterface.OnClickListener?
) {
    val dialog = Dialog(context)
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
    val binding: DialogTwoButtonBinding =
        DialogTwoButtonBinding.inflate(LayoutInflater.from(context))
    dialog.setContentView(binding.root)
    dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    dialog.window!!.setLayout(
        ViewGroup.LayoutParams.MATCH_PARENT,
        ViewGroup.LayoutParams.MATCH_PARENT
    )
    dialog.setCanceledOnTouchOutside(false)
    dialog.show()

    binding.tvTitle.text = title
    binding.tvMessage.text = msg
    binding.tvBtnSecondary.text = txtSecondary
    binding.tvBtnPrimary.text = txtPrimary

    binding.tvBtnPrimary.setOnClickListener {
        try {
            onDismissListener?.onClick(dialog, DialogInterface.BUTTON_POSITIVE)
            dialog.dismiss()
        } catch (e: NullPointerException) {
            LogUtils.e(TAG, e.localizedMessage)
        }
    }

    binding.tvBtnSecondary.setOnClickListener {
        try {
            onDismissListener?.onClick(dialog, DialogInterface.BUTTON_NEGATIVE)
            dialog.dismiss()
        } catch (e: NullPointerException) {
            LogUtils.e(TAG, e.localizedMessage)
        }
    }

}

fun showInfoDialog(
    context: Context,
    title: String,
    message: String,
) {
    showInfoDialog(context, title, message, null)
}

fun showImageDialog(
    context: Context,
    image:String?,
    item:NisabItem
){
    val dialog = Dialog(context)
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
    val binding: DialogListImagesBinding =
        DialogListImagesBinding.inflate(LayoutInflater.from(context))
    dialog.setContentView(binding.root)
    dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    dialog.window!!.setLayout(
        ViewGroup.LayoutParams.MATCH_PARENT,
        ViewGroup.LayoutParams.MATCH_PARENT
    )
    dialog.setCanceledOnTouchOutside(true)
    binding.vm = item
    dialog.show()
    binding.ivCancel.setOnClickListener {
        try {
            dialog.dismiss()
        } catch (e: NullPointerException) {
            LogUtils.e(TAG, e.localizedMessage)
        }
    }


}

fun showInfoDialog(
    context: Context?,
    title: String,
    message: String,
    onDismissListener: DialogInterface.OnClickListener?
) {
    if(context == null) {
        return
    }

    val dialog = Dialog(context)
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
    val binding: DialogInfoBinding =
        DialogInfoBinding.inflate(LayoutInflater.from(context))
    dialog.setContentView(binding.root)
    dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    dialog.window!!.setLayout(
        ViewGroup.LayoutParams.MATCH_PARENT,
        ViewGroup.LayoutParams.MATCH_PARENT
    )
    dialog.setCanceledOnTouchOutside(true)
    dialog.show()

    binding.tvMessage.text = message
    binding.tvTitle.text = title
    binding.tvBtnOk.setOnClickListener {
        try {
            dialog.dismiss()
            onDismissListener?.onClick(dialog, DialogInterface.BUTTON_POSITIVE)
        } catch (e: NullPointerException) {
            LogUtils.e(TAG, e.localizedMessage)
        }
    }

}