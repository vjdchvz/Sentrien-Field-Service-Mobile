package com.example.sfs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit

class GetPinActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_pin)


        val buttonGetOTP: Button = findViewById(R.id.buttonGetOTP)

        buttonGetOTP.setOnClickListener {
            val inputMobile = findViewById<EditText>(R.id.inputNum)
            var mobileNumber = inputMobile.text.toString()
            if (mobileNumber.isEmpty()) {
                Toast.makeText(this, "Please input your number", Toast.LENGTH_SHORT).show()
            } else if (mobileNumber.isNotEmpty()){
                mobileNumber = "+63" + mobileNumber.substring(1)
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
                        }

                        override fun onCodeSent(
                            verificationId: String,
                            token: PhoneAuthProvider.ForceResendingToken
                        ) {
                            // verification code sent
                            // You can use the verificationId and the token to allow the user to input the code and resend the code if necessary
                            val intent = Intent(this@GetPinActivity, EnterPinActivity::class.java)
                            intent.putExtra("verificationId", verificationId)
                            intent.putExtra("token", token)
                            intent.putExtra("mobile", mobileNumber) // Pass the phone number in an extra
                            startActivity(intent)
                        }
                    }
                )
            } else {
                Toast.makeText(
                    this,
                    "Incorrect number of digits or first digit is not '9'",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}
