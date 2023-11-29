package com.example.btl_mobile_spotify.utils

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.acrcloud.rec.ACRCloudClient
import com.acrcloud.rec.ACRCloudConfig
import com.acrcloud.rec.ACRCloudResult
import com.acrcloud.rec.IACRCloudListener


class MusicRecognition(val context: Context) : IACRCloudListener {

    private var mProcessing = false
    private var mAutoRecognizing = false
    private var initState = false

    private var startTime: Long = 0
    private val stopTime: Long = 0

    private var mConfig: ACRCloudConfig = ACRCloudConfig()
    private var mClient: ACRCloudClient = ACRCloudClient()

    fun recognize() {
        mConfig.acrcloudListener = this
        mConfig.context = context
        // Please create project in "http://console.acrcloud.cn/service/avr".
        mConfig.host = "identify-ap-southeast-1.acrcloud.com"
        mConfig.accessKey = "3ab93f2a7ca6e78276339fcaf6425264"
        mConfig.accessSecret = "BbcrrjDbcykQausioheyxT5sRChM6cIXwHu3oD0s"

        // If you do not need volume callback, you set it false.
        mConfig.recorderConfig.isVolumeCallback = false

        mClient = ACRCloudClient()
        initState = mClient.initWithConfig(mConfig)

        start()

        //ACRCloudLogger.setLog(true)
    }

    private fun start() {
        if (!initState) {
            Toast.makeText(context, "init error", Toast.LENGTH_SHORT).show()
            cancel()
            return
        }

        if (!mProcessing) {
            mProcessing = true
            if (!mClient.startRecognize()) {
                mProcessing = false
            }
            startTime = System.currentTimeMillis()
        }
    }

    private fun cancel() {
        if (mProcessing) {
            mClient.cancel()
            stop()
        }

        mProcessing = false
    }

    override fun onResult(results: ACRCloudResult?) {
        val result = results?.result

        startTime = System.currentTimeMillis()

        //TODO: Show result
        Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
        Log.d("Result", result.toString())


        cancel()
    }

    override fun onVolumeChanged(curVolume: Double) {}

    private fun stop() {
        mClient.release()
        initState = false
    }
}
