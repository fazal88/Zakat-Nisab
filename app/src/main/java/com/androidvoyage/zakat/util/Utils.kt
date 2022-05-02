package com.androidvoyage.zakat.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Handler
import android.os.Looper
import android.text.TextUtils
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.TextView
import android.widget.Toast
import com.androidvoyage.zakat.R
import java.io.ByteArrayOutputStream
import java.text.NumberFormat
import java.util.*
import kotlin.math.roundToInt
import kotlin.math.roundToLong

object Utils {

    private var mToast: Toast? = null

    fun getCurrencySymbol(): String {
        return "₹ "
    }

    fun getAmountWithCommas(amount: String?): String {
        if(amount.isNullOrEmpty()){
            return "0"
        }
        val temp = if(amount.contains('.')){
            val tempDouble = amount.toDouble()
            tempDouble.roundToLong()
        }else
            amount.toLong()

        return if (temp > 0) {
            NumberFormat.getNumberInstance(Locale.UK)
                .format(temp)
        } else {
            "0"
        }
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


fun bitmapToBase64(bitmap: Bitmap?, format: Int): String? {
    var encoded: String? = null
    if (bitmap != null) {
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
        val byteArray = byteArrayOutputStream.toByteArray()
        encoded = Base64.encodeToString(byteArray, format)
    }
    return encoded
}

fun base64ToBitmap(base64: String?, format: Int): Bitmap? {
    var decoded: Bitmap? = null
    if (base64 != null && base64.isNotEmpty()) {
        val byteArray = Base64.decode(base64, format)
        decoded = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
    }
    return decoded
}