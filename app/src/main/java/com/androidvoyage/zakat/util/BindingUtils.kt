package com.androidvoyage.zakat.util

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Build
import android.util.Base64
import android.view.View
import android.view.ViewAnimationUtils
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.Fade
import androidx.transition.Slide
import androidx.transition.Transition
import androidx.transition.TransitionManager
import com.androidvoyage.zakat.R
import com.androidvoyage.zakat.model.Features
import com.androidvoyage.zakat.model.NisabItem
import com.androidvoyage.zakat.pref.SharedPreferencesManager
import com.androidvoyage.zakat.screens.edit.NisabImagesAdapter
import com.bumptech.glide.Glide
import java.util.*
import kotlin.math.max
import kotlin.math.roundToLong

val TAG: String = "BindingUtils"

/**
 * Created by Fazal on 15/02/22.
 * Copyright (c) 2022 Fazal. All rights reserved.
 */

@BindingAdapter("onClickWithAnimation")
fun View.onClickWithAnimation(clickFunction: () -> Unit) {
    this.setOnClickListener {
        Utils.setClickedAnimation(this, object : OnClickedAnimationListener {
            override fun onAnimationEnd() {
                clickFunction()
            }
        })
    }

}

@BindingAdapter("setListAdapter")
fun RecyclerView.setListAdapter(item : NisabItem?){
    item?.let { nisab ->
        val adapter = NisabImagesAdapter(NisabImagesAdapter.NisabClickListener { image ->
            showImageDialog(this.context, image, nisab.listImages)
        })
        this.adapter = adapter
        adapter.submitList(nisab.listImages)
    }
}


@BindingAdapter("setImageBitmap")
fun ImageView.setImageBitmap(item: String?) {
    try {
        item?.let {
            Glide.with(this.context)
                .load(base64ToBitmap(item, Base64.NO_WRAP))
                .centerCrop()
                .circleCrop()
                .placeholder(R.drawable.ic_nisab)
                .into(this)
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}


@BindingAdapter("setVisibility")
fun View.setVisibility(isVisible: Boolean?) {
    isVisible?.let {
        visibility = if (isVisible) View.VISIBLE else View.GONE
    }
}


@BindingAdapter("setInVisible")
fun View.setInVisible(isVisible: Boolean?) {
    isVisible?.let {
        visibility = if (isVisible) View.VISIBLE else View.INVISIBLE
    }
}


@BindingAdapter("visibleWithAnimation")
fun visibleWithAnimation(view: View, isVisible: Boolean) {

    if (view.parent is ViewGroup) {
        val transition: Transition = Fade()
        transition.duration = 600
        transition.addTarget(view)
        TransitionManager.beginDelayedTransition(view.parent as ViewGroup, transition)
    }

    view.visibility = if (isVisible) View.VISIBLE else View.GONE
}


@BindingAdapter("visibleIfMetal")
fun visibleWithAnimation(view: View, type: String) {

    val isVisible = type==Features.PREF_GOLD_SILVER
    if (view.parent is ViewGroup) {
        val transition: Transition = Fade()
        transition.duration = 600
        transition.addTarget(view)
        TransitionManager.beginDelayedTransition(view.parent as ViewGroup, transition)
    }

    view.visibility = if (isVisible) View.VISIBLE else View.GONE
}




@BindingAdapter("visibleSlide")
fun visibleSlide(view: View, isVisible: Boolean) {

    if (view.parent is ViewGroup) {
        val transition: Transition = Slide()
        transition.duration = 600
        transition.addTarget(view)
        TransitionManager.beginDelayedTransition(view.parent as ViewGroup, transition)
    }

    view.visibility = if (isVisible) View.VISIBLE else View.GONE
}

@BindingAdapter("inVisibleWithAnimation")
fun inVisibleWithAnimation(view: View, isVisible: Boolean) {

    if (view.parent is ViewGroup) {
        val transition: Transition = Fade()
        transition.duration = 600
        transition.addTarget(view)
        TransitionManager.beginDelayedTransition(view.parent as ViewGroup, transition)
    }

    view.visibility = if (isVisible) View.VISIBLE else View.INVISIBLE
}


@BindingAdapter("setFirtsCap")
fun TextView.setFirstCap(item: String?) {
    item.let {
        try {
            text = item.toString()
                .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
        } catch (e: Exception) {
            LogUtils.e(TAG, e.localizedMessage)
        }
    }
}

fun String.firstCap() = this.replaceFirstChar { it.uppercase() }


@BindingAdapter("trimText")
fun TextView.trimText(trimeText: String?) {
    try {
        this.text = trimeText?.trim()
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun revealAnimation(view: View, pivotView: View, duration: Long) {
    view.visibility = View.VISIBLE
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        val radius = max(view.width, view.height) * 2.0f
        val cx = (pivotView.left + pivotView.right) / 2
        val cy = (pivotView.top + pivotView.bottom) / 2
        val reveal = ViewAnimationUtils.createCircularReveal(view, cx, cy, 0f, radius)
        reveal.duration = duration
        reveal.start()
    }
}

fun revealBackAnimation(view: View, pivotView: View, duration: Long) {
    if (view.visibility == View.VISIBLE && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        val radius = max(view.width, view.height) * 2.0f
        val cx = (pivotView.left + pivotView.right) / 2
        val cy = (pivotView.top + pivotView.bottom) / 2
        val reveal = ViewAnimationUtils.createCircularReveal(view, cx, cy, radius, 0f)
        reveal.duration = duration
        reveal.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                view.visibility = View.INVISIBLE
            }
        })
        reveal.start()
    } else {
        view.visibility = View.INVISIBLE
    }
}


