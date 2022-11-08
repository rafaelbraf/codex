package com.example.codextesteapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class Question1Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question1)

        val buttonReady: Button = findViewById(R.id.buttonTransformCNPJ)
        buttonReady.setOnClickListener {

            val editTextCnpj: EditText = findViewById(R.id.editTextCnpj)
            var textViewCnpjFormatted: TextView = findViewById(R.id.textViewCnpjFormatted)
            var textViewCnpjNumbersOnly: TextView = findViewById(R.id.textViewCnpjNumbersOnly)
            var textViewErrorMessage: TextView = findViewById(R.id.textViewErrorMessage)

            if (!editTextCnpj.text.isNullOrEmpty()) {

                val cnpj = editTextCnpj.text.toString()
                textViewCnpjFormatted.text = ""
                textViewCnpjNumbersOnly.text = ""
                textViewErrorMessage.text = ""

                if (cnpj.equals("00000000000000") ||
                    cnpj.equals("11111111111111") ||
                    cnpj.equals("22222222222222") ||
                    cnpj.equals("33333333333333") ||
                    cnpj.equals("44444444444444") ||
                    cnpj.equals("55555555555555") ||
                    cnpj.equals("66666666666666") ||
                    cnpj.equals("77777777777777") ||
                    cnpj.equals("88888888888888") ||
                    cnpj.equals("99999999999999") ||
                    cnpj.equals("00.000.000/0000-00") ||
                    cnpj.equals("11.111.111/1111-11") ||
                    cnpj.equals("22.222.222/2222-22") ||
                    cnpj.equals("33.333.333/3333-33") ||
                    cnpj.equals("44.444.444/4444-44") ||
                    cnpj.equals("55.555.555/5555-55") ||
                    cnpj.equals("66.666.666/6666-66") ||
                    cnpj.equals("77.777.777/7777-77") ||
                    cnpj.equals("88.888.888/8888-88") ||
                    cnpj.equals("99.999.999/9999-99") ) {
                    textViewErrorMessage.text = "Type a valid CNPJ"
                } else {

                    var reg = Regex("[0-9./-]+")
                    var resultado = cnpj.matches(reg)
                    Log.i("teste", resultado.toString())
                    if (resultado) {

                        if (cnpj.length == 14 && (!cnpj.contains(".") && !cnpj.contains("/") && !cnpj.contains("-"))) {

                            val cnpjFormatted = cnpj.substring(0, 2) + "." + cnpj.substring(2, 5) + "." + cnpj.substring(5, 8) + "/" + cnpj.substring(8, 12) + "-" + cnpj.substring(12, 14)
                            textViewCnpjFormatted.text = "CNPJ Formatted: $cnpjFormatted"
                            textViewCnpjNumbersOnly.text = "CNPJ Numbers Only: $cnpj"

                        } else if (cnpj.length == 18) {

                            val cnpjNumbersOnly = cnpj.substring(0,2) + cnpj.substring(3, 6) + cnpj.substring(7, 10) + cnpj.substring(11, 15) + cnpj.substring(16, 18)
                            textViewCnpjFormatted.text = "CNPJ Formatted: $cnpj"
                            textViewCnpjNumbersOnly.text = "CNPJ Numbers Only: $cnpjNumbersOnly"

                        } else {
                            textViewErrorMessage.text = "Type a valid CNPJ"
                        }

                    } else {
                        textViewErrorMessage.text = "Type a valid CNPJ"
                    }

                }

                editTextCnpj.text.clear()

            }

        }

    }
}