package com.androidvoyage.zakat.util

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Build
import android.view.View
import android.view.ViewAnimationUtils
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter
import androidx.transition.Fade
import androidx.transition.Transition
import androidx.transition.TransitionManager
import com.androidvoyage.zakat.R
import com.androidvoyage.zakat.compose.Feature
import com.androidvoyage.zakat.model.Features
import com.androidvoyage.zakat.pref.SharedPreferencesManager
import com.bumptech.glide.Glide
import java.util.*
import kotlin.math.max

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
                .placeholder(R.drawable.ic_launcher_background)
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
                .placeholder(R.drawable.ic_launcher_background)
                .into(this)
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

@BindingAdapter("setAmountFromPref")
fun TextView.setAmountFromPref(key: String?) {
    key?.let {
        val amount = SharedPreferencesManager.getInstance().getValue(key)
        val amountString = Utils.getCurrencySymbol()+ Utils.getAmountWithCommas(amount)
        text = amountString
    }
}

@BindingAdapter("setColorFromKey")
fun View.setColorFromKey(key: String?) {
    key?.let {
        backgroundTintList = ContextCompat.getColorStateList(context, Features.getColor(key))
    }
}

@BindingAdapter("setIconFromKey")
fun ImageView.setIconFromKey(key: String?) {
    key?.let {
        this.setImageResource(Features.getIcon(key))
    }
}

@BindingAdapter("setTitleFromKey")
fun TextView.setTitleFromKey(key: String?) {
    key?.let {
        text = (Features.getTitle(key))
    }
}