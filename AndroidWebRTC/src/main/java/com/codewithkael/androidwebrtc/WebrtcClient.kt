package com.codewithkael.androidwebrtc

import android.content.Context
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.codewithkael.androidwebrtc.databinding.CallViewRootBinding
import com.codewithkael.androidwebrtc.utils.DataModel
import com.codewithkael.androidwebrtc.utils.DataModelType.Answer
import com.codewithkael.androidwebrtc.utils.DataModelType.EndCall
import com.codewithkael.androidwebrtc.utils.DataModelType.IceCandidates
import com.codewithkael.androidwebrtc.utils.DataModelType.Offer
import com.codewithkael.androidwebrtc.utils.DataModelType.StartCall
import com.codewithkael.androidwebrtc.utils.IceServer
import com.codewithkael.androidwebrtc.webrtc.RTCClient
import com.codewithkael.androidwebrtc.webrtc.WebRTCPacketListener
import com.google.gson.Gson
import org.webrtc.PeerConnection

class WebrtcClient(
    private var context: Context,
    private val iceServerList: List<IceServer>,
    listener: WebRTCPacketListener
) : RTCClient.Listener {

    private val gson = Gson()
    private var listener: WebRTCPacketListener? = listener

    private lateinit var callRootView: CallViewRootBinding

    //webrtc
    private var rtcClient: RTCClient? = null

    fun attachViewGroup(frameLayout: FrameLayout) {
        frameLayout.addView(callRootView.root)
    }

    init {
        initViews()
        initRepositories()
    }

    private fun initViews() {
        callRootView = CallViewRootBinding.inflate(
            LayoutInflater.from(context)
        )

    }

    private fun initRepositories() {
        rtcClient =
            RTCClient(iceServerList.map {
                PeerConnection.IceServer.builder(it.uri)
                    .setUsername(it.username)
                    .setPassword(it.password).createIceServer()
            }, context, gson)
        rtcClient?.listener = this
    }

    fun onDestroy() {
        rtcClient?.closeConnection()
        listener = null
    }

    fun feedPacket(packet: String) {
        val model: DataModel? = try {
            gson.fromJson(packet, DataModel::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
        model?.let { dataModel ->
            handleIncomingDataModel(dataModel)
        }
    }


    //todo handle here
    private fun handleIncomingDataModel(dataModel: DataModel) {
        when (dataModel.type) {
            StartCall -> {}
            Offer -> {}
            Answer -> {}
            IceCandidates -> {}
            EndCall -> {}
        }
    }

    override fun onTransferEventToSocket(data: DataModel) {
        listener?.onPacketGenerated(gson.toJson(data))
    }


}