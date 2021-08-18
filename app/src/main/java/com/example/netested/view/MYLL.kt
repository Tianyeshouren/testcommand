package com.example.netested.view

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.NestedScrollingParent2
import androidx.recyclerview.widget.RecyclerView
import com.example.netested.R
import com.scwang.smart.refresh.layout.SmartRefreshLayout

class MYLL(context: Context, attributeSet: AttributeSet):LinearLayout(context,attributeSet){
    lateinit var tv1:TextView
    lateinit var tv2:TextView
    init {
        setWillNotDraw(false)
    }




    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        tv1 = findViewById(R.id.tv1)
        tv2 = findViewById(R.id.tv2)
        Log.e("ll","text1 = ${tv1.measuredHeight},text2 = ${tv2.measuredHeight} " )




//        head = getChildAt(0)
//        headHeight = head.measuredHeight
//        stickHeight = stickHeight
 //       stick = getChildAt(1)




    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        super.onLayout(changed, l, t, r, b)
        //getChildAt(0).layout(-100,0,r,1000)
        Log.e("ll","!! text1.bt = ${tv1.bottom}, text1.top = ${tv1.top}" )



    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        Log.e("ll","!! text1.bt = ${tv1.bottom}, text1.top = ${tv1.top}" )
    }



}