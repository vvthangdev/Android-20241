package com.example.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CurrencyActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var currencyConverter: CurrencyConverter
    private lateinit var inputAmount: EditText
    private lateinit var spinnerFrom: Spinner
    private lateinit var spinnerTo: Spinner
    private lateinit var resultTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_currency)

        currencyConverter = CurrencyConverter()

        inputAmount = findViewById(R.id.edit_text_currency_input)
        spinnerFrom = findViewById(R.id.spinner_currency_from)
        spinnerTo = findViewById(R.id.spinner_currency_to)
//        resultTextView = findViewById(R.id.text_content_currency)

//        val buttonConvert = findViewById<Button>(R.id.button_convert)
//        buttonConvert.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
//        if (view?.id == R.id.button_convert) {
//            performConversion()
//        }
    }

    private fun performConversion() {
        val amount = inputAmount.text.toString().toDoubleOrNull()
        if (amount == null || amount <= 0) {
            resultTextView.text = "Số tiền không hợp lệ"
            return
        }

        val fromCurrency = spinnerFrom.selectedItem.toString()
        val toCurrency = spinnerTo.selectedItem.toString()

        val result = currencyConverter.convertCurrency(amount, fromCurrency, toCurrency)
        resultTextView.text = "Kết quả: $result $toCurrency"
    }
}
