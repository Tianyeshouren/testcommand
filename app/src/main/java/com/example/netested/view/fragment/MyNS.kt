package com.example.netested.view.fragment

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.widget.LinearLayout
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.RecyclerView

class MyNS(context: Context, attributeSet: AttributeSet):NestedScrollView(context,attributeSet) {
    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        Log.e("ns", "我外层准备分发事件${ev?.action}")
       val b = super.dispatchTouchEvent(ev)
        Log.e("ns", "我外层分发事件结果为${b}")
        return b
    }

    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        Log.e("ns", "我外层准备处理事件${ev?.action}")
        val b =  super.onTouchEvent(ev)
        Log.e("ns", "我外层处理事件结果为${b}")
        return b
    }

    override fun onInterceptTouchEvent(e: MotionEvent?): Boolean {
        Log.e("ns", "我外层准备拦截事件${e?.action}")

        val b =  super.onInterceptTouchEvent(e)
        //val b = false
        Log.e("ns","拦截这个事件的结果是${b}")
        return b
    }
}