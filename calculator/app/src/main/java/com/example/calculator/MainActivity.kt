package com.example.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.math.round

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var op1 = 0.0
    private var op2 = 0.0
    private var buffer = ""
    private var state = 1
    private var operator = ""
    //Last button kiểm tra button cuối cùng nhập vào, nếu là toán tử thì = 2, tránh việc tính nhầm khi
    // nhập 2 toán tử liên tiếp
    private var lastButton = 0

    private lateinit var textContent1: TextView
    private lateinit var textContent2: TextView

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

        findViewById<Button>(R.id.button_ce).setOnClickListener(this)
        findViewById<Button>(R.id.button_c).setOnClickListener(this)
        findViewById<Button>(R.id.button_bs).setOnClickListener(this)
        findViewById<Button>(R.id.button_divide).setOnClickListener(this)
        findViewById<Button>(R.id.button_multiply).setOnClickListener(this)
        findViewById<Button>(R.id.button_plus).setOnClickListener(this)
        findViewById<Button>(R.id.button_minus).setOnClickListener(this)
        findViewById<Button>(R.id.button_dot).setOnClickListener(this)
        findViewById<Button>(R.id.button_sign).setOnClickListener(this)
        findViewById<Button>(R.id.button_equals).setOnClickListener (this)
        findViewById<Button>(R.id.button_0).setOnClickListener(this)
        findViewById<Button>(R.id.button_1).setOnClickListener(this)
        findViewById<Button>(R.id.button_2).setOnClickListener(this)
        findViewById<Button>(R.id.button_3).setOnClickListener(this)
        findViewById<Button>(R.id.button_4).setOnClickListener(this)
        findViewById<Button>(R.id.button_5).setOnClickListener(this)
        findViewById<Button>(R.id.button_6).setOnClickListener(this)
        findViewById<Button>(R.id.button_7).setOnClickListener(this)
        findViewById<Button>(R.id.button_8).setOnClickListener(this)
        findViewById<Button>(R.id.button_9).setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        val id = p0?.id;
        when (id) {
            R.id.button_0 -> {
                if(op1 == 0.0 || op2 == 0.0) return
                addDigit("0")
            }
            R.id.button_1 -> {addDigit("1")}
            R.id.button_2 -> {addDigit("2")}
            R.id.button_3 -> {addDigit("3")}
            R.id.button_4 -> {addDigit("4")}
            R.id.button_5 -> {addDigit("5")}
            R.id.button_6 -> {addDigit("6")}
            R.id.button_7 -> {addDigit("7")}
            R.id.button_8 -> {addDigit("8")}
            R.id.button_9 -> {addDigit("9")}
            R.id.button_dot -> {addDot()}

            R.id.button_plus -> {setOperator("+")}
            R.id.button_minus -> {setOperator("-")}
            R.id.button_multiply -> {setOperator("*")}
            R.id.button_divide -> {setOperator("/")}

            R.id.button_equals -> {calculateResult()}
            R.id.button_sign -> {toggleSign()}
            R.id.button_ce -> {clearEntry()}
            R.id.button_c -> {clearAll()}
            R.id.button_bs -> {backSpace()}
        }
    }

    fun addDigit(c: String) {
        if (state == 1) {
            buffer += c
            op1 = buffer.toDouble()
            textContent1.text = buffer
        }
        else {
            buffer += c
            op2 = buffer.toDouble()
            textContent1.text = buffer
        }
        lastButton = 1
    }

    fun addDot(){
        if(!buffer.contains(".")){
            if (state == 1) {
                buffer += "."
                textContent1.text = buffer
            }
            else {
                buffer += "."
                textContent1.text = buffer
            }
        }
    }
    private fun setOperator(op: String) {
        if (lastButton == 2) {
            operator = op
            textContent2.text = "${formatNumber(op1)} $operator"
            return
        }

        if (state == 2) {
            calculateResult()
            operator = op
            textContent2.text = "${formatNumber(op1)} $operator"
        }
        else {
            operator = op
            textContent2.text = "${formatNumber(op1)} $operator"

        }
        state = 2
        lastButton = 2
        buffer = ""

    }

    fun calculateResult() {
        val result = when (operator) {
            "+" -> op1 + op2
            "-" -> op1 - op2
            "*" -> op1 * op2
            "/" -> if(op2 != 0.0) (op1 / op2) else Double.NaN
            else -> {0.0}
        }
        textContent2.text = "${formatNumber(op1)} $operator ${formatNumber(op2)}"
        textContent1.text = "${formatNumber(result)}"
        resetAfterCalculation(result)
    }

    fun resetAfterCalculation(result: Double) {
        op1 = result
        op2 = 0.0
        operator = ""
        buffer = ""
        state = 1
        lastButton = 0
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

    private fun backSpace() {
        val currentText = textContent1.text.toString()

        if (currentText.isNotEmpty()) {
            // Xóa ký tự cuối cùng
            val newText = currentText.dropLast(1)

            // Cập nhật lại nội dung hiển thị
            textContent1.text = if (newText.isEmpty()) "0" else newText

            // Cập nhật giá trị op1 hoặc op2 tùy theo state
            if (state == 1) {
                op1 = newText.toDoubleOrNull() ?: 0.0
            } else {
                op2 = newText.toDoubleOrNull() ?: 0.0
            }
        }
    }

    private fun clearAll() {
        op1 = 0.0
        op2 = 0.0
        buffer = ""
        state = 1
        operator = ""
        lastButton = 0
        textContent1.text = "0"
        textContent2.text = ""
    }

    private fun clearEntry() {
        buffer = ""
        textContent1.text = ""
    }

    private fun formatNumber(num: Double): String {
        return if (num == num.toInt().toDouble()) {
            num.toInt().toString()
        } else {
            (round(num * 1000000000)/ 1000000000).toString()
        }
    }
}
