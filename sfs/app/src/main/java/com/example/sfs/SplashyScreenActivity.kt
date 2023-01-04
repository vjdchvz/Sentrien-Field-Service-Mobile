package com.example.sfs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class SplashyScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashyscreen)

        Handler(Looper.getMainLooper()).postDelayed({
            val intent1 = Intent(this, GetPinActivity::class.java)
            startActivity(intent1)
            overridePendingTransition(android.R.anim.bounce_interpolator,android.R.anim.bounce_interpolator)

            // to get rid of this when u push the back button
            finish()
        }, 2000)

    }
    }
