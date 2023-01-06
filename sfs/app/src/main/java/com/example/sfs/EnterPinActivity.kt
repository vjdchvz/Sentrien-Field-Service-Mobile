package com.example.sfs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.doOnTextChanged


class EnterPinActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enter_pin)

        val textMobile: TextView = findViewById(R.id.textMobile)
        val mobileNumber = intent.getStringExtra("mobile")
        textMobile.text = String.format("+63-%s", mobileNumber)



        setupOTPInputs()

    }

    private fun setupOTPInputs() {
        val inputCode1 = findViewById<EditText>(R.id.inputCode1)
        val inputCode2 = findViewById<EditText>(R.id.inputCode2)
        val inputCode3 = findViewById<EditText>(R.id.inputCode3)
        val inputCode4 = findViewById<EditText>(R.id.inputCode4)
        val inputCode5 = findViewById<EditText>(R.id.inputCode5)
        val inputCode6 = findViewById<EditText>(R.id.inputCode6)

        inputCode1.doOnTextChanged { _, _, _, count ->
            if (count > 0) {
                inputCode2.requestFocus()
            } else {
                inputCode1.text = null
            }
        }
        inputCode2.doOnTextChanged { _, _, _, count ->
            if (count > 0) {
                inputCode3.requestFocus()
            } else {
                inputCode1.requestFocus()
            }
        }
        inputCode3.doOnTextChanged { _, _, _, count ->
            if (count > 0) {
                inputCode4.requestFocus()
            } else {
                inputCode2.requestFocus()
            }
        }
        inputCode4.doOnTextChanged { _, _, _, count ->
            if (count > 0) {
                inputCode5.requestFocus()
            } else {
                inputCode3.requestFocus()
            }
        }
        inputCode5.doOnTextChanged { _, _, _, count ->
            if (count > 0) {
                inputCode6.requestFocus()
            } else {
                inputCode4.requestFocus()
            }
        }
        inputCode6.doOnTextChanged { _, _, _, count ->
            if (count > 0) {
                // Do nothing
            } else {
                inputCode5.requestFocus()
            }
        }
    }

}

