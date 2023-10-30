package com.example.alcoholcalculator

import android.content.Context
import android.util.Log
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


    override fun getView(position: Int, view: View?, parent: ViewGroup?): View? { //list_item.xml의 view와 데이터간의 연동이 이루어짐

        var convertView = view
        var viewHolder: CalcViewHolder
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.calc_list_item, parent,false)
            viewHolder = CalcViewHolder()
            viewHolder.listViewMaterial = convertView.findViewById<EditText>(R.id.listViewMaterial)
            viewHolder.listViewDegree = convertView.findViewById<EditText>(R.id.listViewDegree)
            viewHolder.listViewAmount = convertView.findViewById<EditText>(R.id.listViewAmount)
            viewHolder.listViewButton = convertView.findViewById<Button>(R.id.listViewButton)

            convertView.tag=viewHolder
        }
        else{
            viewHolder = convertView.tag as CalcViewHolder
            viewHolder.ref = position
        }


        val calc = calcItems[position]
        viewHolder.listViewAmount?.setText(calc.material_)

        viewHolder.listViewDegree?.setText("${calc.degree_}") //얘는 int로 했고
        viewHolder.listViewAmount?.setText("${calc.amount_}") //얘는 float로 하는 방법을 해봄

        //        삭제 함수

        viewHolder.listViewButton.setOnClickListener {
            calcItems.removeAt(viewHolder.ref)
            this.notifyDataSetChanged()

        }


        return convertView

    }
}