@BindingAdapter("htmlText")
fun TextView.htmlText(htmlText: String?) {
    htmlText?.let {
        text = HtmlCompat.fromHtml(htmlText, HtmlCompat.FROM_HTML_MODE_LEGACY).toString()
    }
}

@BindingAdapter("setSelected")
fun TextView.setSelected(item: Boolean?) {
    item?.let {
        isSelected = item
    }
}

@BindingAdapter("setSelected")
fun TextView.setSelected(item: String?) {
    item?.let {
        isSelected = text.toString() == item
    }
}

@BindingAdapter("setDate")
fun TextView.setDate(item: String?) {
    item?.let {
        text = if (item.isNotEmpty()) {
            DateTimeUtility.getFormattedDateTime(
                item,
                DateTimeUtility.YYYY_MM_DD,
                DateTimeUtility.D_MMM_YYYY,
                false
            )
        } else {
            ""
        }
    }
}

@BindingAdapter("setDate")
fun TextView.setDate(item: Long?) {
    item?.let {
        text = "Last update : "+if (item > 0) {
            DateTimeUtility.convertDateToString(item, DateTimeUtility.D_MMM_YYYY)
        } else {
            ""
        }
    }
}


@BindingAdapter(value = ["cardNo", "index", "isHidden"], requireAll = false)
fun TextView.cardNumber(cardNo: String?, index: Int, isHidden: Boolean) {
    text = if (isHidden && (index != 3 || cardNo == null || cardNo.isEmpty())) {
        context.getString(R.string.str_card_mask)
    } else {
        try {
            val array = cardNo?.split(" ")
            array?.get(index)!!
        } catch (e: Exception) {
            e.printStackTrace()
            ""
        }
    }
}


@BindingAdapter(value = ["startDate", "endDate"], requireAll = true)
fun TextView.setFilterDate(startDate: String, endDate: String) {
    if (startDate.isNotEmpty() && endDate.isNotEmpty()) {
        visibility = View.VISIBLE
        val start = DateTimeUtility.getFormattedDateTime(
            startDate,
            DateTimeUtility.YYYY_MM_DD,
            DateTimeUtility.d_mmm_yy,
            false
        )
        val end = DateTimeUtility.getFormattedDateTime(
            endDate,
            DateTimeUtility.YYYY_MM_DD,
            DateTimeUtility.d_mmm_yy,
            false
        )
        text = "$start - $end"
    } else {
        visibility = View.GONE
    }
}


