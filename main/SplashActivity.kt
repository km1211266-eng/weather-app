package com.example.weatherapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    private val TAG = "SplashActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Log.d(TAG, "Splash screen loaded")

        val btnStart = findViewById<Button>(R.id.btnStart)
        val btnExit  = findViewById<Button>(R.id.btnExit)

        // Navigate to Main Screen
        btnStart.setOnClickListener {
            Log.i(TAG, "User tapped Start — navigating to MainActivity")
            startActivity(Intent(this, MainActivity::class.java))
        }

        // Exit the app completely
        btnExit.setOnClickListener {
            Log.i(TAG, "User tapped Exit — closing app")
            finishAffinity()
        }
    }
}
