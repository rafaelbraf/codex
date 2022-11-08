package com.example.codextesteapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.util.Log
import android.widget.Button
import android.widget.TextView
import okhttp3.*
import org.json.JSONObject
import org.json.JSONTokener
import java.io.IOException
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class Question5And6Activity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question5_and_6)



        val buttonConsultar: Button = findViewById(R.id.buttonConsultar)
        buttonConsultar.setOnClickListener {

            getCurrentDateTime()

        }

    }

    fun getCurrentDateTime() {
        val client = OkHttpClient()
        try {
            val url = "http://worldclockapi.com/api/json/utc/now"
            val request = Request.Builder().url(url).build()
            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {}
                override fun onResponse(call: Call, response: Response) {
                    createThread(response)
                }
            })
        } catch (err: Error) {
            println("Erro na requisição")
        }
    }

    fun createThread(response: Response) {
        val thread = Thread(
            Runnable {
                runOnUiThread {
                    val jsonObject = JSONTokener(response.body!!.string()).nextValue() as JSONObject
                    var textViewCurrentDateTime: TextView = findViewById(R.id.textViewCurrentDateTime)
                    textViewCurrentDateTime.text = jsonObject.getString("currentDateTime")
                }
            }
        )
        thread.start()
    }

}