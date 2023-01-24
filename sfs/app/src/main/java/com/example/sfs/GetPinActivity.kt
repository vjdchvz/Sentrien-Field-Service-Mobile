package com.example.sfs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputFilter
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit



class GetPinActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        FirebaseApp.initializeApp(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_pin)
        val inputMobile = findViewById<EditText>(R.id.inputNum)
        //max 10 numbers can be input
        inputMobile.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(10))


        val buttonGetOTP = findViewById<Button>(R.id.buttonGetOT)

        buttonGetOTP.setOnClickListener {
            // this disabled the recaptcha from showing
            // FirebaseAuth.getInstance().firebaseAuthSettings.setAppVerificationDisabledForTesting(true)

            var mobileNumber = inputMobile.text.toString()
            val progressBar = findViewById<ProgressBar>(R.id.progressBar)
            progressBar.visibility = View.VISIBLE
            buttonGetOTP.visibility =View.INVISIBLE
            if (mobileNumber.isEmpty()) {
                progressBar.visibility = View.INVISIBLE
                buttonGetOTP.visibility =View.VISIBLE
                Toast.makeText(this, "Please input your number", Toast.LENGTH_SHORT).show()
            } else if (mobileNumber.length != 10) {
                progressBar.visibility = View.INVISIBLE
                buttonGetOTP.visibility =View.VISIBLE
                Toast.makeText(this, "Number should be equal to 10 digits", Toast.LENGTH_SHORT)
                    .show()
            } else if (mobileNumber[0] != '9') {
                progressBar.visibility = View.INVISIBLE
                buttonGetOTP.visibility =View.VISIBLE
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
                            buttonGetOTP.visibility =View.VISIBLE
                            progressBar.visibility = View.INVISIBLE
                            Toast.makeText(this@GetPinActivity, "Verification code sent!", Toast.LENGTH_SHORT).show()
                            // You can sign in the user with the credential here
                        }

                        override fun onVerificationFailed(e: FirebaseException) {
                            // verification failed
                            buttonGetOTP.visibility =View.VISIBLE
                            progressBar.visibility = View.INVISIBLE
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
                            buttonGetOTP.visibility =View.VISIBLE
                            progressBar.visibility =View.INVISIBLE
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

                        }

                    }   )
            }

        }
    }
}


