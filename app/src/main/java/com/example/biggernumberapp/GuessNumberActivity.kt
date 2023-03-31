package com.example.biggernumberapp

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import java.util.*

object Constants{
    const val USER_NAME : String = "user_name"
const val SCORE : String = "score"}

class GuessNumberActivity : AppCompatActivity() {
    private lateinit var leftButton: Button
    private lateinit var rightButton: Button
    private lateinit var background: ConstraintLayout
    private lateinit var submitBtn: Button
    private lateinit var mUserName : String
    private var score : Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guess_number)
        leftButton = findViewById(R.id.btnLeft)
        rightButton = findViewById(R.id.btnRight)
        background = findViewById(R.id.backgroundView)
        submitBtn = findViewById(R.id.submit)
        mUserName= intent.getStringExtra(Constants.USER_NAME)!!

        assignNumbersToButton()
        leftButton.setOnClickListener {
            checkAnswer(true)
            assignNumbersToButton()
        }
        rightButton.setOnClickListener {

            checkAnswer(false)
            assignNumbersToButton()
        }
        submitBtn.setOnClickListener {
            val intent = Intent(this, ScoreCard::class.java)
            intent.putExtra(Constants.USER_NAME, mUserName)
            intent.putExtra(Constants.SCORE,score )
            startActivity(intent)
            finish()
        }
    }

    private fun assignNumbersToButton() {
        val r = Random()
        val num1 = r.nextInt(10)
        var num2 = num1
        while (num2 == num1) {
            num2 = r.nextInt(10)
        }
        leftButton.text = "$num1"
        rightButton.text = "$num2"

    }
    private fun checkAnswer(leftButtonSelected:Boolean) {
        val n1 = leftButton.text.toString().toInt()
        val n2 = rightButton.text.toString().toInt()
        val isAnswerCorrect =  if(leftButtonSelected) n1 > n2 else n2 > n1

        val backgroundColor: Int
        val toastMessage: String
        if (isAnswerCorrect) {
            backgroundColor = Color.GREEN
            toastMessage = "Correct!!"
              score++

        }else{
            backgroundColor = Color.RED
            toastMessage = "Oops you have selected wrong"
            score--
        }

        Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT).show()
        background.setBackgroundColor(backgroundColor)

    }

}