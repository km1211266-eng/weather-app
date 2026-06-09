package com.example.weatherapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    // Parallel arrays — each index represents one day's data
    private val days       = ArrayList<String>()
    private val minTemps   = ArrayList<Double>()
    private val maxTemps   = ArrayList<Double>()
    private val conditions = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG, "MainActivity loaded")

        // Link UI elements
        val edtDay       = findViewById<EditText>(R.id.edtDay)
        val edtMin       = findViewById<EditText>(R.id.edtMin)
        val edtMax       = findViewById<EditText>(R.id.edtMax)
        val edtCondition = findViewById<EditText>(R.id.edtCondition)
        val btnAdd       = findViewById<Button>(R.id.btnAdd)
        val btnAverage   = findViewById<Button>(R.id.btnAverage)
        val btnDetails   = findViewById<Button>(R.id.btnDetails)
        val btnClear     = findViewById<Button>(R.id.btnClear)
        val btnExit      = findViewById<Button>(R.id.btnExit)
        val txtAverage   = findViewById<TextView>(R.id.txtAverage)

        // ── ADD button ────────────────────────────────────────────
        // Validates all fields then stores data in the parallel arrays
        btnAdd.setOnClickListener {
            val day       = edtDay.text.toString().trim()
            val minText   = edtMin.text.toString().trim()
            val maxText   = edtMax.text.toString().trim()
            val condition = edtCondition.text.toString().trim()

            // Error handling: all fields must be filled
            when {
                day.isEmpty()       -> {
                    edtDay.error = "Please enter a day name"
                    Log.w(TAG, "Validation failed: day name empty")
                }
                minText.isEmpty()   -> {
                    edtMin.error = "Please enter a minimum temperature"
                    Log.w(TAG, "Validation failed: min temp empty")
                }
                maxText.isEmpty()   -> {
                    edtMax.error = "Please enter a maximum temperature"
                    Log.w(TAG, "Validation failed: max temp empty")
                }
                condition.isEmpty() -> {
                    edtCondition.error = "Please enter a weather condition"
                    Log.w(TAG, "Validation failed: condition empty")
                }
                else -> {
                    // Convert temperature strings to Doubles safely
                    val minTemp = minText.toDoubleOrNull()
                    val maxTemp = maxText.toDoubleOrNull()

                    when {
                        minTemp == null -> {
                            edtMin.error = "Enter a valid number"
                            Log.w(TAG, "Validation failed: min temp not a number")
                        }
                        maxTemp == null -> {
                            edtMax.error = "Enter a valid number"
                            Log.w(TAG, "Validation failed: max temp not a number")
                        }
                        minTemp > maxTemp -> {
                            edtMin.error = "Min temp cannot exceed max temp"
                            Log.w(TAG, "Validation failed: min > max")
                        }
                        else -> {
                            // All valid — add to parallel arrays at the same index
                            days.add(day)
                            minTemps.add(minTemp)
                            maxTemps.add(maxTemp)
                            conditions.add(condition)

                            Toast.makeText(this, "$day data added!", Toast.LENGTH_SHORT).show()
                            Log.i(TAG, "Day added: $day | min=$minTemp | max=$maxTemp | $condition")

                            // Clear fields ready for next entry
                            edtDay.text.clear()
                            edtMin.text.clear()
                            edtMax.text.clear()
                            edtCondition.text.clear()
                        }
                    }
                }
            }
        }

        // ── AVERAGE button ────────────────────────────────────────
        // Loops through maxTemps array and calculates the weekly average
        btnAverage.setOnClickListener {
            if (maxTemps.isEmpty()) {
                Toast.makeText(this, "No data entered yet", Toast.LENGTH_SHORT).show()
                Log.w(TAG, "Average requested but no data in arrays")
            } else {
                var total = 0.0

                // Loop through all max temperatures and sum them
                for (temp in maxTemps) {
                    total += temp
                }

                val average = total / maxTemps.size
                txtAverage.text = "Average Max Temp: ${"%.2f".format(average)}°C"
                Log.i(TAG, "Average calculated: ${"%.2f".format(average)}°C over ${maxTemps.size} day(s)")
            }
        }

        // ── DETAILS button ────────────────────────────────────────
        // Passes all four parallel arrays to DetailActivity
        btnDetails.setOnClickListener {
            if (days.isEmpty()) {
                Toast.makeText(this, "Add some data first!", Toast.LENGTH_SHORT).show()
                Log.w(TAG, "Navigation to details blocked: arrays empty")
            } else {
                Log.i(TAG, "Navigating to DetailActivity with ${days.size} day(s)")
                val intent = Intent(this, DetailActivity::class.java)
                intent.putStringArrayListExtra("days", days)
                intent.putStringArrayListExtra("conditions", conditions)
                intent.putExtra("minTemps", minTemps.toDoubleArray())
                intent.putExtra("maxTemps", maxTemps.toDoubleArray())
                startActivity(intent)
            }
        }

        // ── CLEAR button ──────────────────────────────────────────
        // Empties all four parallel arrays and resets the display
        btnClear.setOnClickListener {
            days.clear()
            minTemps.clear()
            maxTemps.clear()
            conditions.clear()
            txtAverage.text = "Average Max Temp: --"
            Toast.makeText(this, "All data cleared", Toast.LENGTH_SHORT).show()
            Log.i(TAG, "All arrays cleared by user")
        }

        // ── EXIT button ───────────────────────────────────────────
        btnExit.setOnClickListener {
            Log.i(TAG, "User exited app from MainActivity")
            finishAffinity()
        }
    }
}
