package com.example.codextesteapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

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

                textViewCnpjFormatted.text = ""
                textViewCnpjNumbersOnly.text = ""
                textViewErrorMessage.text = ""

                var listCnpjs: MutableList<String> = validaCnpj(editTextCnpj.text.toString())
                if (listCnpjs.size == 2) {
                    textViewCnpjFormatted.text = "CNPJ Formatted: ${listCnpjs[0]}"
                    textViewCnpjNumbersOnly.text = "CNPJ Numbers Only: ${listCnpjs[1]}"
                } else {
                    textViewErrorMessage.text = "Type a valid CNPJ"
                }

                editTextCnpj.text.clear()

            }

        }

    }

    fun validaCnpj(cnpj: String): MutableList<String> {

        var cnpjFormatted: String = ""
        var cnpjNumbersOnly: String = ""
        var listCnpjs: MutableList<String> = arrayListOf()

        if (!verificaSeTodosOsDigitosSaoIguais(cnpj)) {

            var reg = Regex("[0-9./-]+")
            var result = cnpj.matches(reg)
            if (result) {

                if (cnpj.length == 14 && (!cnpj.contains(".") && !cnpj.contains("/") && !cnpj.contains("-"))) {
                    cnpjFormatted = cnpj.substring(0, 2) + "." + cnpj.substring(2, 5) + "." + cnpj.substring(5,8) + "/" + cnpj.substring(8, 12) + "-" + cnpj.substring(12, 14)
                    cnpjNumbersOnly = cnpj
                    var primeiroDigitoVerificador = calculaPrimeiroDigitoVerificador(cnpj)
                    var segundoDigitoVerificador = calculaSegundoDigitoVerificador(cnpj, primeiroDigitoVerificador)
                    if (primeiroDigitoVerificador == cnpj[12].digitToInt() && segundoDigitoVerificador == cnpj[13].digitToInt()) {
                        listCnpjs.add(cnpjFormatted)
                        listCnpjs.add(cnpjNumbersOnly)
                    }
                } else if (cnpj.length == 18 && (cnpj.contains(".") && cnpj.contains("/") && cnpj.contains("-"))) {
                    cnpjFormatted = cnpj
                    cnpjNumbersOnly = cnpj.substring(0, 2) + cnpj.substring(3, 6) + cnpj.substring(7,10) + cnpj.substring(11, 15) + cnpj.substring(16, 18)
                    var primeiroDigitoVerificador = calculaPrimeiroDigitoVerificador(cnpjNumbersOnly)
                    var segundoDigitoVerificador = calculaSegundoDigitoVerificador(cnpjNumbersOnly, primeiroDigitoVerificador)
                    if (primeiroDigitoVerificador == cnpj[16].digitToInt() && segundoDigitoVerificador == cnpj[17].digitToInt()) {
                        listCnpjs.add(cnpjFormatted)
                        listCnpjs.add(cnpjNumbersOnly)
                    }
                }

            }

        }
        return listCnpjs
    }

    fun verificaSeTodosOsDigitosSaoIguais(cnpj: String): Boolean {

        return cnpj.equals("00000000000000") ||
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
                cnpj.equals("99.999.999/9999-99")

    }

    fun calculaPrimeiroDigitoVerificador(cnpj: String): Int {

        var primeiroDigitoVerificador: Int = 0

        val resPrimeiroDigito =  5 * cnpj[0].digitToInt()
        val resSegundoDigito = 4 * cnpj[1].digitToInt()
        val resTerceiroDigito = 3 * cnpj[2].digitToInt()
        val resQuartoDigito = 2 * cnpj[3].digitToInt()
        val resQuintoDigito = 9 * cnpj[4].digitToInt()
        val resSextoDigito = 8 * cnpj[5].digitToInt()
        val resSetimoDigito = 7 * cnpj[6].digitToInt()
        val resOitavoDigito = 6 * cnpj[7].digitToInt()
        val resNonoDigito = 5 * cnpj[8].digitToInt()
        val resDecimoDigito = 4 * cnpj[9].digitToInt()
        val resDecimoPrimeiroDigito = 3 * cnpj[10].digitToInt()
        val resDecimoSegundoDigito = 2 * cnpj[11].digitToInt()

        val somaDeTodosOsResultados = resPrimeiroDigito + resSegundoDigito + resTerceiroDigito + resQuartoDigito + resQuintoDigito + resSextoDigito + resSetimoDigito + resOitavoDigito + resNonoDigito + resDecimoDigito + resDecimoPrimeiroDigito + resDecimoSegundoDigito
        val restoDaDivisaoDaSomaDosResultados = somaDeTodosOsResultados % 11
        if (restoDaDivisaoDaSomaDosResultados < 2) {
            primeiroDigitoVerificador = 0
        } else {
            primeiroDigitoVerificador = 11 - restoDaDivisaoDaSomaDosResultados
        }

        return primeiroDigitoVerificador

    }

    fun calculaSegundoDigitoVerificador(cnpj: String, primeiroDigitoVerificador: Int): Int {

        var segundoDigitoVerificador: Int

        val resPrimeiroDigito = 6 * cnpj[0].digitToInt()
        val resSegundoDigito = 5 * cnpj[1].digitToInt()
        val resTerceiroDigito = 4 * cnpj[2].digitToInt()
        val resQuartoDigito = 3 * cnpj[3].digitToInt()
        val resQuintoDigito = 2 * cnpj[4].digitToInt()
        val resSextoDigito = 9 * cnpj[5].digitToInt()
        val resSetimoDigito = 8 * cnpj[6].digitToInt()
        val resOitavoDigito = 7 * cnpj[7].digitToInt()
        val resNonoDigito = 6 * cnpj[8].digitToInt()
        val resDecimoDigito = 5 * cnpj[9].digitToInt()
        val resDecimoPrimeiroDigito = 4 * cnpj[10].digitToInt()
        val resDecimoSegundoDigito = 3 * cnpj[11].digitToInt()
        val resPrimeiroDigitoVerificador = 2 * primeiroDigitoVerificador

        val somaDeTodosOsResultados = resPrimeiroDigito + resSegundoDigito + resTerceiroDigito + resQuartoDigito + resQuintoDigito + resSextoDigito + resSetimoDigito + resOitavoDigito + resNonoDigito + resDecimoDigito + resDecimoPrimeiroDigito + resDecimoSegundoDigito + resPrimeiroDigitoVerificador
        val restoDaDivisaoDaSomaDosResultados = somaDeTodosOsResultados % 11
        if (restoDaDivisaoDaSomaDosResultados < 2) {
            segundoDigitoVerificador = 0
        } else {
            segundoDigitoVerificador = 11 - restoDaDivisaoDaSomaDosResultados
        }

        return segundoDigitoVerificador

    }

}