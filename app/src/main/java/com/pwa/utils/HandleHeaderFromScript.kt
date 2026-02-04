package com.pwa.utils
import android.content.Context
import org.json.JSONObject

class HandleHeaderFromScript {

    fun handleScriptData(
        context: Context,
        data: String,
    ): Pair<String, String> {
        val jsonData = JSONObject(data)
        val action = jsonData.getString("action")
        val otherValue = jsonData.optString("payload", "")
        return Pair(action, otherValue)
    }
}
