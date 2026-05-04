package com.example.hacklifeapp

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class QuizActivity : AppCompatActivity() {

    // Tracks the current question index
    var index = 0
    // Tracks the user's score
    var score = 0

    // UI elements declared here so they're accessible across functions
    lateinit var questionText: TextView
    lateinit var feedbackText: TextView

    // Array of quiz questions displayed to the user
    val questions = arrayOf(
        "Turning your phone off immediately after water exposure helps prevent damage",
        "Learning 5 keyboard shortcuts can save over an hour of work per week",
        "Drinking 8 glasses of water a day is scientifically required for everyone",
        "Reviewing notes within 24 hours dramatically improves retention",
        "Letting your battery drain to 0% before charging extends its life"
    )

    // Correct answers corresponding to each question (true = Hack, false = Myth)
    val answers = arrayOf(false, true, false, true, false)

    // Explanations shown after the user answers each question
    val explanations = arrayOf(
        "Hack: Powering off stops electricity flowing through wet circuits, reducing the risk of a short circuit.",
        "Hack: Even basic shortcuts like copy, paste and window switching compound into significant time savings daily.",
        "Myth: Water needs vary by body size, climate and activity level — 8 glasses is a guideline, not a universal rule.",
        "Hack: The brain consolidates memory during sleep, so reviewing notes before then locks information in far better.",
        "Myth: Modern lithium-ion batteries degrade faster when fully drained — keeping charge between 20–80% is healthier."
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inflate the quiz layout
        setContentView(R.layout.activity_quiz)

        // Bind UI elements to their layout views
        questionText = findViewById(R.id.questionText)
        feedbackText = findViewById(R.id.feedbackText)

        // Bind the three buttons
        val hackButton = findViewById<Button>(R.id.hackButton)
        val mythButton = findViewById<Button>(R.id.mythButton)
        val nextButton = findViewById<Button>(R.id.nextButton)

        // Display the first question on launch
        loadQuestion()

        // "Hack" button submits true as the user's answer
        hackButton.setOnClickListener { checkAnswer(true) }
        // "Myth" button submits false as the user's answer
        mythButton.setOnClickListener { checkAnswer(false) }

        nextButton.setOnClickListener {
            // Move to the next question
            index++
            if (index < questions.size) {
                // More questions remain — load the next one and clear feedback
                loadQuestion()
                feedbackText.text = ""
            } else {
                // All questions answered — navigate to the score screen
                val intent = Intent(this, ScoreActivity::class.java)
                intent.putExtra("score", score)         // Pass the user's score
                intent.putExtra("total", questions.size) // Pass the total question count
                startActivity(intent)
                finish() // Remove QuizActivity from the back stack
            }
        }
    }

    // Displays the current question in the TextView
    fun loadQuestion() {
        questionText.text = questions[index]
    }

    // Compares the user's answer to the correct answer and updates feedback
    fun checkAnswer(userAnswer: Boolean) {
        if (userAnswer == answers[index]) {
            // Correct answer — show success message and increment score
            feedbackText.text = "Correct! 🎉\n${explanations[index]}"
            score++
        } else {
            // Wrong answer — show failure message with explanation
            feedbackText.text = "Wrong! ❌\n${explanations[index]}"
        }
    }
}