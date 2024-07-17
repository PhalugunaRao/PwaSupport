package com.example.pwatestdemo.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Environment
import com.example.pwatestdemo.BuildConfig
import android.os.Message
import android.provider.MediaStore
import android.util.Log
import android.webkit.ConsoleMessage
import android.webkit.GeolocationPermissions
import android.webkit.PermissionRequest
import android.webkit.ValueCallback
import android.webkit.WebChromeClient
import android.webkit.WebView
import androidx.core.content.FileProvider
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class ReusableChromeClient(
    private val mActivity: Activity,
) : WebChromeClient() {
    private var mUploadMessage: ValueCallback<Array<Uri>>? = null
    private var mCameraPhotoPath: String? = null

    override fun onCreateWindow(view: WebView, isDialog: Boolean, isUserGesture: Boolean, resultMsg: Message): Boolean {
        val transport = resultMsg.obj as WebView.WebViewTransport
        transport.webView = view
        resultMsg.sendToTarget()
        return true
    }

    override fun onProgressChanged(view: WebView, newProgress: Int) {
        super.onProgressChanged(view, newProgress)

        // Show the refresh indicator while the page is loading
        // swipeRefreshLayout.isRefreshing = newProgress < 100
//        if (newProgress == 100) {
//            swipeRefreshLayout.isRefreshing = false
//        }
    }

    override fun onGeolocationPermissionsShowPrompt(
        origin: String?,
        callback: GeolocationPermissions.Callback?,
    ) {
        callback?.invoke(origin, true, true)
    }

    override fun onPermissionRequest(request: PermissionRequest?) {
        super.onPermissionRequest(request)
    }

    override fun onConsoleMessage(consoleMessage: ConsoleMessage): Boolean {
        return super.onConsoleMessage(consoleMessage)
    }

    override fun onShowFileChooser(
        webView: WebView,
        filePathCallback: ValueCallback<Array<Uri>>,
        fileChooserParams: FileChooserParams,
    ): Boolean {
        if (mUploadMessage != null) {
            mUploadMessage!!.onReceiveValue(null)
            mUploadMessage = null
        }
        mUploadMessage = filePathCallback
        var takePictureIntent: Intent? = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (takePictureIntent!!.resolveActivity(mActivity.packageManager) != null) {
            var photoFile: File? = null
            try {
                photoFile = createImageFile()
                takePictureIntent.putExtra("PhotoPath", mCameraPhotoPath)
            } catch (ex: IOException) {
                Log.e("MyWebChromeClient", "Error creating image file: " + ex.message)
            }
            if (photoFile != null) {
                mCameraPhotoPath = "file:" + photoFile.absolutePath
                val imgUrl: Uri
                imgUrl = if (mActivity.applicationInfo.targetSdkVersion > Build.VERSION_CODES.M) {
                    val authority = BuildConfig.APPLICATION_ID + ".fileprovider"
                    //val authority = "com.example.pwatestdemo" + ".fileprovider"
                    FileProvider.getUriForFile(mActivity, authority, photoFile)
                } else {
                    Uri.fromFile(photoFile)
                }
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, imgUrl)
            } else {
                takePictureIntent = null
            }
        }
        val contentSelectionIntent = Intent(Intent.ACTION_GET_CONTENT)
        contentSelectionIntent.addCategory(Intent.CATEGORY_OPENABLE)
        contentSelectionIntent.type = "*/*"
        val intentArray: Array<Intent?>
        intentArray = takePictureIntent?.let { arrayOf(it) } ?: arrayOfNulls(0)
        val chooserIntent = Intent(Intent.ACTION_CHOOSER)
        chooserIntent.putExtra(Intent.EXTRA_INTENT, contentSelectionIntent)
        chooserIntent.putExtra(Intent.EXTRA_TITLE, "Image Chooser")
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, intentArray)
        mActivity.startActivityForResult(chooserIntent, FCR)
        return true
    }

    @Throws(IOException::class)
    private fun createImageFile(): File {
        @SuppressLint("SimpleDateFormat")
        val timeStamp =
            SimpleDateFormat("yyyyMMdd_HHmmss").format(
                Date(),
            )
        val imageFileName = "IMG_" + timeStamp + "_"
        val storageDir = mActivity.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(imageFileName, ".jpg", storageDir)
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == Activity.RESULT_CANCELED) {
            mUploadMessage?.onReceiveValue(null)
            mUploadMessage = null
        } else if (requestCode == FCR) {
            if (null == mUploadMessage) {
                return
            }
            var results: Array<Uri>? = null
            if (resultCode == Activity.RESULT_OK) {
                if (data == null || data.data == null) {
                    if (mCameraPhotoPath != null) {
                        results = arrayOf(Uri.parse(mCameraPhotoPath))
                    }
                } else {
                    val dataString = data.dataString
                    if (dataString != null) {
                        results = arrayOf(Uri.parse(dataString))
                    }
                }
            }
            mUploadMessage!!.onReceiveValue(results)
            mUploadMessage = null
        }
    }

    companion object {
        private const val FCR = 1
    }
}
