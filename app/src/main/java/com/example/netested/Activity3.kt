package com.example.netested

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.netested.adapter.GetList
import com.example.netested.adapter.OuterAdapter
import com.example.netested.adapter.deco.XiDect
import com.example.netested.model.Item
import com.example.netested.view.MYLL
import com.example.netested.view.MytextView
import com.scwang.smart.refresh.layout.SmartRefreshLayout

/*
    下拉刷新以及普通刷新后的吸顶
    这个吸顶方案基本算是可行的一种方案了
 */
class Activity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ac3)
        var ll = findViewById<LinearLayout>(R.id.ll)
        //ll.scrollBy(0,200)
        var tv1 = findViewById<MytextView>(R.id.tv1)
        var tv2 = findViewById<MytextView>(R.id.tv2)
        findViewById<Button>(R.id.bt).setOnClickListener {
            findViewById<View>(R.id.ll).scrollBy(0,20)
            Log.e("ll","!! text1.bt = ${tv1.scaleY}, text1.top = ${tv1.top}" )
            Log.e("ll","!! text2.bt = ${tv2.scrollY}, text2.top = ${tv2.top}" )
        }







    }
}