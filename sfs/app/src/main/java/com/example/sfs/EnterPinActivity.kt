package com.example.sfs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential

import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit

class EnterPinActivity : AppCompatActivity() {
    private var verificationId: String? = null
    private var mobileNumber: String? = null
    private var resendToken: PhoneAuthProvider.ForceResendingToken? = null
    private lateinit var auth: FirebaseAuth
    private lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks

    override fun onCreate(savedInstanceState: Bundle?) {
        FirebaseApp.initializeApp(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enter_pin)

        //resend

        callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                // verification successful
                auth.signInWithCredential(credential).addOnCompleteListener(this@EnterPinActivity) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this@EnterPinActivity, "Verification Successful", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@EnterPinActivity, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(this@EnterPinActivity, "Error: " + task.exception?.localizedMessage, Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onVerificationFailed(e: FirebaseException) {
                Toast.makeText(this@EnterPinActivity, "Error: " + e.localizedMessage, Toast.LENGTH_SHORT).show()
            }

            override fun onCodeSent(verificationId: String, token: PhoneAuthProvider.ForceResendingToken) {
                super.onCodeSent(verificationId, token)
                this@EnterPinActivity.verificationId = verificationId
                resendToken = token
            }
        }

// Get the verificationId and mobile number from the intent
        verificationId = intent.getStringExtra("verificationId")
        mobileNumber = intent.getStringExtra("mobile")

        val textMobile: TextView = findViewById(R.id.textMobile)
        textMobile.text = String.format("The number you input", mobileNumber)
        auth = FirebaseAuth.getInstance()

        val buttonResendOTP = findViewById<Button>(R.id.textResendOTP)
        buttonResendOTP.setOnClickListener {
            PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+63$mobileNumber", // Phone number to verify
                60, // Timeout duration
                TimeUnit.SECONDS, // Unit of timeout
                this, // Activity (for callback binding)
                callbacks, // OnVerificationStateChangedCallbacks
                resendToken // token for resending the verification code
            )
        }


        val buttonVerify = findViewById<Button>(R.id.buttonVerify)
        buttonVerify.setOnClickListener {
            val code = findViewById<EditText>(R.id.inputCode1).text.toString() +
                    findViewById<EditText>(R.id.inputCode2).text.toString() +
                    findViewById<EditText>(R.id.inputCode3).text.toString() +
                    findViewById<EditText>(R.id.inputCode4).text.toString() +
                    findViewById<EditText>(R.id.inputCode5).text.toString() +
                    findViewById<EditText>(R.id.inputCode6).text.toString()


            if (code.isEmpty()) {
                Toast.makeText(this, "Please enter the code", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val credential = PhoneAuthProvider.getCredential(verificationId!!, code)
            auth.signInWithCredential(credential).addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // verification successful
                    Toast.makeText(this, "Verification Successful", Toast.LENGTH_SHORT).show()
                    // proceed to the next activity
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        Toast.makeText(
                            this,
                            "Invalid code. Please try again.",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(
                            this,
                            "Error: " + task.exception?.localizedMessage,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
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