package com.codewithkael.kaelwebrtcimpelementation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.codewithkael.androidwebrtc.WebrtcClient

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val webrtcClient = WebrtcClient()
        webrtcClient.test(this)
    }
}