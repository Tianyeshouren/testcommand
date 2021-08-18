package com.example.netested.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.netested.R

class InnerViewHolder(view:View):RecyclerView.ViewHolder(view) {
    var textView1: TextView = view.findViewById(R.id.tv1)
    var textView2: TextView = view.findViewById(R.id.tv2)


}