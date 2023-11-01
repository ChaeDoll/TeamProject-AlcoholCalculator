package com.example.alcoholcalculator

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
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

    //    변수
    private lateinit var calcListView : ListView
    private val calcList = arrayListOf<CalcItem>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // activity_main.xml 연결
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        // activity_main.xml 기본 view로 설정
        setContentView(mbinding.root)

        calcList.add(CalcItem("", "", ""))
        calcList.add(CalcItem("", "", ""))
        //calcListViewAdapter.kt 연결
        val calcAdapter = CalcListViewAdapter(this, calcList)
        /* activity_main.xml의 요소인 listview를 calclistView와 연결
        여기서의 calclistView는 요소 추가, 요소 삭제, 수정 등의 작업을 함 */
        calcListView = mbinding.listview
        calcListView.adapter = calcAdapter

        // 계산 아이템 추가 버튼
        val addButton : Button = mbinding.add
        addButton.setOnClickListener {
            if(calcList.size == 10) {
                Toast.makeText(this, "재료를 추가할 수 없습니다 (최대 10개)", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val inputManager : InputMethodManager = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.hideSoftInputFromWindow(this.currentFocus?.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)

            calcList.add(CalcItem("", "", ""))
            calcAdapter.notifyDataSetChanged()
            calcListView.transcriptMode = ListView.TRANSCRIPT_MODE_NORMAL
        }
        // 초기화 버튼
        val clearButton : ImageButton = mbinding.clear
        clearButton.setOnClickListener{
            calcList.clear()
            calcList.add(CalcItem("", "", ""))
            calcList.add(CalcItem("", "", ""))
            calcAdapter.notifyDataSetChanged()
            Toast.makeText(this, "초기화 되었습니다.", Toast.LENGTH_SHORT).show()
        }
        // 결과 버튼
        val resultButton : Button = mbinding.resultButton
        val resultView : TextView = mbinding.resultView
        resultButton.setOnClickListener {
            var sumAmount : Float = 0f // 총 양
            var sumAlcohol  : Float = 0f // 양 * 도수의 총 합
            for(i in 0 until calcList.size step(1)) {
                var amountString = calcList.get(i).amount_.toString()
                var degreeString = calcList.get(i).degree_.toString()

                // 전부 if로 한 이유는 else if로 할 경우 if가 우선, else if가 차선이 되어
                //반응 속도가 한 박자 느리기 때문임
                if (amountString == "" && degreeString == "") {
                    // 아무것도 할 게 없다.
                    calcList[i].amount_ = 0
                    calcList[i].degree_ = 0
                } else if (amountString == "" && degreeString != "") {
                    calcList[i].amount_ = 0

                } else if (amountString != "" && degreeString == "") {
                    calcList[i].degree_ = 0
                    sumAmount += (amountString.toFloat())
                } else {
                    sumAmount += (amountString.toFloat())
                    sumAlcohol += (amountString.toFloat()) * (degreeString.toFloat())
                }

                calcAdapter.notifyDataSetChanged()

                //sumAmount가 0일 경우 대처할 if문 필요
                val degree : Float
                if (sumAmount == 0f) {
                    // sumAmount가 0일 때 degree값이 nan는 에러 해결 코드
                    degree = 0f
                }
                else{
                    degree = (sumAlcohol / sumAmount)
                }
                val degreeToFixed = String.format("%.2f", degree)


                //결과 내용
                val tmp: String = "총 양 " + sumAmount.toString() + "도수 " + degreeToFixed
                resultView.text = tmp

            }
        }
    }
    //뒤로가기 눌렀을 때 종료되도록 함. 홈,탐색 버튼으로는 종료되지 않음
    override fun onBackPressed() {
        super.onBackPressed()
        Log.d("Help", "App is Down")
        finish()
    }
}

// 총 양이 .0이면 Int처럼 소수점이면 소수점으로. -> 채윤이가 해결할 것
