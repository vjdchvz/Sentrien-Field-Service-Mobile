package com.example.sfs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler(Looper.getMainLooper()).postDelayed({
            val intent2 = Intent(this, MainActivity::class.java)
            startActivity(intent2)
            overridePendingTransition(android.R.anim.bounce_interpolator,android.R.anim.bounce_interpolator)

            // to get rid of this when u push the back button
            finish()
        }, 5000)

    }
    }
