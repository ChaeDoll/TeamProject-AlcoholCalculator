package com.example.alcoholcalculator

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.inputmethod.InputMethod
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.core.widget.doAfterTextChanged
import com.example.alcoholcalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    //    binding
    private var mainBinding: ActivityMainBinding?= null
    private val mbinding get() = mainBinding!!

    //   전역 변수

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // activity_main.xml 연결
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        // activity_main.xml 기본 view로 설정
        setContentView(mbinding.root)

        // 지역변수
        val calcList = arrayListOf<CalcItem>()
        val calcAdapter = CalcListViewAdapter(this, calcList) //calcListViewAdapter.kt 연결
        val calcListView : ListView = mbinding.listview
        val parentLayout = findViewById<LinearLayout>(R.id.content_item)
        val tips = LayoutInflater.from(this).inflate(R.layout.content_tips, null) as LinearLayout
        val results = LayoutInflater.from(this).inflate(R.layout.content_results, null) as LinearLayout
        parentLayout.addView(tips)

        calcListView.adapter = calcAdapter

        // 맨 처음에 두 개의 아이템 추가
        calcList.add(CalcItem("", "", ""))
        calcList.add(CalcItem("", "", ""))



        // 계산 아이템 추가 버튼
        val addButton : Button = mbinding.addButton
        addButton.setOnClickListener {
            // 아이템 추가 개수 제한
            if(calcList.size == 10) {
                Toast.makeText(this, "재료를 추가할 수 없습니다 (최대 10개)", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            // 키보드 내려가는거
            val inputManager : InputMethodManager = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.hideSoftInputFromWindow(this.currentFocus?.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)

            // 아이템 추가 및 새로고침
            calcList.add(CalcItem("", "", ""))
            calcAdapter.notifyDataSetChanged()
//            calcListView.transcriptMode = ListView.TRANSCRIPT_MODE_NORMAL
            calcListView.transcriptMode = ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL
        }

        // 초기화 버튼
        val clearButton : ImageButton = mbinding.clearButton
        clearButton.setOnClickListener{
            calcList.clear()
            calcList.add(CalcItem("", "", ""))
            calcList.add(CalcItem("", "", ""))
            calcAdapter.notifyDataSetChanged()

            // 결과 내용(수정)
            parentLayout.removeAllViews()
            parentLayout.addView(tips)
            Toast.makeText(this, "초기화 되었습니다.", Toast.LENGTH_SHORT).show()
        }
        // 결과 버튼
        val resultButton : Button = mbinding.resultButton
        resultButton.setOnClickListener {
            val inputManager : InputMethodManager = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.hideSoftInputFromWindow(this.currentFocus?.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
            //result content 불러오기
            parentLayout.removeAllViews()
            parentLayout.addView(results)

            var sumAmount : Float = 0f // 총 양
            var sumAlcohol  : Float = 0f // 양 * 도수의 총 합

            // 도수와 양 중 빈칸으로 계산 결과를 누르면 해당 칸을 0으로 채우기
            for(i in 0 until calcList.size step(1)) {
                val amountString = calcList[i].amount_.toString()
                val degreeString = calcList[i].degree_.toString()

                if (amountString == "" && degreeString == "") {
                    calcList[i].amount_ = 0
                    calcList[i].degree_ = 0
                }else if(amountString == "" && degreeString.toFloat() > 100) {
                    calcList[i].degree_ = ""
                    Toast.makeText(this, "100 이하의 수로 입력해주세요", Toast.LENGTH_SHORT).show()
                }else if (amountString == "" && degreeString != "") {
                    calcList[i].amount_ = 0
                } else if (amountString != "" && degreeString == "") {
                    calcList[i].degree_ = 0
                    sumAmount += (amountString.toFloat())
                } else if (amountString != "" && degreeString.toFloat()>100){
                    calcList[i].degree_ = ""
                    Toast.makeText(this,"100 이하의 수로 입력해주세요",Toast.LENGTH_SHORT).show()
                } else {
                    sumAmount += (amountString.toFloat())
                    sumAlcohol += (amountString.toFloat()) * (degreeString.toFloat())
                }
            }

            calcAdapter.notifyDataSetChanged()

            val degree: Float // 최종 도수 (= 총 양 / 총 알코올 양)
            // sumAmount가 0일 경우 degree를 계산할 때 나누는 값이 0이 되기 때문에 에러 발생 함
            // 이를 대비하기 위한 코드

            if (sumAmount == 0f) {
                // sumAmount가 0일 때 degree값이 nan는 에러 해결 코드
                degree = 0f
            } else {
                degree = (sumAlcohol / sumAmount)
            }
            val degreeToFixed = String.format("%.2f", degree) // 최종 도수를 소수점 2번째까지

            // 결과 내용(수정)
            val tmp: String = "총 양 " + sumAmount.toString() + "도수 " + degreeToFixed

            results.findViewById<TextView>(R.id.resultDegree).text = "$degreeToFixed%"
            results.findViewById<TextView>(R.id.resultAmount).text = "${sumAmount}ml"
        }
    } //fun onCreate 끝

    //뒤로가기 눌렀을 때 종료되도록 함. 홈,탐색 버튼으로는 종료되지 않음
    override fun onBackPressed() {
        super.onBackPressed()
        Log.d("Help", "App is Down")
        finish()
    }
}