package com.example.sfs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val backButton = findViewById<View>(R.id.backbutton) as ImageButton
        backButton.setOnClickListener {
            startActivity(Intent(this@SettingsActivity, MainActivity::class.java))
        }
    }
}