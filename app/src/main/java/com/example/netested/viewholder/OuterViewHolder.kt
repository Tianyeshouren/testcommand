package com.example.netested.viewholder

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.netested.R
import com.example.netested.adapter.InnerAdapter

class OuterViewHolder(view: View,val type:Int):RecyclerView.ViewHolder(view) {
    lateinit var  textView: TextView
    lateinit var rv:RecyclerView
    lateinit var bt:Button
    init {
        when(type){
            1 -> {
                textView = view.findViewById(R.id.tv)
                val lp = itemView.layoutParams
//                if(lp is StaggeredGridLayoutManager.LayoutParams)
//                {
//                    lp.isFullSpan = true
//                }
                bt = view.findViewById(R.id.button)

            }
            2 -> {
                rv = view.findViewById(R.id.rv)
            }
            3-> {

            }
        }
    }

}