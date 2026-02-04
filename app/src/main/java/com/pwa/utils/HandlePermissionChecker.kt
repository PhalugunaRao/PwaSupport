package com.pwa.utils

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class HandlePermissionChecker(private val context: Context) {

    fun checkLocationPermissionStatus(): PermissionStatus {
        val permission = Manifest.permission.ACCESS_FINE_LOCATION
        return checkPermissionStatus(permission)
    }

    fun checkCameraPermissionStatus(): PermissionStatus {
        val permission = Manifest.permission.CAMERA
        return checkPermissionStatus(permission)
    }

    private fun checkStoragePermissionStatus(): PermissionStatus {
        val permission = if (PwaKeys.VERSION > 32) {
            Manifest.permission.READ_MEDIA_IMAGES
        } else {
            Manifest.permission.READ_EXTERNAL_STORAGE
        }
        return checkPermissionStatus(permission)
    }

    fun checkCameraAndStorage(): Boolean {
        return (checkStoragePermissionStatus().toString() == "GRANTED") ||
            (checkCameraPermissionStatus().toString() == "GRANTED")
    }

    fun checkCamera(): Boolean {
        return (checkCameraPermissionStatus().toString() == "GRANTED")
    }
    fun checkStorage(): Boolean {
        return (checkStoragePermissionStatus().toString() == "GRANTED")
    }

    fun isPostNotificationsPermissionGranted(): Boolean {
        return ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.POST_NOTIFICATIONS,
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun checkPermissionStatus(permission: String): PermissionStatus {
        val permissionStatus = ContextCompat.checkSelfPermission(context, permission)
        return when {
            permissionStatus == PackageManager.PERMISSION_GRANTED -> PermissionStatus.GRANTED
            ActivityCompat.shouldShowRequestPermissionRationale(context as Activity, permission) -> PermissionStatus.DENIED
            else -> PermissionStatus.NOT_ALLOWED
        }
    }

    fun requestLocationPermission(requestCode: Int) {
        val permission = Manifest.permission.ACCESS_FINE_LOCATION
        requestPermission(permission, requestCode)
    }

    fun requestCameraPermission(requestCode: Int) {
        val permission = Manifest.permission.CAMERA
        requestPermission(permission, requestCode)
    }

    fun requestStoragePermission(requestCode: Int) {
        val permission = Manifest.permission.WRITE_EXTERNAL_STORAGE
        requestPermission(permission, requestCode)
    }

    private fun requestPermission(permission: String, requestCode: Int) {
        ActivityCompat.requestPermissions(context as Activity, arrayOf(permission), requestCode)
    }
}

enum class PermissionStatus {
    GRANTED,
    DENIED,
    NOT_ALLOWED,
}
