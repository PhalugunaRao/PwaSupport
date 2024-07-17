package com.example.pwatestdemo

import android.app.Activity
import android.Manifest
import android.content.Intent
import androidx.databinding.DataBindingUtil
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.webkit.CookieManager
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebView.setWebContentsDebuggingEnabled
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.ekincare.webscript.JavaScriptEvaluator
import com.ekincare.webscript.WebViewMethodHandler
import com.ekincare.webutil.PaymentResponseData
import com.example.pwatestdemo.databinding.ActivityMainBinding
import com.example.pwatestdemo.utils.JavaScriptCodeBuilder
import com.example.pwatestdemo.utils.JavaScriptInterfaceee
import com.example.pwatestdemo.utils.NetworkUtil
import com.example.pwatestdemo.utils.PaymentHandler
import com.example.pwatestdemo.utils.PermissionAllowDialog
import com.example.pwatestdemo.utils.PermissionUtil.Companion.PERMISSIONS_ACTIVITY
import com.example.pwatestdemo.utils.PermissionUtil.Companion.PERMISSIONS_BOTH
import com.example.pwatestdemo.utils.PermissionUtil.Companion.PERMISSIONS_CAMERA
import com.example.pwatestdemo.utils.PermissionUtil.Companion.PERMISSIONS_LOCATION
import com.example.pwatestdemo.utils.PermissionUtil.Companion.PERMISSIONS_NOTIFICATION
import com.example.pwatestdemo.utils.PermissionUtil.Companion.PERMISSIONS_STORAGE
import com.example.pwatestdemo.utils.PreferenceHelper
import com.example.pwatestdemo.utils.PwaKeys
import com.example.pwatestdemo.utils.PwaPermissionManager
import com.example.pwatestdemo.utils.ReusableChromeClient
import com.example.pwatestdemo.utils.ReusableDownloadListener
import com.example.pwatestdemo.utils.ReusableWebViewClient
import com.example.pwatestdemo.utils.initialLoadUrl
import com.example.pwatestdemo.utils.remove
import com.example.pwatestdemo.utils.show
import com.example.pwatestdemo.utils.whiteStatus
import com.razorpay.PaymentData
import com.razorpay.PaymentResultWithDataListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject

