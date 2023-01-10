package com.example.sfs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import kotlinx.coroutines.*

class SplashyScreenActivity : AppCompatActivity() {
    @OptIn(DelicateCoroutinesApi::class)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashyscreen)

        Handler(Looper.getMainLooper()).postDelayed({

            val intent1 = Intent(this, GetPinActivity::class.java)
            startActivity(intent1)

            GlobalScope.launch(Dispatchers.IO) {
                // heavy initialization tasks or network requests here

                withContext(Dispatchers.Main) {
                    val intent1 = Intent(this@SplashyScreenActivity, GetPinActivity::class.java)
                    startActivity(intent1)


                    finish()
                }
            }
        }, 2000)

    }
}
