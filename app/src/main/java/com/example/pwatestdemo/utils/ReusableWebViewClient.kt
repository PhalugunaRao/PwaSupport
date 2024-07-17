package com.example.pwatestdemo.utils

import android.content.Context
import android.content.res.AssetManager
import android.graphics.Bitmap
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.pwatestdemo.MainActivity
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import java.util.regex.Pattern

class ReusableWebViewClient(private val context: Context) : WebViewClient() {

    private val okHttpClient: OkHttpClient
    init {
        val cacheSize = (10 * 1024 * 1024).toLong() // Specify the cache size (e.g., 10 MB)
        val cacheDirectory = File(context.cacheDir, "webview_cache")
        val cache = Cache(cacheDirectory, cacheSize)
        okHttpClient = OkHttpClient.Builder()
            .cache(cache)
            .build()
    }

    override fun shouldInterceptRequest(
        view: WebView?,
        request: WebResourceRequest?,
    ): WebResourceResponse? {
        val url = request?.url.toString()
        if (isImageResource(request) || isJsResource(request) || isCssResource(request) || isSvgResource(request)) {
            // Check if the resource is available in the cache directory
            val cachedResponse = loadFromCacheOrResource(url)
            // val cachedResponse = loadFromCache(url)
            if (cachedResponse != null) {
                return cachedResponse
            } else {
                // Cache the resource if it's not already cached
                if (url.contains(initialLoadUrl())) {
                    cacheResource(url)
                }
            }
        }
        return super.shouldInterceptRequest(view, request)
    }

