package com.example.conversor_moedas

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->

            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val spinnerIn: Spinner = findViewById(R.id.spinner_in)
        val spinnerOut: Spinner = findViewById(R.id.spinner_out)


        val edtIn: EditText = findViewById(R.id.edt_currency_value_input)
        val edtOut: EditText = findViewById(R.id.edt_currency_value_output)



        val btnClean: Button = findViewById(R.id.btn_clean)
        val btnSwap: ImageButton = findViewById(R.id.btn_swap)


        btnClean.setOnClickListener {
            edtIn.text.clear()
            edtOut.text.clear()
        }

        btnSwap.setOnClickListener {
            val selectedCurrencyInPosition = spinnerIn.selectedItemPosition
            val selectedCurrencyOutPosition = spinnerOut.selectedItemPosition

            spinnerIn.setSelection(selectedCurrencyOutPosition)
            spinnerOut.setSelection(selectedCurrencyInPosition)


        }


        val adapter = CurrencySpinnerAdapter(this, currency)

        edtOut.isFocusable = false

        spinnerIn.adapter = adapter
        spinnerOut.adapter = adapter

        edtIn.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                convertAndDisplay()
            }

            override fun afterTextChanged(s: Editable?) {}
        })


    }

    private fun convertAndDisplay() {

        val edtIn: EditText = findViewById(R.id.edt_currency_value_input)
        val edtOut: EditText = findViewById(R.id.edt_currency_value_output)
        val spinnerIn: Spinner = findViewById(R.id.spinner_in)
        val spinnerOut: Spinner = findViewById(R.id.spinner_out)

        val inputValueStr = edtIn.text.toString()

        if (inputValueStr.isNotEmpty()) {
            val inputValue = inputValueStr.toDoubleOrNull()
            if (inputValue != null) {

                val selectCurrencyIn = spinnerIn.selectedItem as Currency
                val selectCurrencyOut = spinnerOut.selectedItem as Currency

                val convertedValue =
                    convertCurrency(inputValue, selectCurrencyIn, selectCurrencyOut)

                edtOut.setText(String.format("%.2f", convertedValue))






            }
        } else {
            edtOut.text.clear()
        }


    }

    private fun convertCurrency(value: Double, from: Currency, to: Currency): Double {

        val fromValue = from.value.toDouble()
        val toValue = to.value.toDouble()

        return value * (toValue / fromValue)
    }


}

val currency = listOf(
    Currency(
        currency = "BRL",
        value = "6.10",
        icon = R.drawable.ic_brl

    ),
    Currency(
        currency = "EUR",
        value = "1.00",
        icon = R.drawable.ic_euro
    ),
    Currency(
        currency = "USD",
        value = "1.10",
        icon = R.drawable.ic_us
    ),
    Currency(
        currency = "GBP",
        value = "0.85",
        icon = R.drawable.ic_libra
    )
)