class MainActivity : AppCompatActivity() ,
    JavaScriptInterfaceee.CallbackListener,
    PaymentResultWithDataListener,
    PermissionAllowDialog.PermissionCloseListener,
    PermissionAllowDialog.PermissionListener {
        lateinit var binding: ActivityMainBinding
        var redirectPage = ""
        var deeplinkUrl: String? = null
        var appLinkData: Uri? = null
        var extraParams = JSONObject()
        var type: String? = ""
        var title: String? = ""
        var message: String? = ""
        var customerId: String? = ""
        var payloadData: JSONObject? = null
        var deviceId: String? = null
        var prefs: PreferenceHelper? = null
        var bundle: Bundle? = null
        val GOOGLE_FIT_PERMISSIONS_REQUEST_CODE = 100
        private lateinit var webViewMethodHandler: WebViewMethodHandler
        private val REQUEST_CODE_CONFIRM_DEVICE_CREDENTIALS = 22
        private val SECURITY_SETTING_REQUEST_CODE = 233
        private val CAMERA_STORAGE_PERMISSION_REQUEST_CODE = 6
        private val CAMERA_PERMISSION_REQUEST_CODE = 3
        private val STORAGE_PERMISSION_REQUEST_CODE = 2
        private val MY_PERMISSIONS_REQUEST_ACTIVITY_RECOGNITION = 3001
        private lateinit var webView: WebView
        private var appInBackground = false

        private val paymentHandler: PaymentHandler by lazy {
            PaymentHandler(webView)
        }
        private val permissionRequestCode = 1

        private lateinit var permissionManager: PwaPermissionManager

        private val REQUEST_LOCATION_SETTINGS = 10

        private var webChromeClient2: ReusableChromeClient? = null

        override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            super.onActivityResult(requestCode, resultCode, data)
            if (resultCode == Activity.RESULT_OK) {
                if (requestCode == PwaKeys.GPS_REQUEST) {
                    if (permissionManager.isLocationEnabled()) {
                        permissionManager.getLocation()
                    } else {
                        permissionManager.checkLocationSettings()
                    }
                } else {
                    if (requestCode == GOOGLE_FIT_PERMISSIONS_REQUEST_CODE) {
                        val javascriptCode: String = JavaScriptCodeBuilder.buildCode2(
                            JavaScriptCodeBuilder.createGFConnectJsonData().toString(),
                        )
                        JavaScriptEvaluator.evaluateJavascript(
                            binding.webviewPwa,
                            javascriptCode,
                        ) { value ->
                            // Handle the result returned by JavaScript
                        }
                    } else {
                        webChromeClient2?.onActivityResult(requestCode, resultCode, data)
                    }
                }
            } else {
                    webChromeClient2?.onActivityResult(requestCode, resultCode, data)
            }
        }

        override fun onResume() {
            super.onResume()
        }

        override fun onPause() {
            super.onPause()
            appInBackground = true
        }

        override fun onStart() {
            super.onStart()
        }

        override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<String>,
            grantResults: IntArray,
        ) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
            when (requestCode) {
                CAMERA_PERMISSION_REQUEST_CODE -> {
                    val cameraPermission = Manifest.permission.CAMERA
                    val cameraPermissionGranted =
                        grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    if (cameraPermissionGranted) {
                        permissionManager.storageJs(PwaKeys.CAMERA, PwaKeys.GRANTED)
                    } else {
                        permissionManager.storageJs(PwaKeys.CAMERA, PwaKeys.DENIED)
                        checkUserRequestedDontAskAgain(cameraPermission)
                    }
                }
                STORAGE_PERMISSION_REQUEST_CODE -> {
                    val readMediaImagesPermission = if (PwaKeys.VERSION > 32) {
                        Manifest.permission.READ_MEDIA_IMAGES
                    } else {
                        Manifest.permission.READ_EXTERNAL_STORAGE
                    }
                    val readMediaImagesPermissionGranted =
                        grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    if (readMediaImagesPermissionGranted) {
                        permissionManager.storageJs(PwaKeys.STORAGE, PwaKeys.GRANTED)
                    } else {
                        permissionManager.storageJs(PwaKeys.STORAGE, PwaKeys.DENIED)
                        checkUserRequestedDontAskAgain(readMediaImagesPermission)
                    }
                }
                CAMERA_STORAGE_PERMISSION_REQUEST_CODE -> {
                    val cameraPermission = Manifest.permission.CAMERA
                    val cameraPermissionGranted =
                        grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    val storagePermissionGranted =
                        grantResults.size > 1 && grantResults[1] == PackageManager.PERMISSION_GRANTED
                    permissionManager.storageJs(PwaKeys.CAMERA, if (cameraPermissionGranted) PwaKeys.GRANTED else PwaKeys.DENIED)
                    permissionManager.storageJs(PwaKeys.STORAGE, if (storagePermissionGranted) PwaKeys.GRANTED else PwaKeys.DENIED)

                    if (cameraPermissionGranted && storagePermissionGranted) {
                        // Both camera and storage permissions granted
                    } else {
                        if (cameraPermissionGranted) {
                        } else {
                            checkUserRequestedDontAskAgain(cameraPermission)
                        }
                    }
                }
                permissionRequestCode -> {
                    if (permissions.contains(Manifest.permission.ACCESS_FINE_LOCATION) &&
                        permissions.contains(Manifest.permission.ACCESS_COARSE_LOCATION)
                    ) {
                        if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                            permissionManager.checkLocationSettings()
                        } else {
                            permissionManager.showPermissionDeniedDialog()
                        }
                    } else {
                    }
                }
            }
        }

        private fun checkUserRequestedDontAskAgain(permission: String) {
            val rationalFlag = !shouldShowRequestPermissionRationale(permission)
            if (rationalFlag) {
                // The user has denied the permission and selected "Don't ask again"
                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.fromParts("package", packageName, null))
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            }
        }



        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(null)
            whiteStatus(this)
            this.window.statusBarColor = ContextCompat.getColor(this, R.color.white)
            binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
            webView = binding.webviewPwa
            prefs = PreferenceHelper(this)
            webViewMethodHandler = WebViewMethodHandler()
            permissionManager = PwaPermissionManager(this, binding.webviewPwa)

            try {
                val extras = intent.extras
                extras?.apply {
                    deeplinkUrl = getString("deeplink_url")
                }
            } catch (_: Exception) {
            }
            val appLinkIntent = intent
            appLinkData = appLinkIntent.data
            var userAgent = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 " +
                    "(KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36"
            binding.webviewPwa.isLongClickable = true
            binding.webviewPwa.setOnLongClickListener { true }
            binding.webviewPwa.settings.javaScriptCanOpenWindowsAutomatically = true
            binding.webviewPwa.settings.setSupportMultipleWindows(false)
            CookieManager.getInstance().setAcceptThirdPartyCookies(binding.webviewPwa, true)
            binding.webviewPwa.settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
            binding.webviewPwa.settings.apply {
                useWideViewPort = true
                loadWithOverviewMode = true
                javaScriptEnabled = true
                allowFileAccess = true
                mixedContentMode = 0
                binding.webviewPwa.setLayerType(View.LAYER_TYPE_HARDWARE, null)
                loadsImagesAutomatically = true
                builtInZoomControls = true
                displayZoomControls = false
                loadWithOverviewMode = true
                databaseEnabled = true
                domStorageEnabled = true
                allowContentAccess = true
                allowFileAccess = true
                cacheMode = WebSettings.LOAD_DEFAULT
                userAgent = userAgent
                setGeolocationEnabled(true)
                allowFileAccessFromFileURLs = false
                allowUniversalAccessFromFileURLs = false
                mixedContentMode = WebSettings.MIXED_CONTENT_NEVER_ALLOW

            }
            webChromeClient2 = ReusableChromeClient(this)

            binding.webviewPwa.apply {
                setWebContentsDebuggingEnabled(true)
                clearHistory()
                clearFormData()
                // clearCache(true)
                clearSslPreferences()
                addJavascriptInterface(
                    JavaScriptInterfaceee(applicationContext, this@MainActivity),
                    PwaKeys.JS_INTERFACE,
                )
                webViewClient =
                    ReusableWebViewClient(this@MainActivity)
                if (appLinkData.toString() == "null" && deeplinkUrl.isNullOrEmpty()) {
                    loadUrl(initialLoadUrl())
                }
                setDownloadListener(
                    ReusableDownloadListener(
                        this@MainActivity,
                        binding.webviewPwa,
                    ),
                )
                webChromeClient = webChromeClient2
            }



            binding.refresh.setOnClickListener {
                if (NetworkUtil.isNetworkAvailable(this)) {
                    binding.webviewPwa.reload()
                    CoroutineScope(Dispatchers.Main).launch {
                        delay(1000)
                        binding.noConnectionLayout.remove()
                    }
                }
            }
        }


        override fun onNewIntent(intent: Intent) {
            super.onNewIntent(intent)
            setIntent(intent)  // Update the intent held by the activity
            handleIntent(intent)
        }
        private fun handleIntent(intent: Intent?) {
            intent?.let {
                val url = it.getStringExtra("deeplink_url")
                CoroutineScope(Dispatchers.Main).launch {
                    loadUrlData(url ?: "")
                }
            }
        }

        fun handleLocationSettings() {
            permissionManager.checkLocationSettings()
        }

        fun handleGoogleFitScript() {
            if (prefs?.isFitConnected == true) {
                val javascriptCode: String = JavaScriptCodeBuilder.buildCode2(
                    JavaScriptCodeBuilder.createGFConnectJsonData().toString(),
                )
                JavaScriptEvaluator.evaluateJavascript(binding.webviewPwa, javascriptCode) { value ->
                }
            }
        }




        private suspend fun loadUrlData(url: String) {
            withContext(Dispatchers.Main) {
                binding.webviewPwa.loadUrl(url)
            }
        }

        override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
            if (event.action == KeyEvent.ACTION_DOWN) {
                when (keyCode) {
                    KeyEvent.KEYCODE_BACK -> {
                        if (binding.webviewPwa.canGoBack()) {
                            binding.webviewPwa.goBack()
                        } else {
                            if ((appLinkData.toString() != "null" || !deeplinkUrl.isNullOrEmpty()) &&
                                !binding.webviewPwa.canGoBack()){
                                CoroutineScope(Dispatchers.Main).launch {
                                    loadUrlData(initialLoadUrl())

                                    delay(500) // Add a small delay to ensure URL is loaded (you can adjust this value)
                                    binding.webviewPwa.clearHistory()

                                    deeplinkUrl= null
                                    appLinkData= null
                                }
                            }else {
                                finish()
                            }
                        }
                        dismissKeyboard()

                        return true
                    }
                }
            }
            return super.onKeyDown(keyCode, event)
        }

        private fun dismissKeyboard() {
            val inputMethodManager =
                getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(binding.webviewPwa.windowToken, 0)
        }



        fun handleError() {
            if (NetworkUtil.isNetworkAvailable(this)) {
                binding.noConnectionLayout.remove()
            } else {
                binding.noConnectionLayout.show()
            }
        }

        override fun onNativewMethod(data: String?) {
            webViewMethodHandler.handleOnNativeMethod(this, data)
        }



        fun startPayment(paymentData: PaymentResponseData) {
            paymentHandler.startPayment(this,paymentData)
        }

        override fun onPaymentSuccess(razorpayPaymentID: String?, paymentData: PaymentData?) {
            paymentHandler.handlePaymentSuccess(razorpayPaymentID, paymentData)
        }

        override fun onPaymentError(code: Int, response: String?, paymentData: PaymentData?) {
            paymentHandler.handlePaymentFailure(code)
        }

        fun storageJs(permissionName: String, permissionStatus: String) {
            val javascriptCode: String = JavaScriptCodeBuilder.buildCode2(
                JavaScriptCodeBuilder.permissionJsonData(permissionName, permissionStatus).toString(),
            )
            JavaScriptEvaluator.evaluateJavascript(
                binding.webviewPwa,
                javascriptCode,
            ) { value ->
                // Handle the result returned by JavaScript
            }
        }

        override fun onCLose(type: String) {
            when {
                type.equals(PERMISSIONS_NOTIFICATION, true) -> {
                    PreferenceHelper(this).isNp = true
                }
                type.equals(PERMISSIONS_LOCATION, true) -> {
                    permissionManager.locationFailed(PwaKeys.DENIED)
                }
                type.equals(PERMISSIONS_STORAGE, true) -> {
                    permissionManager.storageJs(PwaKeys.STORAGE, PwaKeys.PROMPT)
                }
                type.equals(PERMISSIONS_CAMERA, true) -> {
                    permissionManager.storageJs(PwaKeys.CAMERA, PwaKeys.PROMPT)
                }
                type.equals(PERMISSIONS_BOTH, true) -> {
                    permissionManager.storageJs(PwaKeys.STORAGE, PwaKeys.PROMPT)
                    permissionManager.storageJs(PwaKeys.CAMERA, PwaKeys.PROMPT)
                }
            }
        }
        override fun permissionGrant(type: String) {
            when {
                type.equals(PERMISSIONS_ACTIVITY, true) -> {
                    permissionManager.isActivityRecognitionPermissionGranted(this)
                }
                type.equals(PERMISSIONS_BOTH, true) -> {
                    if (!permissionManager.hasCameraPermission() && !permissionManager.hasStoragePermission()) {
                        permissionManager.requestCameraAndStoragePermissions()
                    } else {
                        permissionManager.storageJs(PwaKeys.STORAGE, PwaKeys.GRANTED)
                        permissionManager.storageJs(PwaKeys.CAMERA, PwaKeys.GRANTED)
                    }
                }
                type.equals(PERMISSIONS_LOCATION, true) -> {
                    if (!permissionManager.checkLocationPermissions()) {
                        permissionManager.requestLocationPermissions()
                    } else {
                        permissionManager.checkLocationSettings()
                    }
                }
                type.equals(PERMISSIONS_CAMERA, true) -> {
                    if (!permissionManager.hasCameraPermission()) {
                        permissionManager.requestCameraPermissions()
                    } else {
                        permissionManager.storageJs(PwaKeys.CAMERA, PwaKeys.GRANTED)
                    }
                }
                type.equals(PERMISSIONS_STORAGE, true) -> {
                    if (!permissionManager.hasStoragePermission()) {
                        permissionManager.requestStoragePermission()
                    } else {
                        permissionManager.storageJs(PwaKeys.STORAGE, PwaKeys.GRANTED)
                    }
                }
            }
        }
    }
