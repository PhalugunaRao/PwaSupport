package com.example.pwatestdemo.utils

import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.view.View
import com.example.pwatestdemo.MyApplication
import com.razorpay.BuildConfig

object PwaKeys {
    const val RELOAD_URL = "https://app.ekincare.com/"
    const val RELOAD_URL_DASHBOARD = "https://app.ekincare.com/dashboard"
    const val RELOAD_URL_PRESCRIPTION = "https://app.ekincare.com/prescription-upload"
    const val JS_INTERFACE = "ekincareAndroidInterface"
    const val TRACKEVENT = "trackEvent"
    const val GRANTED = "granted"
    const val PROMPT = "prompt"
    const val STORAGE = "storage"
    const val CAMERA = "camera"
    const val LOCATION = "LOCATION"
    const val FAILED = "failed"
    const val DENIED = "denied"
    const val HEALTH_COACH_LOCAL = "https://pr-3875.dbxtbhegnbvf3.amplifyapp.com"
    const val GPS_REQUEST: Int = 1001
    const val PAYMENT_KEY: String = "rzp_test_hh1OMcKpr2K5c0"
    const val WALLET_LOGO: String =
        "https://res.cloudinary.com/ekincare/image/upload/v1541673563/hr_dashboard_login_page/ekincare-logo-new_200_200.png"

    val VERSION = Build.VERSION.SDK_INT
    fun handleLocalUrl(): String {
        return if (BuildConfig.DEBUG) HEALTH_COACH_LOCAL else RELOAD_URL
    }
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









