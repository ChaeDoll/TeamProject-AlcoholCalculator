package com.example.alcoholcalculator

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class CalcListViewAdapter(private val context:Context, private val calcItems: ArrayList<CalcItem>):BaseAdapter() {
//    private var listBinding: ListItemBinding?= null
//    private val lbinding get() = listBinding!!

    override fun getCount(): Int = calcItems.size //list 전체 크기를 반환


    override fun getItem(position: Int): CalcItem = calcItems[position] //해당 위치의 요소 반환


    override fun getItemId(position: Int): Long = position.toLong() // 해당 위치 요소 id 반환


    override fun getView(position: Int, converView: View?, parent: ViewGroup?): View { //list_item.xml의 view와 데이터간의 연동이 이루어짐
        val view : View = LayoutInflater.from(context).inflate(R.layout.calc_list_item, parent,false)

        val listViewMaterial = view.findViewById<EditText>(R.id.listViewMaterial)
        val listViewDegree = view.findViewById<EditText>(R.id.listViewDegree)
        val listViewAmount = view.findViewById<EditText>(R.id.listViewAmount)
        val listViewButton = view.findViewById<Button>(R.id.listViewButton)
        val text1 = view.findViewById<TextView>(R.id.listViewText1)
        val text2 = view.findViewById<TextView>(R.id.listViewText2)

        val calc = calcItems[position]
        listViewMaterial.setText(calc.material_)
        listViewDegree.setText("${calc.degree_}") //얘는 int로 했고
        listViewAmount.setText("${calc.amount_}") //얘는 float로 하는 방법을 해봄

        return view

    }
}
