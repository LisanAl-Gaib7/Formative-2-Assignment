package com.example.hacklifeapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

// Main entry point activity of the app
class MainActivity : AppCompatActivity() {

    // Called when the activity is first created
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set the UI layout for this activity
        setContentView(R.layout.activity_main)

        // Apply window insets so content isn't hidden behind system bars (status/nav bar)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            // Get the insets for system bars (top, bottom, left, right)
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())

            // Pad the view so content clears the system bars
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Find the start button in the layout by its ID
        val startButton = findViewById<Button>(R.id.startButton)

        // Set a click listener on the start button
        startButton.setOnClickListener {
            // Launch the QuizActivity when the button is tapped
            startActivity(Intent(this, QuizActivity::class.java))
        }
    }
}