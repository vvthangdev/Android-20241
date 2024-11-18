package com.example.currency

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private val decimalFormat: DecimalFormat = DecimalFormat("#,###.##", DecimalFormatSymbols(Locale.US))
    private var money01: EditText? = null
    private var money02: EditText? = null
    private var spinner01: Spinner? = null
    private var spinner02: Spinner? = null

    private var isUpdating = false // Biến để tránh vòng lặp vô tận khi cập nhật

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        money01 = findViewById(R.id.money01)
        money02 = findViewById(R.id.money02)
        spinner01 = findViewById(R.id.spinner01)
        spinner02 = findViewById(R.id.spinner02)

        // Thiết lập các tùy chọn tiền tệ cho Spinner
        val currencies = arrayOf("USD", "EUR", "JPY", "GBP", "VND")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, currencies)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner01?.adapter = adapter
        spinner02?.adapter = adapter

        spinner01?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                performConversionFromMoney02()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Xử lý nếu không có mục nào được chọn, có thể bỏ trống nếu không cần
            }
        }

        spinner02?.onItemSelectedListener = object  : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                performConversionFromMoney01()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        // Lắng nghe thay đổi trong ô money01
        money01?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (!isUpdating) { // Chỉ thực hiện nếu không trong quá trình cập nhật
                    isUpdating = true
                    performConversionFromMoney01()
                    isUpdating = false
                }
            }

            override fun afterTextChanged(s: Editable) {}
        })

        // Lắng nghe thay đổi trong ô money02 (nếu muốn chuyển đổi ngược lại)
        money02?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (!isUpdating) { // Chỉ thực hiện nếu không trong quá trình cập nhật
                    isUpdating = true
                    performConversionFromMoney02()
                    isUpdating = false
                }
            }

            override fun afterTextChanged(s: Editable) {}
        })
    }

    // Phương thức để chuyển đổi từ money01 sang money02
    private fun performConversionFromMoney01() {
        val inputText = money01?.text.toString().replace(",", "")

        Log.v("CurrencyConverter", "${inputText}")

        if (inputText.isEmpty()) {
            money02?.setText("")
            return
        }

        val amount = inputText.toDoubleOrNull() ?: return
        val fromCurrency = spinner01?.selectedItem?.toString() ?: return
        val toCurrency = spinner02?.selectedItem?.toString() ?: return

        val convertedAmount = convertCurrency(amount, fromCurrency, toCurrency)
        money02?.setText(decimalFormat.format(convertedAmount))
    }

    // Phương thức để chuyển đổi từ money02 sang money01 (nếu cần)
    private fun performConversionFromMoney02() {
        val inputText = money02?.text.toString().replace(",", "")

        Log.v("CurrencyConverter", "${inputText}")

        if (inputText.isEmpty()) {
            money01?.setText("")
            return
        }

        val amount = inputText.toDoubleOrNull() ?: return
        val fromCurrency = spinner02?.selectedItem?.toString() ?: return
        val toCurrency = spinner01?.selectedItem?.toString() ?: return

        val convertedAmount = convertCurrency(amount, fromCurrency, toCurrency)
        money01?.setText(decimalFormat.format(convertedAmount))
    }

    // Phương thức quy đổi tiền tệ
    private fun convertCurrency(amount: Double, fromCurrency: String, toCurrency: String): Double {
        // Định nghĩa tỷ lệ quy đổi cố định cho từng loại tiền tệ
        val rates = mapOf(
            "USD" to 1.0,
            "EUR" to 0.94,
            "JPY" to 154.98,
            "GBP" to 0.79,
            "VND" to 25345.0,
        )

        // Lấy tỷ lệ cho từng loại tiền tệ
        val fromRate = rates[fromCurrency] ?: return 0.0
        val toRate = rates[toCurrency] ?: return 0.0

        // Tính toán theo công thức: toMoney = (toRate / fromRate) * fromMoney
        return (toRate / fromRate) * amount
    }
}
