package com.codewithkael.androidwebrtc.utils

import android.content.Context
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import com.airbnb.paris.extensions.layoutHeight
import com.airbnb.paris.extensions.layoutWidth
import com.airbnb.paris.extensions.style
import org.webrtc.SurfaceViewRenderer

object ViewCreator {
    fun createRemoteSurfaceRenderer(context: Context)
            : SurfaceViewRenderer {
        return SurfaceViewRenderer(context).apply {
            style {
                layoutWidth(MATCH_PARENT)
                layoutHeight(MATCH_PARENT)
                tag = "RemoteView"
            }
        }
    }
}