@BindingAdapter("setImageUrl")
fun ImageView.setImageUrl(item: String?) {
    try {
        item?.let {
            Glide.with(this.context)
                .load(item)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(this)
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

@BindingAdapter("setImageRes")
fun ImageView.setImageUrl(item: Int?) {
    try {
        item?.let {
            Glide.with(this.context)
                .load(item)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(this)
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

@BindingAdapter("setZakatVisibile")
fun TextView.setZakatVisibile(type: String?) {
    type?.let {
        visibility = if(type == Features.PREF_OVER_ALL) View.VISIBLE else View.GONE
    }
}

@BindingAdapter("setAmountWithRupee")
fun TextView.setAmountWithRupee(type: String?) {
    type?.let {
        val amountString = "₹ " + Utils.getAmountWithCommas(it)
        text = amountString
    }
}

@BindingAdapter("setAmountWithRupee")
fun TextView.setAmountWithRupee(type: Double?) {
    type?.let {
        val amountString = "₹ " + Utils.getAmountWithCommas(it.roundToLong().toString())
        text = amountString
    }
}

@BindingAdapter("setColorFromKey")
fun View.setColorFromKey(type: String?) {
    type?.let {
        backgroundTintList = ContextCompat.getColorStateList(context, Features.getColorRes(type))
    }
}

@BindingAdapter("setTextColorFromKey")
fun TextView.setTextColorFromKey(type: String?) {
    type?.let {
        setTextColor(ContextCompat.getColorStateList(context, Features.getColorRes(type)))
    }
}

@BindingAdapter("setPuritySection")
fun View.setPuritySection(type: String?) {
    type?.let {
        visibility = if (type == Features.PREF_GOLD_SILVER) View.VISIBLE else View.GONE
    }
}

@BindingAdapter("setIconFromKey")
fun ImageView.setIconFromKey(type: String?) {
    type?.let {
        this.setImageResource(Features.getIcon(type))
    }
}

@BindingAdapter("setEstimatedValue")
fun TextView.setEstimatedValue(vm: NisabItem?) {
    vm?.let {
        text = "₹ ${Utils.getAmountWithCommas(vm.price)}"
    }
}

@BindingAdapter("setEstimatedZakat")
fun TextView.setEstimatedZakat(totalValue : Double?){
    text = "₹ ${totalValue?.times(0.025)?.roundToLong()}"
}

@BindingAdapter("set24KRate")
fun TextView.set24KRate(i : String){
    var rate = SharedPreferencesManager.getInstance().getRate(Features.PREF_24_K)+""+i
    if(rate.isEmpty() || rate.isBlank()){
        rate = "NA"
    }
    text = rate
}

@BindingAdapter("set22KRate")
fun TextView.set22KRate(i : String){
    var rate = SharedPreferencesManager.getInstance().getRate(Features.PREF_22_K)+""+i
    if(rate.isEmpty() || rate.isBlank()){
        rate = "NA"
    }
    text = rate
}

@BindingAdapter("set18KRate")
fun TextView.set18KRate(i : String){
    var rate = SharedPreferencesManager.getInstance().getRate(Features.PREF_18_K)+""+i
    if(rate.isEmpty() || rate.isBlank()){
        rate = "NA"
    }
    text = rate
}

@BindingAdapter("set14KRate")
fun TextView.set14KRate(i : String){
    var rate = SharedPreferencesManager.getInstance().getRate(Features.PREF_14_K)+""+i
    if(rate.isEmpty() || rate.isBlank()){
        rate = "NA"
    }
    text = rate
}

@BindingAdapter("set23KRate")
fun TextView.set23KRate(i : String){
    var rate = SharedPreferencesManager.getInstance().getRate(Features.PREF_23_KDM)+""+i
    if(rate.isEmpty() || rate.isBlank()){
        rate = "NA"
    }
    text = rate
}

@BindingAdapter("setSilverRate")
fun TextView.setSilverRate(i : String){
    var rate = SharedPreferencesManager.getInstance().getRate(Features.PREF_SILVER)+""+i
    if(rate.isEmpty() || rate.isBlank()){
        rate = "NA"
    }
    text = rate
}

@BindingAdapter("setEstimatedZakat")
fun TextView.setEstimatedZakat(vm: NisabItem?) {
    vm?.let {
        val isMetail = it.type == Features.PREF_GOLD_SILVER

        text = if (isMetail) {
            val grams = it.weight
            val rate = SharedPreferencesManager.getInstance().getRate(vm.purity)
            val estimatedValue = grams.toFloat() * rate.toFloat() * 0.025
            val roundOffEstimatedValue = Utils.roundOff(estimatedValue, 2)
            "₹ $roundOffEstimatedValue"
        } else {
            "₹ ${vm.price}"
        }
    }
}