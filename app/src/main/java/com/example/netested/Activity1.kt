package com.example.netested

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.netested.adapter.GetList
import com.example.netested.adapter.OuterAdapter
import com.example.netested.adapter.deco.XiDect
import com.example.netested.model.Item
import com.scwang.smart.refresh.layout.SmartRefreshLayout

/*
    下拉刷新以及普通刷新后的吸顶
    这个吸顶方案基本算是可行的一种方案了
 */
class Activity1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ac1)

        val sm:SmartRefreshLayout = findViewById(R.id.sm)
        val data:MutableList<Item> = GetList.getItemList()
        val ada = OuterAdapter(data)

        val rv:RecyclerView = findViewById(R.id.rv)


        rv.adapter = ada
        rv.layoutManager = LinearLayoutManager(this)
        rv.addItemDecoration(GapDeco())
        val di = XiDect(data,rv,findViewById(R.id.header),findViewById(R.id.header))
        ada.id = di
        rv.addItemDecoration(di)


        sm.setOnRefreshListener {
            if(it.isRefreshing)
            {
                data.clear()
                data.addAll(GetList.getItemList())
                ada.notifyDataSetChanged()
                //Thread.sleep(1000)
                it.finishRefresh(0)
                di.needRefresh = true

            }
        }







    }
}