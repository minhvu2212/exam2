package com.example.exam2

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {
    private lateinit var editTextNumber: EditText
    private lateinit var radioGroup: RadioGroup
    private lateinit var buttonShow: Button
    private lateinit var listViewNumbers: ListView
    private lateinit var textViewError: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Khởi tạo các view
        editTextNumber = findViewById(R.id.editTextNumber)
        radioGroup = findViewById(R.id.radioGroup)
        buttonShow = findViewById(R.id.buttonShow)
        listViewNumbers = findViewById(R.id.listViewNumbers)
        textViewError = findViewById(R.id.textViewError)

        buttonShow.setOnClickListener {
            showNumbers()
        }
    }

    private fun showNumbers() {
        // Xóa thông báo lỗi cũ
        textViewError.text = ""

        // Lấy giá trị n từ EditText
        val input = editTextNumber.text.toString()
        if (input.isEmpty()) {
            textViewError.text = getString(R.string.error_empty)
            return
        }

        try {
            val n = input.toInt()
            if (n < 0) {
                textViewError.text = getString(R.string.error_negative)
                return
            }

            // Tạo danh sách số theo loại được chọn
            val numbers = when (radioGroup.checkedRadioButtonId) {
                R.id.radioEven -> getEvenNumbers(n)
                R.id.radioOdd -> getOddNumbers(n)
                R.id.radioSquare -> getSquareNumbers(n)
                else -> {
                    textViewError.text = getString(R.string.error_select_type)
                    return
                }
            }

            // Hiển thị danh sách
            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, numbers)
            listViewNumbers.adapter = adapter

        } catch (e: NumberFormatException) {
            textViewError.text = getString(R.string.error_invalid)
        }
    }

    private fun getEvenNumbers(n: Int): List<Int> {
        return (0..n).filter { it % 2 == 0 }
    }

    private fun getOddNumbers(n: Int): List<Int> {
        return (1..n).filter { it % 2 == 1 }
    }

    private fun getSquareNumbers(n: Int): List<Int> {
        return (0..n).filter {
            val sqrt = sqrt(it.toDouble()).toInt()
            sqrt * sqrt == it
        }
    }
}