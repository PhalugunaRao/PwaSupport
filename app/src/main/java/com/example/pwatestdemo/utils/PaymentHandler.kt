package com.example.pwatestdemo.utils

import android.app.Activity
import android.content.Context
import android.webkit.WebView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.razorpay.Checkout
import com.razorpay.PaymentData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject

class PaymentHandler(private val webView: WebView) {

    fun handlePaymentSuccess(razorpayPaymentID: String?, paymentData: PaymentData?) {
        // Handle payment success logic
        val javascriptCode: String = JavaScriptCodeBuilder.buildCode2(
            JavaScriptCodeBuilder.PaymentSucessJsonData(razorpayPaymentID, paymentData).toString(),
        )
        JavaScriptEvaluator.evaluateJavascript(webView, javascriptCode) {
        }
    }

    fun handlePaymentFailure(code: Int) {
        val javascriptFailed: String = JavaScriptCodeBuilder.buildCode2(
            JavaScriptCodeBuilder.PaymentFailedJsonData(code).toString(),
        )
        JavaScriptEvaluator.evaluateJavascript(webView, javascriptFailed) {
        }
    }

    fun startPayment(context: Context, paymentData: PaymentResponseData) {
        val isPluxeePaymentAllowed = paymentData.pluxee_payment ?: false
        val lifecycleOwner = context as? LifecycleOwner
        lifecycleOwner?.lifecycleScope?.launch {
            withContext(Dispatchers.Main) {
                Checkout.preload(context)
            }
        }

        val checkout = Checkout()
        val options = createCommonOptions(paymentData)

        if (isPluxeePaymentAllowed) {
            val config = createPluxeeConfig()
            options.put("config", config)
        }

        try {
            checkout.setKeyID(PwaKeys.PAYMENT_KEY);
            checkout.open(context as Activity, options)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun createCommonOptions(paymentData: PaymentResponseData): JSONObject {
        return JSONObject().apply {
            put("name", "ekincare")
            put("description", paymentData.description)
            put("image", PwaKeys.WALLET_LOGO)
            put("currency", "INR")
            put("amount", paymentData.amount)
            put("order_id", paymentData.order_id)
            put("prefill", JSONObject().apply {
                put("contact", paymentData.prefill?.contact ?: "")
                put("email", paymentData.prefill?.email ?: "")
            })
        }
    }

    private fun createPluxeeConfig(): JSONObject {
        val idfbBlock = JSONObject().apply {
            put("name", "Pay Using Pluxee Card")
            put("instruments", JSONArray().put(JSONObject().apply {
                put("method", "card")
                put("iins", JSONArray().put("607577"))
            }))
        }
        return JSONObject().apply {
            put("display", JSONObject().apply {
                put("blocks", JSONObject().put("idfb", idfbBlock))
                put("hide", JSONArray().apply {
                    put(JSONObject().put("method", "upi"))
                    put(JSONObject().put("method", "card"))
                    put(JSONObject().put("method", "netbanking"))
                    put(JSONObject().put("method", "wallet"))
                    put(JSONObject().put("method", "cardless_emi"))
                })
                put("sequence", JSONArray().put("block.idfb").put("block.other"))
                put("preferences", JSONObject().put("show_default_blocks", false))
            })
        }
    }

}
