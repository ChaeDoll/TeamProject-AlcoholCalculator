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
        resultButton.setOnClickListener{
            val inputManager : InputMethodManager = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.hideSoftInputFromWindow(this.currentFocus?.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
        }
    }
}