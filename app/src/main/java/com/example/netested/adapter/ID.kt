package com.example.netested.adapter

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class ID: RecyclerView.ItemDecoration() {
    val paint = Paint().apply { color = Color.BLUE }
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        Log.e("wjc","调用了getItemOffset这个函数")

        super.getItemOffsets(outRect, view, parent, state)
        outRect.bottom = 10
        outRect.left = 100
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        Log.e("wjc","ONDRAW")
        super.onDraw(c, parent, state)
        var ca = parent.childCount
        for(i in 0 until ca) {
            var child = parent.getChildAt(i)
            var cx  =100f
            var cy:Float = (child.top + child.height/2).toFloat()
            Log.e("wjc","ONDRAW+cy是${cy}")
            c.drawCircle(cx,cy,20f,paint)
        }
        }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        Log.e("wjc","OVER")
        super.onDrawOver(c, parent, state)
        var ca = parent.childCount
        for(i in 0 until ca) {
            var child = (parent.getChildAt(i))
            var cx  =10f
            var cy:Float = (child.top + child.height/2).toFloat()
            Log.e("wjc","over+cy是${cy}")
            if(parent.getChildAdapterPosition(child) % 3 == 0 && parent.getChildViewHolder(child).itemViewType == 3)
            c.drawCircle(cx,cy,20f,paint)
        }

    }

    }
