package com.example.sfs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class GetPinActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_pin)



        val buttonGetOTP: Button = findViewById(R.id.buttonGetOTP)


        buttonGetOTP.setOnClickListener {
            val inputMobile = findViewById<EditText>(R.id.inputNum)
            val intent = Intent(this, EnterPinActivity::class.java)
            intent.putExtra("mobile", inputMobile.text.toString())
            startActivity(intent)
        }
    }
}
