package com.example.alcoholcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import com.example.alcoholcalculator.databinding.ActivityMainBinding
import com.example.alcoholcalculator.databinding.CalcListItemBinding

class MainActivity : AppCompatActivity() {
//    binding
    private var mainBinding: ActivityMainBinding?= null
    private val mbinding get() = mainBinding!!
    private var cistBinding: CalcListItemBinding?= null
    private val cbinding get() = cistBinding!!

//    변수
    private lateinit var calclistView : ListView
    val calcList = arrayListOf<CalcItem>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // activity_main.xml 연결
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        // activity_main.xml 기본 view로 설정
        setContentView(mbinding.root)
        //calc_list_item.xml 연결
        cistBinding = CalcListItemBinding.inflate(layoutInflater)

        //calcListViewAdapter.kt 연결
        val calcAdapter = CalcListViewAdapter(this, calcList)

        // activity_main.xml의 요소인 listview를 calclistView와 연결
        // 여기서의 calclistView는 요소 추가, 요소 삭제, 수정 등의 작업을 함
        calclistView = mbinding.listview
        calclistView.adapter = calcAdapter

        val addButton : Button = mbinding.add
        addButton.setOnClickListener {
            var count : Int
            count = calcAdapter.count
            calcList.add(count,CalcItem("",0f,0f))
            calcAdapter.notifyDataSetChanged()
        }


    }
}