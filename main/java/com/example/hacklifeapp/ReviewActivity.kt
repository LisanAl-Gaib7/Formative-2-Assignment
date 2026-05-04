package com.example.hacklifeapp

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

// Activity that displays a summary review of all questions and their correct answers
class ReviewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inflate the review screen layout
        setContentView(R.layout.activity_review)

        // Find the vertical LinearLayout that will hold the review items
        val layout = findViewById<LinearLayout>(R.id.reviewLayout)

        // All quiz questions to be reviewed
        val questions = arrayOf(
            "Sleeping less than 6 hours a night has no long term health effects",
            "Stretching before a workout prevents muscle injury",
            "Reading something once is enough to remember it",
            "Eating breakfast speeds up your metabolism for the day",
            "Blue light from screens directly causes permanent eye damage"
        )

        // Correct answers for each question (true = Hack, false = Myth)
        val answers = arrayOf(false, true, false, true, false)

        // Loop through each question and dynamically create a TextView for it
        for (i in questions.indices) {
            val tv = TextView(this)

            // Convert the boolean answer to a human-readable label
            val answerText = if (answers[i]) "Hack" else "Myth"

            // Set the question and its correct answer as the TextView content
            tv.text = "${questions[i]}\nAnswer: $answerText\n"

            // Add bottom padding to space out each review item
            tv.setPadding(0, 0, 0, 20)

            // Add the TextView to the layout
            layout.addView(tv)
        }
    }
}