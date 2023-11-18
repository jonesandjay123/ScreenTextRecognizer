package com.jonesandjay123.screentextrecognizer

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.graphics.PixelFormat
import android.view.WindowManager
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout

class OverlayService : Service() {
    private lateinit var windowManager: WindowManager
    private lateinit var overlayView: View

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        windowManager = getSystemService(WINDOW_SERVICE) as WindowManager

        // Inflate the overlay layout
        overlayView = LayoutInflater.from(this).inflate(R.layout.overlay_layout, null)

        // Define layout parameters for the overlay
        val params = WindowManager.LayoutParams(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
            PixelFormat.TRANSLUCENT
        )

        // Specify the overlay position
        params.gravity = Gravity.TOP or Gravity.START
        params.x = 0
        params.y = 100

        // Add the overlay view to the window
        windowManager.addView(overlayView, params)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (overlayView != null) {
            windowManager.removeView(overlayView)
        }
    }
}
