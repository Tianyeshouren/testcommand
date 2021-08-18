package com.example.netested.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.netested.R
import com.example.netested.adapter.deco.XiDect
import com.example.netested.model.Item
import com.example.netested.model.ItemRc1
import com.example.netested.model.ItemRc2
import com.example.netested.model.Title
import com.example.netested.viewholder.OuterViewHolder

class OuterAdapter(val list:MutableList<Item>):RecyclerView.Adapter<OuterViewHolder>() {
    lateinit var id:XiDect
    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OuterViewHolder {
        Log.e("wjc","我外部rc开始创建数据")
        val ll = LayoutInflater.from(parent.context)
        var view: View = ll.inflate(R.layout.item_rv1,parent,false)
        when(viewType){
            1 ->  {
                Log.e("wjc","创建的是普通视图")
               view = ll.inflate(R.layout.item_rv1,parent,false)

            }
            2 -> {
                Log.e("wjc","创的是rc视图")
                view = ll.inflate(R.layout.item_rv2,parent,false)
               // (view as RecyclerView).isNestedScrollingEnabled =false
            }
            3 -> {
                view = ll.inflate(R.layout.title,parent,false)
            }
        }
        return OuterViewHolder(view,viewType)
    }

    override fun onBindViewHolder(holder: OuterViewHolder, position: Int) {
        Log.e("wjc","我外部rc开始绑定数据")
        when(holder.type) {
            1 -> {
                Log.e("wjc","绑定的是普通数据")
                holder.textView.text = (list[position] as ItemRc1).content
                holder.bt.setOnClickListener {

                    up() }


            }
            2 ->{
                Log.e("wjc","绑定的是列表数据")
                ((holder.rv)).apply {
                    adapter = InnerAdapter((list[position] as ItemRc2).content)
                    layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
                }
            }
            3-> {
                holder.itemView.findViewById<TextView>(R.id.tv1).text = (list[position] as Title).content
                holder.itemView.findViewById<TextView>(R.id.tv2).text = (list[position] as Title).content
            }
        }

    }

    fun up()
    {list.clear()
        list.addAll(GetList.getItemList())
        this.notifyDataSetChanged()
        id?.needRefresh = true



    }


    override fun getItemViewType(position: Int): Int {
        return list.get(position).type
    }
}