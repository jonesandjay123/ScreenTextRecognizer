package com.jonesandjay123.screentextrecognizer

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.graphics.PixelFormat
import android.view.WindowManager
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View

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
        val displayMetrics = resources.displayMetrics
        val width = (displayMetrics.widthPixels * 0.5).toInt()
        val height = (displayMetrics.heightPixels * 0.5).toInt()

        val params = WindowManager.LayoutParams(
            width,
            height,
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
            PixelFormat.TRANSLUCENT
        )

        params.gravity = Gravity.CENTER
        windowManager.addView(overlayView, params)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (this::overlayView.isInitialized) {
            windowManager.removeView(overlayView)
        }
    }
}
