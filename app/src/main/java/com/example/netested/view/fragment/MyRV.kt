package com.example.netested.view.fragment

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView

class MyRV(context: Context,attributeSet: AttributeSet):RecyclerView(context,attributeSet) {
    private var my = 0f


    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        Log.e("ns","内层在分派事件")
        return super.dispatchTouchEvent(ev)
    }

    override fun onTouchEvent(e: MotionEvent?): Boolean {
        Log.e("ns","内层在处理事件")
        return super.onTouchEvent(e)
    }

    override fun onInterceptTouchEvent(e: MotionEvent?): Boolean {
        Log.e("rv", "我外层准备拦截事件${e?.action}")
        val innerll = (getChildAt(childCount-1))
        if(innerll is LinearLayout) {
            val innerRv  = innerll.getChildAt(1)
        if (!canScrollVertically(1)) {
            when (e?.action)
            {
                MotionEvent.ACTION_DOWN ->  {my = e?.rawY
                return false}
                MotionEvent.ACTION_MOVE -> {

                    if(e.rawY  < my)
                    {
                        return false
                    }
                    else {
                        if(!innerRv.canScrollVertically(-1))
                        {
                            Log.e("abc","sad")
                            return super.onInterceptTouchEvent(e)
                        }
                        return false
                    }

                }
                else -> return false

            }

        }}

        val b =  super.onInterceptTouchEvent(e)
        //val b = false
        Log.e("rv","拦截这个事件的结果是${b}")
        return b
    }
}