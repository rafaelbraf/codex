package com.example.codextesteapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonQuestion1: Button = findViewById(R.id.buttonQuestion1)
        buttonQuestion1.setOnClickListener {
            val intent = Intent(this, Question1Activity::class.java)
            startActivity(intent)
        }

        val buttonQuestion2: Button = findViewById(R.id.buttonQuestion2)
        buttonQuestion2.setOnClickListener {
            val intent = Intent(this, Question2Activity::class.java)
            startActivity(intent)
        }

        val buttonQuestion4: Button = findViewById(R.id.buttonQuestion4)
        buttonQuestion4.setOnClickListener {
            val intent = Intent(this, Question4Activity::class.java)
            startActivity(intent)
        }

        val buttonQuestion5And6: Button = findViewById(R.id.buttonQuestion5And6)
        buttonQuestion5And6.setOnClickListener {
            val intent = Intent(this, Question5And6Activity::class.java)
            startActivity(intent)
        }

        val buttonQuestion7: Button = findViewById(R.id.buttonQuestion7)
        buttonQuestion7.setOnClickListener {
            val intent = Intent(this, Question7Activity::class.java)
            startActivity(intent)
        }

    }
}