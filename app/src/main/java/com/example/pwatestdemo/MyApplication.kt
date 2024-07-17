package com.example.pwatestdemo

import android.app.Application
import android.content.res.Resources

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        mInstance = this
        res = resources
    }

    companion object {
        private var mInstance: MyApplication? = null
        private var res: Resources? = null
        @JvmStatic
        @get:Synchronized
        val instance: MyApplication?
            get() {
                if (mInstance == null) {
                    mInstance = MyApplication()
                }
                return mInstance
            }

    }
}