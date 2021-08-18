package com.example.netested

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.netested.adapter.GetList
import com.example.netested.adapter.OuterAdapter
import com.example.netested.adapter.deco.XiDect
import com.example.netested.model.Item
import com.example.netested.view.RVContainer
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.scwang.smart.refresh.layout.listener.ScrollBoundaryDecider

/*
    下拉刷新以及普通刷新后的吸顶
    这个吸顶方案基本算是可行的一种方案了
 */
class Activity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ac2)

        val sm:SmartRefreshLayout = findViewById(R.id.sm)
        sm.setEnableLoadMore(true)
        sm.setEnableRefresh(true)
        sm.setEnableNestedScroll(true)
        sm.setScrollBoundaryDecider(object:ScrollBoundaryDecider
        {
            override fun canLoadMore(content: View?): Boolean {
                val vi =  (content as FrameLayout).getChildAt(0) as RVContainer
                return !vi.rv2.canScrollVertically(1)

            }

            override fun canRefresh(content: View?): Boolean {
                val vi =  (content as FrameLayout).getChildAt(0) as RVContainer
                return vi.offset == 0 && !vi.rv1.canScrollVertically(-1)
            }
        })
        val data:MutableList<Item> = GetList.getItemList()
        val ada = OuterAdapter(data)

        val rv:RecyclerView = findViewById(R.id.rv)

        val rv2:RecyclerView = findViewById(R.id.rv2)


        rv.adapter = ada
        rv.layoutManager = LinearLayoutManager(this)
        rv.addItemDecoration(GapDeco())
//        val di = XiDect(data,rv,findViewById(R.id.header),findViewById(R.id.rvcontainer))
//        ada.id = di
//        rv.addItemDecoration(di)

        rv2.adapter = OuterAdapter(GetList.RV2getItemList())
        rv2.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)




        sm.setOnRefreshListener {
            if(it.isRefreshing)
            {
                data.clear()
                data.addAll(GetList.getItemList())
                ada.notifyDataSetChanged()
                //Thread.sleep(1000)
                it.finishRefresh(0)
               //di.needRefresh = true

            }
        }

        findViewById<Button>(R.id.bt1).setOnClickListener {
            findViewById<RVContainer>(R.id.rvcontainer).back()
        }







    }
}