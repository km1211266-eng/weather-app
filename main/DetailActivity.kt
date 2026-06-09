package com.example.weatherapp

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {

    private val TAG = "DetailActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        Log.d(TAG, "DetailActivity loaded")

        val txtDetails = findViewById<TextView>(R.id.txtDetails)
        val btnBack    = findViewById<Button>(R.id.btnBack)

        // Retrieve parallel arrays passed from MainActivity via Intent
        val days       = intent.getStringArrayListExtra("days")
        val conditions = intent.getStringArrayListExtra("conditions")
        val minTemps   = intent.getDoubleArrayExtra("minTemps")
        val maxTemps   = intent.getDoubleArrayExtra("maxTemps")

        Log.i(TAG, "Received ${days?.size ?: 0} day(s) from MainActivity")

        // Build the display string by looping through all arrays by index
        val sb = StringBuilder()
        sb.appendLine("╔══════════════════════════════╗")
        sb.appendLine("       WEEKLY WEATHER REPORT    ")
        sb.appendLine("╚══════════════════════════════╝\n")

        if (days != null && minTemps != null && maxTemps != null && conditions != null) {
            for (i in days.indices) {
                sb.appendLine("📅 Day        : ${days[i]}")
                sb.appendLine("🌡 Min Temp   : ${minTemps[i]}°C")
                sb.appendLine("🌡 Max Temp   : ${maxTemps[i]}°C")
                sb.appendLine("🌤 Condition  : ${conditions[i]}")
                sb.appendLine("─────────────────────────────")
                Log.d(TAG, "Displaying day ${i + 1}: ${days[i]}")
            }
        } else {
            sb.appendLine("No data to display.")
            Log.w(TAG, "One or more arrays were null on arrival")
        }

        txtDetails.text = sb.toString()

        // Back button — closes DetailActivity and returns to MainActivity
        btnBack.setOnClickListener {
            Log.i(TAG, "Back button pressed — returning to MainActivity")
            finish()
        }
    }
}
