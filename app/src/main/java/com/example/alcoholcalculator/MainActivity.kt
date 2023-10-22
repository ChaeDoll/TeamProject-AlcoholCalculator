package com.example.alcoholcalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        ArrayList<Material> materialList = new ArrayList<Material>()
        val ButtonTmp: Button = findViewById(R.id.buttonTmp) //임시 확인 버튼
        val textViewTmp: TextView = findViewById(R.id.textViewTmp) //임시 결과 창
        val editTextMaterial : EditText = findViewById(R.id.editTextMaterial) //재료명 적는 곳
        val editTextDegree : EditText = findViewById(R.id.editTextDegree) // 도수 적는 곳
        val editTextNumberAmount : EditText = findViewById(R.id.editTextNumberAmount) //양 적는 곳

        ButtonTmp.setOnClickListener {
            val naeyong :String = editTextMaterial.text.toString()
            val degree : Float = editTextDegree.text.toString().toFloat()
            val amount : Float = editTextNumberAmount.text.toString().toFloat()
            textViewTmp.setText("재료명 : "+naeyong+" 도수: "+degree+" 양 : "+amount)


        }
    }
}