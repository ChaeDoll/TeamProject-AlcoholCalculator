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
        resultButton.setOnClickListener{
            //계산결과 버튼누르면 키보드 숨김
            val inputManager : InputMethodManager = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.hideSoftInputFromWindow(this.currentFocus?.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
            
            var sumAmount : Float = 0f // 총 양
            var sumDegree  : Float = 0f // 양 * 도수의 총 합
            for(i in 0 .. calcList.size-1 step(1)){
                //a나 b가 빈 값이면 안됨
                val a = calcList.get(i).amount_.toString()
                val b = calcList.get(i).degree_.toString()
                sumAmount += (a.toFloat())
                sumDegree += (a.toFloat()) * (b.toFloat())
            }

            val degree : Float = (sumDegree/sumAmount)
            val c = String.format("%.2f",degree)

            val result : String = "총 양 " + sumAmount.toString() + "도수 "+c
            resultView.setText(result)
        }
    }
    //뒤로가기 눌렀을 때 종료되도록 함. 홈,탐색 버튼으로는 종료되지 않음
    override fun onBackPressed() {
        super.onBackPressed()
        Log.d("Help", "App is Down")
        finish()
    }
}