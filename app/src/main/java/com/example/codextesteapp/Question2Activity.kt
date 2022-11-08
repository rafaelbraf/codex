package com.example.codextesteapp

import android.graphics.Rect
import android.graphics.Rect.intersects
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView

class Question2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question2)

        val textViewIntersectAAndB: TextView = findViewById(R.id.textViewIntersectionAAndB)
        val textViewIntersectAAndC: TextView = findViewById(R.id.textViewIntersectionAAndC)
        val textViewIntersectBAndC: TextView = findViewById(R.id.textViewIntersectionBAndC)
        val buttonVerifyIntersection: Button = findViewById(R.id.buttonVerifiyIntersection)

        val rectangleA: Rect = Rect(3, 11, 11,5)
        val rectangleB: Rect = Rect(7, 7, 13, 2)
        val rectangleC: Rect = Rect(11, 13, 15, 11)

        buttonVerifyIntersection.setOnClickListener {

            textViewIntersectAAndB.text = "intersects(A, B) => ${intersects(rectangleA, rectangleB)}"
            textViewIntersectAAndC.text = "intersects(A, C) => ${intersects(rectangleA, rectangleC)}"
            textViewIntersectBAndC.text = "intersects(B, C) => ${intersects(rectangleB, rectangleC)}"
            Log.i("teste", "${intersects(rectangleA, rectangleB)}")
        }

    }

//    fun intersects(rect1: Rect, rect2: Rect): Boolean {
//        return rect1.intersect(rect2)
//    }

}