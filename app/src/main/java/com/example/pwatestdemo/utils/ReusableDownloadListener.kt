package com.example.pwatestdemo.utils

import android.app.DownloadManager
import android.content.*
import android.net.Uri
import android.os.Environment
import android.webkit.DownloadListener
import android.webkit.URLUtil
import android.webkit.WebView
import android.widget.Toast
import androidx.core.content.FileProvider
import java.io.File

class ReusableDownloadListener(private val context: Context, private val webView: WebView) :
    DownloadListener {

    var filePath = ""
    override fun onDownloadStart(
        url: String,
        userAgent: String,
        contentDisposition: String,
        mimeType: String,
        contentLength: Long,
    ) {
        val onComplete = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                if (intent.action == DownloadManager.ACTION_DOWNLOAD_COMPLETE) {
                    val downloadId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1)
                    if (downloadId != -1L) {
                        Toast.makeText(context, "Downloading Complete", Toast.LENGTH_SHORT).show()
                        openDownloadedFile(context, File(filePath), mimeType)
                    }
                }
            }
        }

        if (url.startsWith("blob")) {
            // Handle blob URLs
            webView.loadUrl(JavaScriptInterfaceee.getBase64StringFromBlobUrl(url, mimeType))
        } else {
            // Handle regular download URLs
            val manager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
            val uri = Uri.parse(url)
            val request = DownloadManager.Request(uri)
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            request.setTitle(URLUtil.guessFileName(url, contentDisposition, mimeType))
            request.setMimeType(mimeType)
            request.setDestinationInExternalPublicDir(
                Environment.DIRECTORY_DOWNLOADS,
                URLUtil.guessFileName(url, contentDisposition, mimeType),
            )

            filePath =

                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).path + File.separator + URLUtil.guessFileName(
                    url,
                    contentDisposition,
                    mimeType,
                )

            manager.enqueue(request)
            context.registerReceiver(
                onComplete,
                IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE),
                "com.ekincare.webutil.DOWNLOAD_COMPLETE_PERMISSION",
                null,
            )
        }
    }
}

fun openDownloadedFile(context: Context, downloadedFile: File, mimeType: String) {
    if (downloadedFile.exists()) {
        val uri = FileProvider.getUriForFile(
            context,
            context.applicationContext.packageName + ".fileprovider",
            downloadedFile,
        )
        val intent = Intent(Intent.ACTION_VIEW)
        intent.setDataAndType(uri, mimeType)
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        try {
            context.startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            // Handle the case where a suitable PDF viewer app is not installed
        }
    } else {
        // Handle the case where the file does not exist
    }
}
