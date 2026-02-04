package com.pwa.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.Window
import android.view.WindowManager
import android.webkit.WebView
import android.widget.Toast

fun initialLoadUrl(): String {
    // You have to update url URl
    return "https://app.ekincare.com"
    //return "https://pwa-staging.ekincare.com/pwa-login?slug=titan&message=ayyLk6jdIoYPV1xEDueg+H/deKqphUuhNWxm5xq9Lt85t66tT3pUiXXcnDVPhIA+1erDeVZHE7+/Jidc+hv44F3+jqHm1CJmLDRwpG50vQ1VXfU1Ku9SQxJBVL9DqUbmnCWcTQ==&auth_tag=3l9rJfh4gOPb/Lfe7ij5Qg=="

}

fun getLocalStorageDeviceId(
    context: Context
): String {
    // Use the context to dynamically generate the localStorageCode
    val storedValue = getDeviceId(context)
    val localStorageCode = StringBuilder()
    localStorageCode.append("window.localStorage.setItem('X-DEVICE-ID', '$storedValue');\n")
    localStorageCode.append("var data = window.localStorage.getItem('X-DEVICE-ID');\n")
    localStorageCode.append("console.log(data);\n")

    return localStorageCode.toString()
}



fun Window.disableScreenCapture() {
    setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE)
}

fun handleExternalUrl(url: String, context: Context) {
    val intentData = Intent(Intent.ACTION_VIEW)
    intentData.data = Uri.parse(url)
    context.startActivity(intentData)
}

fun handlePhoneNumber(url: String, context: Context) {
    val intent = Intent(Intent.ACTION_DIAL)
    intent.data = Uri.parse(url)
    context.startActivity(intent)
}
fun showErrorToast(context: Context) {
    Toast.makeText(context, "Failed loading app!", Toast.LENGTH_SHORT).show()
}
fun loadUrl(view: WebView, url: String) {
    view.loadUrl(url)
}
fun handleEmail(url: String, context: Context) {
    val intent = Intent(Intent.ACTION_SENDTO)
    intent.data = Uri.parse(url)

    // Check if there is an email client available to handle the intent
    if (intent.resolveActivity(context.packageManager) != null) {
        context.startActivity(intent)
    } else {
        Toast.makeText(context, "No email client found.", Toast.LENGTH_SHORT).show()
    }
}
fun handleSms(url: String, context: Context) {
    val intent = Intent(Intent.ACTION_SENDTO)
    intent.data = Uri.parse(url)

    // Check if there is a messaging app available to handle the intent
    if (intent.resolveActivity(context.packageManager) != null) {
        context.startActivity(intent)
    } else {
        Toast.makeText(context, "No messaging app found.", Toast.LENGTH_SHORT).show()
    }
}