    private fun cacheResource(url: String) {
        try {
            val cacheDirectory = File(context.cacheDir, "webview_cache")
            if (!cacheDirectory.exists()) {
                cacheDirectory.mkdirs()
            }

            val file = File(cacheDirectory, getCacheFilename(url))
            if (!file.exists()) {
                val httpRequest = Request.Builder()
                    .url(url)
                    .build()

                val httpResponse = okHttpClient.newCall(httpRequest).execute()
                val responseBody = httpResponse.body()

                if (responseBody != null) {
                    val inputStream = responseBody.byteStream()
                    val outputStream = FileOutputStream(file)

                    inputStream.copyTo(outputStream)

                    inputStream.close()
                    outputStream.close()

                    // Parse the downloaded resource to find additional resources
                    if (isHtmlResource(url)) {
                        val htmlContent = loadContentFromFile(file)
                        val additionalUrls = extractAdditionalUrls(htmlContent)

                        for (additionalUrl in additionalUrls) {
                            cacheResource(additionalUrl)
                        }
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun loadContentFromFile(file: File): String {
        return file.readText()
    }
    private fun extractAdditionalUrls(htmlContent: String): List<String> {
        val urls = mutableListOf<String>()
        val pattern = Pattern.compile("src\\s*=\\s*['\"]([^'\"]+)['\"]")
        val matcher = pattern.matcher(htmlContent)

        while (matcher.find()) {
            val url = matcher.group(1)
            urls.add(url)
        }

        return urls
    }

    private fun isHtmlResource(request: String?): Boolean {
        val url = request
        val mimeType = url?.let { getMimeType(it) }
        return mimeType?.startsWith("text/html") == true
    }
    private fun getCacheFilename(url: String): String {
        val maxLength = 100 // Adjust the maximum length as needed
        val truncatedFilename = url.replace("[^a-zA-Z0-9.-]".toRegex(), "_")

        return if (truncatedFilename.length <= maxLength) {
            truncatedFilename
        } else {
            truncatedFilename.substring(0, maxLength)
        }
    }

    private fun loadFromCacheOrResource(url: String): WebResourceResponse? {
        try {
            val fileName = getResourceName(url)
            val fileType = getFileType(fileName)
            val assets = context.assets
            if (fileType.isNotEmpty()) {
                val inputStream = openAssetStream(assets, fileType, fileName)
                if (inputStream != null) {
                    val mimeType = getMimeType(url)
                    val responseHeaders = mutableMapOf<String, String>()
                    responseHeaders["Access-Control-Allow-Origin"] = initialLoadUrl()

                    return WebResourceResponse(mimeType, "UTF-8", 200, "OK", responseHeaders, inputStream)
                }
            } else {
                // File not found in the assets folder, continue with cache handling
                val cacheDirectory = File(context.cacheDir, "webview_cache")
                val cachedFilename = getCacheFilename(url)
                val cachedFile = File(cacheDirectory, cachedFilename)
                if (cachedFile.exists()) {
                    val mimeType = getMimeType(url)
                    val inputStream = FileInputStream(cachedFile)

                    val responseHeaders = mutableMapOf<String, String>()
                    responseHeaders["Access-Control-Allow-Origin"] = "*"

                    return WebResourceResponse(mimeType, "UTF-8", 200, "OK", responseHeaders, inputStream)
                } else {
                    // Download the file and save it to the cache
                    val fileData = downloadImage(url)

                    if (fileData != null) {
                        val mimeType = getMimeType(url)
                        val outputStream = FileOutputStream(cachedFile)
                        outputStream.write(fileData)
                        outputStream.close()

                        val inputStream = FileInputStream(cachedFile)

                        val responseHeaders = mutableMapOf<String, String>()
                        responseHeaders["Access-Control-Allow-Origin"] = "*"

                        return WebResourceResponse(mimeType, "UTF-8", 200, "OK", responseHeaders, inputStream)
                    }
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return null
    }

    private fun downloadImage(url: String): ByteArray? {
        try {
            val updatedUrl = url.replace("http://", "https://")
            val connection = URL(updatedUrl).openConnection() as HttpURLConnection
            connection.connectTimeout = 5000
            connection.readTimeout = 5000
            connection.connect()

            if (connection.responseCode == HttpURLConnection.HTTP_OK) {
                val inputStream = connection.inputStream
                val outputStream = ByteArrayOutputStream()

                val buffer = ByteArray(4096)
                var bytesRead: Int

                while (inputStream.read(buffer).also { bytesRead = it } != -1) {
                    outputStream.write(buffer, 0, bytesRead)
                }

                outputStream.close()
                inputStream.close()

                return outputStream.toByteArray()
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return null
    }

    private fun openAssetStream(
        assetManager: AssetManager,
        fileType: String,
        assetPath: String,
    ): InputStream? {
        val folder = when (fileType) {
            "css" -> "css"
            "js" -> "js"
            "media" -> "media"
            else -> ""
        }

        if (folder.isNotEmpty()) {
            val filePath = "$folder/$assetPath"
            return try {
                assetManager.open(filePath)
            } catch (e: IOException) {
                null
            }
        }

        return null
    }

    private fun getResourceName(url: String): String {
        // Extract the file name from the URL
        val fileName = url.substringAfterLast("/")

        // Remove the file extension
        return fileName
        // fileName.substringBeforeLast(".")
    }

    private fun getAssetPath(url: String): String {
        // Remove any query parameters or fragments from the URL
        val cleanUrl = url.split("?")[0].split("#")[0]

        // Remove the "file:///android_asset/" prefix from the URL
        return cleanUrl.removePrefix("file:///android_asset/")
    }

    private fun getFileType(fileName: String): String {
        val extension = fileName.substringAfterLast('.', "")
        return when (extension) {
            "css" -> "css"
            "js" -> "js"
            "png", "jpg", "jpeg", "gif", "svg", "webp" -> "media"
            "woff2" -> "fonts"
            else -> ""
        }
    }

    private fun isSvgResource(request: WebResourceRequest?): Boolean {
        val url = request?.url?.toString()
        val mimeType = url?.let { getMimeType(it) }
        return mimeType?.startsWith("image/svg+xml") == true
    }
    private fun isJsResource(request: WebResourceRequest?): Boolean {
        val url = request?.url?.toString()
        val mimeType = url?.let { getMimeType(it) }
        return mimeType?.startsWith("text/javascript") == true
    }

    private fun isCssResource(request: WebResourceRequest?): Boolean {
        val url = request?.url?.toString()
        val mimeType = url?.let { getMimeType(it) }
        return mimeType?.startsWith("text/css") == true
    }

    private fun isImageResource(request: WebResourceRequest?): Boolean {
        val url = request?.url?.toString()
        val mimeType = url?.let { getMimeType(it) }
        return mimeType?.startsWith("image/") == true
    }

    override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
        when {
            url.startsWith("tel:") -> handlePhoneNumber(url, view.context)
            url.startsWith("mailto:") -> handleEmail(url, view.context)
            url.startsWith("sms:") -> handleSms(url, view.context)
            url.startsWith("https://www.icliniq.com") ||
                url.startsWith("https://teams.microsoft.com") ||
                url.startsWith("https://maps.google.com") ->
                handleExternalUrl(url, context)
            else -> loadUrl(view, url)
        }
        return true
    }

    private fun getMimeType(url: String): String {
        val fileExtension = url.substringAfterLast('.', "")
        return when (fileExtension) {
            "js" -> "text/javascript"
            "css" -> "text/css"
            "png" -> "image/png"
            "jpg", "jpeg" -> "image/jpeg"
            "gif" -> "image/gif"
            "svg" -> "image/svg+xml"
            ".ttf" -> "application/x-font-ttf"
            ".otf" -> "application/x-font-opentype"
            ".woff2" -> "application/x-font-woff2"
            else -> "application/octet-stream"
        }
    }

    override fun onPageStarted(view: WebView, url: String?, favicon: Bitmap?) {
        super.onPageStarted(view, url, favicon)
        view.evaluateJavascript(getLocalStorageDeviceId(view.context),null)
    }

    override fun onPageFinished(view: WebView, url: String) {
        view.evaluateJavascript(getLocalStorageDeviceId(view.context), null)
    }

    override fun onReceivedError(
        view: WebView,
        errorCode: Int,
        description: String?,
        failingUrl: String?,
    ) {
        if (context is MainActivity) {
            context.handleError()
        }
    }
}
