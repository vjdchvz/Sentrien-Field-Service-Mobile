package com.example.sfs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AlertDialog

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val backButton = findViewById<View>(R.id.backbutton) as ImageButton
        backButton.setOnClickListener {
            startActivity(Intent(this@SettingsActivity, MainActivity::class.java))

        }
        showEditTextDialog()
    }
    private fun showEditTextDialog(){
        val empID_diag = findViewById<View>(R.id.empID) as Button
        val test1 = findViewById<View>(R.id.test)
        empID_diag.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            val inflater = LayoutInflater.from(this)
            val dialogLayout = inflater.inflate(R.layout.edit_text_layout, null)
            val editText = dialogLayout.findViewById<EditText>(R.id.empID_input)
            val test1 = findViewById<TextView>(R.id.test)
            with(builder){
                setTitle("Enter Employee ID")
                setPositiveButton("OK"){dialog, which ->
                    test1.text = editText.text.toString()

                }
                setNegativeButton("Cancel"){dialog, which ->
                    Log.d("Main","Negative button clicked")

                }
                setView(dialogLayout)
                show()
            }

        }
    }
}