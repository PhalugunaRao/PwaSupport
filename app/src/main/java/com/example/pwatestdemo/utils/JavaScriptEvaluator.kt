package com.ekincare.webscript

import android.os.Handler
import android.webkit.WebView
class JavaScriptEvaluator {
    companion object {
        fun evaluateJavascript(webView: WebView, javascriptCode: String, callback: ((String?) -> Unit)? = null) {
            val mainHandler = Handler(webView.context.mainLooper)
            mainHandler.post {
                try {
                    webView.evaluateJavascript(javascriptCode) { value ->
                        // Handle the result returned by JavaScript
                        callback?.invoke(value)
                    }
                } catch (e: Exception) {
                    // Handle the evaluation exception
                    callback?.invoke(null)
                }
            }
        }
    }
}
