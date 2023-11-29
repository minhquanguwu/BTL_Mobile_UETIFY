package com.example.btl_mobile_spotify.utils

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.acrcloud.rec.ACRCloudClient
import com.acrcloud.rec.ACRCloudConfig
import com.acrcloud.rec.ACRCloudResult
import com.acrcloud.rec.IACRCloudListener
import org.json.JSONException
import org.json.JSONObject


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
        val song = result?.let { handleResult(it) }
        if (result != null) {
            Log.d("Result", result)
        }
        if (song != null) {
            Log.d("Song", song)
        }

        cancel()
    }

    private fun handleResult(acrResult: String): String {
        var res = ""
        try {
            val json = JSONObject(acrResult)
            val status: JSONObject = json.getJSONObject("status")
            val code = status.getInt("code")
            if (code == 0) {
                val metadata: JSONObject = json.getJSONObject("metadata")
                if (metadata.has("music")) {
                    val musics = metadata.getJSONArray("music")
                    val tt = musics[0] as JSONObject
                    val title = tt.getString("title")
                    val artistt = tt.getJSONArray("artists")
                    val art = artistt[0] as JSONObject
                    val artist = art.getString("name")

                    res = "$title ($artist)"
                }
            } else {
                // TODO: Handle error
                res = acrResult
            }
        } catch (e: JSONException) {
            res = "Error parsing metadata"
            Log.e("ACR", "JSONException", e)
        }

        return res
    }

    override fun onVolumeChanged(curVolume: Double) {}

    private fun stop() {
        mClient.release()
        initState = false
    }
}
