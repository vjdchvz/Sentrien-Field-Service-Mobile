package com.example.sfs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class GetPinActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_pin)


        val inputMobile = findViewById<EditText>(R.id.inputNum)
        val buttonGetOTP: Button = findViewById(R.id.buttonGetOTP)


        buttonGetOTP.setOnClickListener {
            if (inputMobile.text.toString().trim().isEmpty()) {
                Toast.makeText(this@GetPinActivity, "Enter Mobile", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val intent = Intent(applicationContext, EnterPinActivity::class.java)
            intent.putExtra("mobile", inputMobile.text.toString())
            startActivity(intent)
        }
    }
}
