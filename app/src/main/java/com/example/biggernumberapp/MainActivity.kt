package com.example.biggernumberapp

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
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

    @SuppressLint("SetTextI18n")
    private fun checkAnswer(isLeftButtonSelected: Boolean) {
        val n1 = leftbutton.text.toString().toInt()
        val n2 = rightbutton.text.toString().toInt()
        var isAnswerCorrect = if (isLeftButtonSelected) n1 > n2 else n2 > n1
        val toastMessage: String
        val backgroundColor: Int

        var finalscore =  0

        if (isAnswerCorrect) {
            toastMessage = "Correct!!"
            backgroundColor = Color.GREEN

            scorecounter.text = "score${finalscore.inc()}"
        } else {
            toastMessage = "Wrong"
            backgroundColor = Color.RED
            scorecounter.text = "score:${finalscore.dec()}"
        }

        Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT).show()
        background.setBackgroundColor(backgroundColor)

    }


}






