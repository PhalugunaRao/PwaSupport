package com.example.pwatestdemo.utils

import com.example.pwatestdemo.R


class PermissionUtil {
    companion object {
        const val MY_PERMISSIONS_REQUEST_LOCATION = 99
        const val PERMISSIONS_CAMERA = "CAMERA"
        const val PERMISSIONS_STORAGE = "STORAGE"
        const val PERMISSIONS_BOTH = "BOTH"
        const val PERMISSIONS_LOCATION = "LOCATION"
        const val PERMISSIONS_CALENDER = "CALENDER"
        const val PERMISSIONS_TAG = "PERMISSION"
        const val PERMISSIONS_ACTIVITY = "PHYSICAL"
        const val UPDATENOW = "Update now"
        const val PERMISSIONS_NOTIFICATION = "NOTIFICATION"

        fun permissionIcon(type: String): Int {
            return when (type) {
                UPDATENOW -> {
                    R.drawable.ic_launcher_background
                }
                PERMISSIONS_NOTIFICATION ,PERMISSIONS_ACTIVITY-> {
                    R.drawable.ic_permission_notification
                }
                PERMISSIONS_LOCATION -> {
                    R.drawable.ic_permission_location
                }
                PERMISSIONS_CAMERA -> {
                    R.drawable.ic_permission_camera
                }
                PERMISSIONS_STORAGE, PERMISSIONS_BOTH -> {
                    R.drawable.ic_permission_gallery
                }

                PERMISSIONS_CALENDER -> {
                    R.drawable.ic_permission_calendar
                }
                else -> {
                    R.drawable.ic_permission_camera
                }
            }
        }

        fun permissionTitle(type: String): String? {
            return when (type) {
                PERMISSIONS_ACTIVITY -> {
                    getStringOutside(R.string.permission_physical_title)
                }
                PERMISSIONS_NOTIFICATION -> {
                    getStringOutside(R.string.permission_notification_title)
                }
                PERMISSIONS_LOCATION -> {
                    getStringOutside(R.string.permission_location_title)
                }
                PERMISSIONS_CAMERA -> {
                    getStringOutside(R.string.permission_camera_title)
                }
                PERMISSIONS_STORAGE->{
                    getStringOutside(R.string.permission_storage_title)
                 }
                PERMISSIONS_BOTH -> {
                    getStringOutside(R.string.permission_storage_camera_title)
                }
                PERMISSIONS_CALENDER -> {
                    getStringOutside(R.string.permission_calendar_title)
                }

                else -> {
                    ""
                }
            }
        }
        fun permissionDesc(type: String): String? {
            return when (type) {
                PERMISSIONS_ACTIVITY -> {
                    getStringOutside(R.string.permission_physical_desc)
                }
                PERMISSIONS_NOTIFICATION -> {
                    getStringOutside(R.string.permission_notification_desc)
                }
                PERMISSIONS_LOCATION -> {
                    getStringOutside(R.string.permission_location_desc)
                }
                PERMISSIONS_CAMERA -> {
                    getStringOutside(R.string.permission_camera_desc)
                }
                PERMISSIONS_STORAGE->{
                    getStringOutside(R.string.permission_storage_desc)
                                     }
                PERMISSIONS_BOTH -> {
                    getStringOutside(R.string.permission_storage_camera_desc)
                }
                PERMISSIONS_CALENDER -> {
                    getStringOutside(R.string.permission_calendar_desc)
                }
                else -> {
                    ""
                }
            }
        }
    }
}
