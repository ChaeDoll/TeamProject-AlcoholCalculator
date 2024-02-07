package com.example.alcoholcalculator

import android.content.Context
import android.text.Editable
import android.text.Selection
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.text.set
import androidx.core.widget.addTextChangedListener

class CalcListViewAdapter(private val context:Context, private val calcItems: ArrayList<CalcItem>):BaseAdapter() {

    override fun getCount(): Int = calcItems.size //list 전체 크기를 반환


    override fun getItem(position: Int): CalcItem = calcItems[position] //해당 위치의 요소 반환


    override fun getItemId(position: Int): Long = position.toLong() // 해당 위치 요소 id 반환

    override fun getView(position: Int, view: View?, parent: ViewGroup?): View? { //list_item.xml의 view와 데이터간의 연동이 이루어짐
        // parent가 상위 layout

        var convertView = view
        val viewHolder: CalcViewHolder
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.test_calc_list_item, parent, false) //test용도
            viewHolder = CalcViewHolder()
            viewHolder.ref = position
            viewHolder.listViewMaterial = convertView.findViewById<EditText>(R.id.listViewMaterial)
            viewHolder.listViewDegree = convertView.findViewById<EditText>(R.id.listViewDegree)
            viewHolder.listViewAmount = convertView.findViewById<EditText>(R.id.listViewAmount)
            viewHolder.listViewButton = convertView.findViewById<Button>(R.id.listViewButton)
            convertView.tag = viewHolder
        } else {
            viewHolder = convertView.tag as CalcViewHolder
            viewHolder.ref = position
            viewHolder.listViewMaterial.setText(calcItems[viewHolder.ref].material_)
            viewHolder.listViewDegree.setText("${calcItems[viewHolder.ref].degree_}")
            viewHolder.listViewAmount.setText("${calcItems[viewHolder.ref].amount_}")
        }

        //삭제 함수
        viewHolder.listViewButton.setOnClickListener {
//            Log.d("viewManager", viewHolder.ref.toString() + "번 Item을 삭제합니다")
            if(calcItems.size>2) {
                calcItems.removeAt(viewHolder.ref)
                this.notifyDataSetChanged()
            }
            else{
                viewHolder.listViewMaterial.setText("")
                viewHolder.listViewAmount.setText("")
                viewHolder.listViewDegree.setText("")
            }
            // - 버튼을 눌렀을 때는 스크롤 고정
            val parentList = parent?.findViewById<ListView>(R.id.listview)
            parentList?.transcriptMode = ListView.TRANSCRIPT_MODE_NORMAL
        }
        //Material EditText 변경 이벤트
        viewHolder.listViewMaterial.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                calcItems[viewHolder.ref].material_ = viewHolder.listViewMaterial.text.toString()
            }
        })
        //Degree EditText 변경 이벤트
        viewHolder.listViewDegree.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
            override fun afterTextChanged(s: Editable?) {
                Selection.setSelection(s, s!!.length)
                if(s.contentEquals(".")){
                    Log.d("잡앗따.","점")
                    calcItems[viewHolder.ref].degree_ = "0."
                    return notifyDataSetChanged()
                }
                calcItems[viewHolder.ref].degree_ = viewHolder.listViewDegree.text
            }
        })
        //Amount EditText 변경 이벤트
        viewHolder.listViewAmount.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                Selection.setSelection(s, s!!.length)
                if(s.contentEquals(".")){
                    Log.d("잡앗따.","점")
                    calcItems[viewHolder.ref].amount_ = "0."
                    return notifyDataSetChanged()
                }
                calcItems[viewHolder.ref].amount_ = viewHolder.listViewAmount.text
            }
        })
        return convertView
    }
}
// 총 양이 .0이면 Int처럼 소수점이면 소수점으로. -> 채윤이가 해결할 것
