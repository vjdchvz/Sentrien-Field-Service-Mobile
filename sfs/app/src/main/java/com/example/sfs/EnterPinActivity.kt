package com.example.sfs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class EnterPinActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enter_pin)

        val textMobile: TextView = findViewById(R.id.textMobile)
        val mobileNumber = intent.getStringExtra("mobile")
        textMobile.text = String.format("+63-%s", mobileNumber)

        val buttonVerify: Button = findViewById(R.id.buttonVerify)
        buttonVerify.setOnClickListener {
            val mainIntent = Intent(this, MainActivity::class.java)
            startActivity(mainIntent)
        }
        setupOTPInputs()
    }

    private fun setupOTPInputs() {
        val inputCode1 = findViewById<EditText>(R.id.inputCode1)
        val inputCode2 = findViewById<EditText>(R.id.inputCode2)
        val inputCode3 = findViewById<EditText>(R.id.inputCode3)
        val inputCode4 = findViewById<EditText>(R.id.inputCode4)
        val inputCode5 = findViewById<EditText>(R.id.inputCode5)
        val inputCode6 = findViewById<EditText>(R.id.inputCode6)

        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val currentField = currentFocus
                if (currentField is EditText) {
                    if (count > 0) {
                        // User entered a character
                        val nextField = when (currentField.id) {
                            R.id.inputCode1 -> inputCode2
                            R.id.inputCode2 -> inputCode3
                            R.id.inputCode3 -> inputCode4
                            R.id.inputCode4 -> inputCode5
                            R.id.inputCode5 -> inputCode6
                            else -> null
                        }
                        nextField?.requestFocus()
                    }
                }
            }

            override fun afterTextChanged(s: Editable?) {
                if (s.isNullOrEmpty()) {
                    val currentField = currentFocus
                    if (currentField is EditText) {
                        val previousField = when (currentField.id) {
                            R.id.inputCode2 -> inputCode1
                            R.id.inputCode3 -> inputCode2
                            R.id.inputCode4 -> inputCode3
                            R.id.inputCode5 -> inputCode4
                            R.id.inputCode6 -> inputCode5
                            else -> null
                        }
                        previousField?.requestFocus()
                    }
                }
            }
        }

        inputCode1.addTextChangedListener(textWatcher)
        inputCode2.addTextChangedListener(textWatcher)
        inputCode3.addTextChangedListener(textWatcher)
        inputCode4.addTextChangedListener(textWatcher)
        inputCode5.addTextChangedListener(textWatcher)
        inputCode6.addTextChangedListener(textWatcher)
    }

}
