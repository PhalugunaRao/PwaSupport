package com.example.pwatestdemo.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.view.View

object NetworkUtil {
    fun isNetworkAvailable(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q -> {
                val capabilities = cm.getNetworkCapabilities(cm.activeNetwork) ?: return false
                return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            }
            else -> {
                val networkInfo = cm.activeNetworkInfo
                return networkInfo != null && networkInfo.isConnected
            }
        }
    }
}
