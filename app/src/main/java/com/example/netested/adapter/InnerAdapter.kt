package com.example.netested.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.netested.R
import com.example.netested.viewholder.InnerViewHolder

class InnerAdapter(private val list:List<String>):RecyclerView.Adapter<InnerViewHolder>(){
    override fun getItemCount() = list.size
    override fun onBindViewHolder(holder: InnerViewHolder, position: Int) {
        Log.e("wjc","我内部rc开始绑定数据")
        holder.textView1.text = list[position]
        holder.textView2.text = list[position].reversed()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InnerViewHolder {
        Log.e("wjc","我内部rc开始创建数据")
        return InnerViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.title,parent,false))
    }
}