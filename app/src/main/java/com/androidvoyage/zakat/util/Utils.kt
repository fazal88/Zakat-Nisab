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


    fun showToast(context: Context, message: String?, longDuration: Boolean) {
        if (TextUtils.isEmpty(message)) return
        val handler = Handler(Looper.getMainLooper())
        handler.post {
            val inflater = LayoutInflater.from(context)
            val layout = inflater.inflate(R.layout.toast_layout, null)
            val text = layout.findViewById<View>(R.id.text) as TextView
            text.text = message
            var toast = mToast
            if (toast == null) {
                toast = Toast(context.applicationContext)
                mToast = toast
            }
            toast.duration =
                if (longDuration) Toast.LENGTH_LONG else Toast.LENGTH_SHORT
            toast.view = layout
            toast.show()
        }
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