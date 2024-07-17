package com.example.pwatestdemo.utils

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.core.content.ContextCompat
import com.example.pwatestdemo.MainActivity
import com.google.gson.Gson

class WebViewMethodHandler {
    fun handleOnNativeMethod(activity: MainActivity, data: String?) {
        val jsRequestHandler = HandleHeaderFromScript()
        val payloadData = jsRequestHandler.handleScriptData(activity, data = data!!)
        println("=======payloadData$payloadData")
        when {
            // this will handle extrenal url like out side webview will open
            payloadData.first.equals("externalurl", true) -> {
                val externalData =
                    Gson().fromJson(
                        payloadData.second,
                        ExternalData::class.java,
                    )
                externalData.url?.let { url ->
                    handleExternalUrl(url, activity)
                }
            }

            payloadData.first.equals("fetchLocation", true) -> {
                if (HandlePermissionChecker(activity).checkLocationPermissionStatus()
                        .toString() == "GRANTED"
                ) {
                    activity.handleLocationSettings()
                } else {
                    val dialogFragment = PermissionAllowDialog()
                    dialogFragment.setPermissionListener(activity)
                    dialogFragment.setPermissionCLoseListener(activity)
                    dialogFragment.arguments = permissionBundle(PermissionUtil.PERMISSIONS_LOCATION)
                    dialogFragment.show(
                        activity.supportFragmentManager,
                        PermissionUtil.PERMISSIONS_TAG,
                    )
                }
            }

            payloadData.first.equals("fetchPermissionsData", true) -> {
                val permissionData =
                    Gson().fromJson(
                        payloadData.second,
                        PermissionPayloadData::class.java,
                    )
                permissionData.permissions?.forEach { permission ->
                    when (permission.lowercase()) {
                        "storage" -> handleStoragePermission(activity)
                        "camera" -> handleCameraPermission(activity)
                    }
                }
            }

            payloadData.first.equals("requestPermissions", true) -> {
                val permissionData =
                    Gson().fromJson(
                        payloadData.second,
                        PermissionPayloadData::class.java,
                    )
                handleBoth(activity)
//                if (permissionData.permissions?.size!! > 1) {
//                    handleBoth(activity)
//                } else {
//                    permissionData.permissions?.forEach { permission ->
//                        handlePermission(permission, activity)
//                    }
//                }
            }

            payloadData.first.equals("collectPayment", true) -> {
                val paymentData =
                    Gson().fromJson(
                        payloadData.second,
                        PaymentResponseData::class.java,
                    )
                activity.startPayment(paymentData)
            }

            payloadData.first.equals("nativeShare", true) -> {
                val share = Intent(Intent.ACTION_SEND)
                share.type = "text/plain"
                share.putExtra(Intent.EXTRA_SUBJECT, "Title Of The Post")
                share.putExtra(
                    Intent.EXTRA_TEXT,
                    "https://play.google.com/store/apps/details?id=com.ekincare&hl=en",
                )
                activity.startActivity(Intent.createChooser(share, "Share link!"))
            }

        }
    }

    fun handleBoth(activity: MainActivity) {
        val dialogFragment = PermissionAllowDialog()
        dialogFragment.setPermissionListener(activity)
        dialogFragment.setPermissionCLoseListener(activity)
        dialogFragment.arguments = permissionBundle(PermissionUtil.PERMISSIONS_BOTH)
        dialogFragment.show(
            activity.supportFragmentManager,
            PermissionUtil.PERMISSIONS_TAG,
        )
    }

    fun handlePermission(permission: String, activity: MainActivity) {
        val checker = HandlePermissionChecker(activity)
        val isStoragePermission = permission.equals("storage", true)
        val isCameraPermission = permission.equals("camera", true)
        val key = if (isStoragePermission) PwaKeys.STORAGE else PwaKeys.CAMERA
        val permissionsToCheck =
            if (isStoragePermission) PermissionUtil.PERMISSIONS_STORAGE else PermissionUtil.PERMISSIONS_CAMERA
        if ((isStoragePermission && checker.checkStorage()) || (isCameraPermission && checker.checkCamera())) {
            activity.storageJs(key, PwaKeys.GRANTED)
        } else {
            val dialogFragment = PermissionAllowDialog()
            dialogFragment.setPermissionListener(activity)
            dialogFragment.setPermissionCLoseListener(activity)
            dialogFragment.arguments = permissionBundle(permissionsToCheck)
            dialogFragment.show(
                activity.supportFragmentManager,
                PermissionUtil.PERMISSIONS_TAG,
            )
        }
    }

    private fun clearWebViewCache(activity: MainActivity) {
        with(activity.binding.webviewPwa) {
            clearCache(true)
            clearHistory()
            clearFormData()
            clearMatches()
            clearSslPreferences()
        }
    }

    fun handleStoragePermission(activity: MainActivity) {
        val checker = HandlePermissionChecker(activity)
        val permission = if (checker.checkStorage()) PwaKeys.GRANTED else PwaKeys.PROMPT
        activity.storageJs(PwaKeys.STORAGE, permission)
    }

    fun handleCameraPermission(activity: MainActivity) {
        val checker = HandlePermissionChecker(activity)
        val permission = if (checker.checkCamera()) PwaKeys.GRANTED else PwaKeys.PROMPT
        activity.storageJs(PwaKeys.CAMERA, permission)
    }

    fun checkActivityPermission(context: MainActivity): Boolean {
        return ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACTIVITY_RECOGNITION
        ) == PackageManager.PERMISSION_GRANTED
    }

    fun handleExternalUrl(url: String, context: Context) {
        val intentData = Intent(Intent.ACTION_VIEW)
        intentData.data = Uri.parse(url)
        context.startActivity(intentData)
    }
}
