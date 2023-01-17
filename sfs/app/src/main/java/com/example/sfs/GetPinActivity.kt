package com.example.sfs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit
import android.webkit.WebView
import android.webkit.WebViewClient


class GetPinActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        FirebaseApp.initializeApp(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_pin)



        val webView = findViewById<WebView>(R.id.webView)
        webView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView, url: String) {
                super.onPageFinished(view, url)
                // hide the WebView when the page finishes loading
                webView.visibility = View.GONE
            }
        }



        val buttonGetOTP: Button = findViewById(R.id.buttonGetOTP)

        buttonGetOTP.setOnClickListener {
            // this disabled the recaptcha from showing
            // FirebaseAuth.getInstance().firebaseAuthSettings.setAppVerificationDisabledForTesting(true)
            val inputMobile = findViewById<EditText>(R.id.inputNum)
            var mobileNumber = inputMobile.text.toString()
            if (mobileNumber.isEmpty()) {
                Toast.makeText(this, "Please input your number", Toast.LENGTH_SHORT).show()
            } else if (mobileNumber.length != 10) {
                Toast.makeText(this, "Number should be equal to 10 digits", Toast.LENGTH_SHORT).show()
            } else if (mobileNumber[0] != '9') {
                Toast.makeText(this, "Number should start with 9", Toast.LENGTH_SHORT).show()
            } else {
                mobileNumber = "+63$mobileNumber"

                // Request a verification code for the specified phone number
                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                    mobileNumber,
                    60,
                    TimeUnit.SECONDS,
                    this,
                    object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                        override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                            // verification completed successfully
                            // You can sign in the user with the credential here
                        }

                        override fun onVerificationFailed(e: FirebaseException) {
                            // verification failed
                            Log.e("PhoneAuth", "Verification failed", e)
                            Toast.makeText(this@GetPinActivity, e.message, Toast.LENGTH_SHORT)
                                .show()
                        }


                        override fun onCodeSent(
                            verificationId: String,
                            token: PhoneAuthProvider.ForceResendingToken
                        ) {
                            // Store the verification ID and resending token so we can use them later
                            val intent = Intent(this@GetPinActivity, EnterPinActivity::class.java)
                            intent.putExtra("verificationId", verificationId)
                            intent.putExtra("token", token)
                            intent.putExtra("mobile", mobileNumber)
                            startActivity(intent)
                            finish()
                            Toast.makeText(
                                this@GetPinActivity,
                                "OTP sent successfully",
                                Toast.LENGTH_SHORT
                            ).show()
                            webView.visibility = View.VISIBLE
                            webView.webViewClient = object : WebViewClient() {
                                override fun onPageFinished(view: WebView, url: String) {
                                    super.onPageFinished(view, url)
                                    // check if the reCAPTCHA page is loaded
                                    if (url.contains("google.com/recaptcha")) {
                                        // hide the loading spinner and show the WebView
                                        webView.visibility = View.GONE
                                    }
                                }
                            }

                            webView.loadUrl("https://example.com/recaptcha")
                        }
                    } )
                }
            }
        }
    }





