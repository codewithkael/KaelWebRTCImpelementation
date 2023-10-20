package com.codewithkael.kaelwebrtcimplementation

import android.app.Application
import android.widget.FrameLayout
import androidx.lifecycle.AndroidViewModel
import com.codewithkael.androidwebrtc.WebrtcClient
import com.codewithkael.androidwebrtc.utils.IceServer
import com.codewithkael.androidwebrtc.webrtc.WebRTCPacketListener
import com.codewithkael.webrtcprojectforrecord.models.MessageModel
import com.codewithkael.webrtcprojectforrecord.utils.NewMessageInterface

class MainViewModel(private val application: Application)
    : AndroidViewModel(application), WebRTCPacketListener, NewMessageInterface {

    private lateinit var webrtcClient:WebrtcClient
    private lateinit var socketRepository: SocketRepository
    fun init(){
        socketRepository = SocketRepository(this)
         webrtcClient = WebrtcClient(application,
            listOf(
                IceServer(
                    "turn:a.relay.metered.ca:443?transport=tcp",
                    username = "83eebabf8b4cce9d5dbcb649",
                    password = "2D7JvfkOQtBdYW3R"
                )
            ) , this@MainViewModel
        )
    }
    fun attachViewGroup(frameLayout: FrameLayout){
        webrtcClient.attachViewGroup(frameLayout)

    }

    override fun onCleared() {
        super.onCleared()
        webrtcClient.onDestroy()
    }

    override fun onPacketGenerated(packet: String) {

    }

    override fun onNewMessage(message: MessageModel) {

    }
}