package com.androidvoyage.zakat.util

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.TextView
import android.widget.Toast
import com.androidvoyage.zakat.R
import java.text.NumberFormat
import java.util.*
import kotlin.math.roundToInt

object Utils {

    private var mToast: Toast? = null

    fun getCurrencySymbol(): String {
        return "₹ "
    }

    fun getAmountWithCommas(amount: String): String {
        val myNumber = if (amount.isNotEmpty()) {
            NumberFormat.getNumberInstance(Locale.UK)
                .format(amount.toInt())
        }else{
            "0"
        }
        return myNumber
    }

    var scaleAnimation: Animation? = null

    fun setClickedAnimation(view: View, onClickedAnimationListener: OnClickedAnimationListener?) {

        val animation: Animation? = getScaleAnimation(view.context)
        view.startAnimation(animation)

        animation?.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {

            }

            override fun onAnimationEnd(animation: Animation) {
                onClickedAnimationListener?.onAnimationEnd()
            }

            override fun onAnimationRepeat(animation: Animation) {

            }
        })

    }

    fun getScaleAnimation(context: Context?): Animation? {

        if (scaleAnimation == null) {
            scaleAnimation = AnimationUtils.loadAnimation(context, R.anim.scale_up_down)
        }

        return scaleAnimation
    }

    fun roundOff(ratingFloat: Float, decimalPlaces: Int): Float {
        var mulDiv = 10F
        for(i in 2..decimalPlaces){
            mulDiv *= mulDiv
        }
        val x = (ratingFloat* mulDiv).roundToInt()
        val y : Float = x/mulDiv
        return y
    }

    fun roundOff(ratingFloat: Double, decimalPlaces: Int): Float {
        var mulDiv = 10F
        for(i in 2..decimalPlaces){
            mulDiv *= mulDiv
        }
        val x = (ratingFloat* mulDiv).roundToInt()
        val y : Float = x/mulDiv
        return y
    }
}

fun View.setOnClickAnimateListener(clickListener: View.OnClickListener) {
    this.setOnClickListener {
        val animation: Animation? = Utils.getScaleAnimation(this.context)
        this.startAnimation(animation)

        animation?.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {

            }

            override fun onAnimationEnd(animation: Animation) {
                clickListener.onClick(this@setOnClickAnimateListener)
            }

            override fun onAnimationRepeat(animation: Animation) {

            }
        })
    }

}

interface OnClickedAnimationListener {

    fun onAnimationEnd()
}