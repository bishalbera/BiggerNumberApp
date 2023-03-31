package com.example.biggernumberapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


 class MainActivity : AppCompatActivity() {


     override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
         setContentView(R.layout.activity_main)
         val startButton: Button = findViewById(R.id.btnStart)
         val editName: EditText = findViewById(R.id.et_name)
         startButton.setOnClickListener {
             if (editName.text.isEmpty()) {
                 Toast.makeText(this, "Please enter your name",Toast.LENGTH_LONG).show()

             }else{
                 val intent = Intent(this, GuessNumberActivity::class.java)
                 intent.putExtra(Constants.USER_NAME, editName.text.toString())
                 startActivity(intent)
                 finish()
             }
         }


     }
 }








