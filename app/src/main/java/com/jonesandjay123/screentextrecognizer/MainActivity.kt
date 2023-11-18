package com.jonesandjay123.screentextrecognizer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {

    private val overlayPermissionActivityResultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (Settings.canDrawOverlays(this)) {
            startService(Intent(this, OverlayService::class.java))
        } else {
            Toast.makeText(this, "Overlay permission is not granted", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("!!!==========MainActivity", "Service is starting. =========")
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

        // 檢查懸浮視窗權限
        if (!Settings.canDrawOverlays(this)) {
            val intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:$packageName"))
            overlayPermissionActivityResultLauncher.launch(intent)
        } else {
            startService(Intent(this, OverlayService::class.java))
        }
    }
}