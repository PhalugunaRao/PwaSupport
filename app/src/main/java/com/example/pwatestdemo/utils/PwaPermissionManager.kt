package com.example.pwatestdemo.utils

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.ekincare.webscript.JavaScriptEvaluator
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.OnSuccessListener

class PwaPermissionManager(private val context: Context, private val webview: WebView) {
    private val cameraPermission = Manifest.permission.CAMERA
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var locationRequest: LocationRequest
    private lateinit var locationCallback: LocationCallback

    companion object {
        private const val CAMERA_PERMISSION_REQUEST_CODE = 3
        private const val STORAGE_PERMISSION_REQUEST_CODE = 2
        private const val permissionRequestCode = 1
        private const val CAMERA_STORAGE_PERMISSION_REQUEST_CODE = 6
        private const val LOCATION_SETTINGS_REQUEST_CODE = 123
        private const val PERMISSION_REQUEST_CODE = 123
        private  const val  MY_PERMISSIONS_REQUEST_ACTIVITY_RECOGNITION = 3001
    }

    private val locationPermissions = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION,
    )

    fun checkLocationPermissions(): Boolean {
        val fineLocationPermissionGranted = ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION,
        ) == PackageManager.PERMISSION_GRANTED

        val coarseLocationPermissionGranted = ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_COARSE_LOCATION,
        ) == PackageManager.PERMISSION_GRANTED

        return fineLocationPermissionGranted && coarseLocationPermissionGranted
    }

    fun handleLocationResult(location: Location) {
        val latitude = location.latitude
        val longitude = location.longitude

        val javascriptCode: String = JavaScriptCodeBuilder.buildCode2(
            JavaScriptCodeBuilder.LocationGrantJsonData("granted", latitude, longitude).toString(),
        )
        JavaScriptEvaluator.evaluateJavascript(
            webview,
            javascriptCode,
        ) { value ->
            // Handle the result returned by JavaScript
        }
    }

    fun requestLocationPermissions() {
        ActivityCompat.requestPermissions(
            context as Activity,
            locationPermissions,
            permissionRequestCode,
        )
    }

    fun checkActivityPermission(context: Context): Boolean {
        return ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACTIVITY_RECOGNITION
        ) == PackageManager.PERMISSION_GRANTED
    }

    fun isActivityRecognitionPermissionGranted(context: Activity) {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACTIVITY_RECOGNITION)
            != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            ActivityCompat.requestPermissions(context,
                arrayOf(Manifest.permission.ACTIVITY_RECOGNITION),
                MY_PERMISSIONS_REQUEST_ACTIVITY_RECOGNITION)
        }

    }



    fun hasCameraPermission(): Boolean {
        val cameraPermission = Manifest.permission.CAMERA
        return ContextCompat.checkSelfPermission(context, cameraPermission) == PackageManager.PERMISSION_GRANTED
    }

    fun hasStoragePermission(): Boolean {
        val storagePermission = if (PwaKeys.VERSION > 32) {
            Manifest.permission.READ_MEDIA_IMAGES
        } else {
            Manifest.permission.READ_EXTERNAL_STORAGE
        }
        return ContextCompat.checkSelfPermission(context, storagePermission) == PackageManager.PERMISSION_GRANTED
    }

    fun requestStoragePermission() {
        val permissions = if (PwaKeys.VERSION > 32) {
            arrayOf(Manifest.permission.READ_MEDIA_IMAGES)
        } else {
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
        }
        ActivityCompat.requestPermissions(
            context as Activity,
            permissions,
            STORAGE_PERMISSION_REQUEST_CODE,
        )
    }

    fun requestCameraPermissions() {
        val permissions = arrayOf(
            Manifest.permission.CAMERA,
        )
        ActivityCompat.requestPermissions(
            context as Activity,
            permissions,
            CAMERA_PERMISSION_REQUEST_CODE,
        )
    }

    fun requestCameraAndStoragePermissions() {
        val permissions = if (PwaKeys.VERSION > 32) {
            arrayOf(
                Manifest.permission.CAMERA,
                Manifest.permission.READ_MEDIA_IMAGES,
            )
        } else {
            arrayOf(
                Manifest.permission.CAMERA,
                Manifest.permission.READ_EXTERNAL_STORAGE,
            )
        }
        ActivityCompat.requestPermissions(
            context as Activity,
            permissions,
            CAMERA_STORAGE_PERMISSION_REQUEST_CODE,
        )
    }

    fun checkLocationSettings() {
        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val isLocationEnabled =
            locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)

        if (isLocationEnabled) {
            getLocation()
            // Location services are enabled, proceed with location-related operations
            // You can start retrieving the device's location here
            // ...
        } else {
            // Location services are not enabled, show a dialog to the user
            GpsUtils(context as Activity).turnGPSOn { isGPSEnable ->
                getLocation()
            }
        }
    }

    fun storageJs(permissionName: String, permissionStatus: String) {
        val javascriptCode: String = JavaScriptCodeBuilder.buildCode2(
            JavaScriptCodeBuilder.permissionJsonData(permissionName, permissionStatus).toString(),
        )
        JavaScriptEvaluator.evaluateJavascript(
            webview,
            javascriptCode,
        ) { value ->
            // Handle the result returned by JavaScript
        }
    }

    fun showPermissionDeniedDialog() {
        AlertDialog.Builder(context)
            .setMessage("Location permission denied. Some features may not be available.")
            .setPositiveButton("OK") { dialog: DialogInterface, _: Int ->
                dialog.dismiss()
            }
            .setCancelable(false)
            .show()
    }

    fun getLocation() {
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION,
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION,
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // Request the missing location permissions if not granted
            ActivityCompat.requestPermissions(
                context as Activity,
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                ),
                PERMISSION_REQUEST_CODE,
            )
            return
        }

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
        fusedLocationClient?.lastLocation?.addOnSuccessListener(
            context as Activity,
            OnSuccessListener<Location> { location: Location? ->
                if (location != null) {
                    handleLocationResult(location)
                } else {
                    createAndRequestLocationUpdates()
                }
            },
        )
    }

    fun isLocationEnabled(): Boolean {
        val locationManager = context.getSystemService(AppCompatActivity.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
            locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
    }

    private fun createAndRequestLocationUpdates() {
        locationRequest = LocationRequest.create().apply {
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
            interval = 10000 // Update interval in milliseconds
        }

        // Create and configure the location callback
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                // Handle the location update result
                val lastLocation = locationResult.lastLocation
                if (lastLocation != null) {
                    handleLocationResult(lastLocation)
                    fusedLocationClient.removeLocationUpdates(this) // Stop location updates after retrieving the current location
                } else {
                    locationFailed(PwaKeys.FAILED)
                }
            }
        }

        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION,
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION,
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        fusedLocationClient?.requestLocationUpdates(
            locationRequest,
            locationCallback,
            null,
        )
    }

    fun locationFailed(status: String) {
        val javascriptCode: String = JavaScriptCodeBuilder.buildCode2(
            JavaScriptCodeBuilder.LocationGrantJsonData(status, 0.0, 0.0).toString(),
        )
        JavaScriptEvaluator.evaluateJavascript(
            webview,
            javascriptCode,
        ) { value ->
            // Handle the result returned by JavaScript
        }
    }
}
