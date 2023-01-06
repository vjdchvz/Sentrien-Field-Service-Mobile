package com.example.sfs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView


class EnterPinActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enter_pin)

        val textMobile: TextView = findViewById(R.id.textMobile)
        textMobile.text = String.format("+63-%s", intent.getStringExtra(""))



        setupOTPInputs()

    }

    private fun setupOTPInputs() {
        val inputCode1 = findViewById<EditText>(R.id.inputCode1)
        val inputCode2 = findViewById<EditText>(R.id.inputCode2)
        val inputCode3 = findViewById<EditText>(R.id.inputCode3)
        val inputCode4 = findViewById<EditText>(R.id.inputCode4)
        val inputCode5 = findViewById<EditText>(R.id.inputCode5)
        val inputCode6 = findViewById<EditText>(R.id.inputCode6)



        inputCode1.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.toString().trim().isEmpty()) {
                    inputCode2.requestFocus()
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })
        inputCode2.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.toString().trim().isEmpty()) {
                    inputCode3.requestFocus()
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })
        inputCode3.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.toString().trim().isEmpty()) {
                    inputCode4.requestFocus()
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })
        inputCode4.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.toString().trim().isEmpty()) {
                    inputCode5.requestFocus()
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })
        inputCode5.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.toString().trim().isEmpty()) {
                    inputCode6.requestFocus()
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })



    }
}

