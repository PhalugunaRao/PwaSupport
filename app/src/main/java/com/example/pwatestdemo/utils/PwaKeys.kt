package com.example.pwatestdemo.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.View
import com.example.pwatestdemo.MyApplication
import com.razorpay.BuildConfig

object PwaKeys {
    const val JS_INTERFACE = "ekincareAndroidInterface"
    const val GRANTED = "granted"
    const val PROMPT = "prompt"
    const val STORAGE = "storage"
    const val CAMERA = "camera"
    const val LOCATION = "LOCATION"
    const val FAILED = "failed"
    const val DENIED = "denied"
    const val GPS_REQUEST: Int = 1001
    const val PAYMENT_KEY: String = "rzp_test_hh1OMcKpr2K5c0"
    const val WALLET_LOGO: String =
        "https://res.cloudinary.com/ekincare/image/upload/v1541673563/hr_dashboard_login_page/ekincare-logo-new_200_200.png"

    val VERSION = Build.VERSION.SDK_INT

}

fun getStringOutside(stringResId: Int): String? {
    return MyApplication.instance?.resources?.getString(stringResId)
}

fun permissionBundle(type: String): Bundle {
    val permissionBundle = Bundle()
    permissionBundle.putString("permissionType", type)
    return permissionBundle
}

fun whiteStatus(activity: Activity) {
    activity.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    activity.window.setBackgroundDrawable(null)
}

@SuppressLint("HardwareIds")
fun getDeviceId(context: Context): String {
    return Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
}
fun View.hide() {
    this.visibility = View.INVISIBLE
}

fun View.remove() {
    this.visibility = View.GONE
}
fun View.show() {
    this.visibility = View.VISIBLE
}









