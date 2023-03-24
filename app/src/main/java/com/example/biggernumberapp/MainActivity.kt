package com.example.biggernumberapp

import android.graphics.Color
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import java.util.*


class MainActivity : AppCompatActivity() {

    private lateinit var leftbutton: Button
    private lateinit var rightbutton: Button
    private lateinit var background: ConstraintLayout
    private lateinit var scorecounter: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        leftbutton = findViewById(R.id.btnLeft)
        rightbutton = findViewById(R.id.btnRight)
        background = findViewById(R.id.backgroundView)
        scorecounter = findViewById(R.id.Scorecounter)
        assignNumbersToButtons()
        leftbutton.setOnClickListener {
            checkAnswer(true)
            assignNumbersToButtons()
        }

        rightbutton.setOnClickListener {
            checkAnswer(false)
            assignNumbersToButtons()
        }

    }



    private fun assignNumbersToButtons() {
        val r = Random()
        val num1 = r.nextInt(10)
        var num2 = num1
        while (num2 == num1) {
            num2 = r.nextInt(10)
        }
        leftbutton.text = "$num1"
        rightbutton.text = "$num2"
    }


    private fun checkAnswer(isLeftButtonSelected: Boolean) {
        val n1 = leftbutton.text.toString().toInt()
        val n2 = rightbutton.text.toString().toInt()
        val isAnswerCorrect = if (isLeftButtonSelected) n1 > n2 else n2 > n1
        val toastMessage: String
        val backgroundColor: Int
        val SCORE_KEY = "score"

        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this /* or context */)


        if (isAnswerCorrect) {
            toastMessage = "Correct!!"
            backgroundColor = Color.GREEN
            var score = sharedPreferences.getInt(SCORE_KEY, 0)

            score++
            sharedPreferences.edit().putInt(SCORE_KEY, score).apply()
            scorecounter.text = "$score"



        } else {
            toastMessage = "Wrong"
            backgroundColor = Color.RED
            var score = sharedPreferences.getInt(SCORE_KEY, 0)

            score--
            sharedPreferences.edit().putInt(SCORE_KEY, score).apply()
            scorecounter.text = "$score"
        }


        Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT).show()
        background.setBackgroundColor(backgroundColor)

    }



}






