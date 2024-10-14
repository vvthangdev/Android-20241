package com.example.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(), View.OnClickListener {

    var state = 1
    var op1 = 0.0
    var op2 = 0.0
    private var operator: String = ""
    var buffer: String = ""

    lateinit var textContent1: TextView
    lateinit var textContent2: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.linear_layout_calculator)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        textContent1 = findViewById(R.id.text_content1)
        textContent2 = findViewById(R.id.text_content2)

        val buttons = listOf(
            R.id.button_ce, R.id.button_c, R.id.button_bs, R.id.button_divide,
            R.id.button_multiply, R.id.button_plus, R.id.button_minus,
            R.id.button_dot, R.id.button_sign, R.id.button_equals,
            R.id.button_0, R.id.button_1, R.id.button_2, R.id.button_3,
            R.id.button_4, R.id.button_5, R.id.button_6, R.id.button_7,
            R.id.button_8, R.id.button_9
        )
        buttons.forEach { findViewById<Button>(it).setOnClickListener(this) }
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.button_0 -> addDigit("0")
            R.id.button_1 -> addDigit("1")
            R.id.button_2 -> addDigit("2")
            R.id.button_3 -> addDigit("3")
            R.id.button_4 -> addDigit("4")
            R.id.button_5 -> addDigit("5")
            R.id.button_6 -> addDigit("6")
            R.id.button_7 -> addDigit("7")
            R.id.button_8 -> addDigit("8")
            R.id.button_9 -> addDigit("9")
            R.id.button_dot -> addDot()
            R.id.button_plus -> setOperator("+")
            R.id.button_minus -> setOperator("-")
            R.id.button_multiply -> setOperator("*")
            R.id.button_divide -> setOperator("/")
            R.id.button_equals -> calculateResult()
            R.id.button_c -> clearEntry()
            R.id.button_ce -> clearAll()
        }
    }

    fun addDigit(digit: String) {
        buffer += digit
        textContent1.text = buffer
    }

    private fun setOperator(op: String) {
        if (buffer.isNotEmpty()) {
            op1 = buffer.toDouble()
            operator = op
            textContent2.text = "${formatNumber(op1)} $operator"
            buffer = ""
            state = 2
        }
    }

    fun calculateResult() {
        if (buffer.isNotEmpty()) {
            op2 = buffer.toDouble()

            val result = when (operator) {
                "+" -> op1 + op2
                "-" -> op1 - op2
                "*" -> op1 * op2
                "/" -> if (op2 != 0.0) op1 / op2 else Double.NaN
                else -> 0.0
            }

            textContent1.text = formatNumber(result)
            textContent2.text = "${formatNumber(op1)} $operator ${formatNumber(op2)} ="
            resetAfterCalculation(result)
        }
    }


    fun resetAfterCalculation(result: Double) {
        op1 = result
        op2 = 0.0
        operator = ""
        buffer = ""
        state = 1
    }

    fun clearEntry() {
        buffer = ""
        textContent1.text = "0"
    }

    fun clearAll() {
        op1 = 0.0
        op2 = 0.0
        operator = ""
        buffer = ""
        state = 1
        textContent1.text = "0"
        textContent2.text = ""
    }

    fun backspace() {
        if (state == 1) {
            op1 = (op1 / 10).toInt().toDouble()
            textContent1.text = formatNumber(op1)
        } else {
            op2 = (op2 / 10).toInt().toDouble()
            textContent1.text = formatNumber(op2)
        }
    }

    fun toggleSign() {
        if (state == 1) {
            op1 = -op1
            textContent1.text = formatNumber(op1)
        } else {
            op2 = -op2
            textContent1.text = formatNumber(op2)
        }
    }

    fun addDot() {
        if (!buffer.contains(".")) {
            buffer += "."
            textContent1.text = buffer
        }
    }

    private fun formatNumber(num: Double): String {
        return if (num == num.toInt().toDouble()) {
            num.toInt().toString()
        } else {
            num.toString()
        }
    }
}
