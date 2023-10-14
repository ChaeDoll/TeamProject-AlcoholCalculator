package com.example.alcoholcalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val changeButton: Button = findViewById(R.id.button)
        val textBox : TextView = findViewById(R.id.textView)
        val editText : EditText = findViewById(R.id.editText)
        changeButton.setOnClickListener {
            val naeyong :String = editText.text.toString()
            textBox.text = naeyong
            Toast.makeText(this.applicationContext, naeyong, Toast.LENGTH_SHORT).show()
        }
    }
}