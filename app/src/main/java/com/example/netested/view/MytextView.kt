package com.example.netested.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.util.Log

class MytextView(context:Context,attrs:AttributeSet):androidx.appcompat.widget.AppCompatTextView(context,attrs) {
    var a = 1;

    override fun onDraw(canvas: Canvas?) {
       // text = "12214135234"
        //super.onDraw(canvas)
        val paint = Paint()
        var rect = Rect(400,0,800,200)
        canvas!!.drawCircle(60f,100f,100f,paint)
        canvas!!.clipRect(rect)

        canvas!!.drawColor(Color.BLUE)
        paint.setColor(Color.GREEN)
        canvas!!.drawCircle(600f,100f,100f,paint)
        Log.e("wjc","draw,gaodu$height")
        //invalidate()


    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        Log.e("wjc","这里测量开始,宽度是${width}")
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val width = measuredWidth

        Log.e("wjc","这里测量结束,宽度是${width}")

    }


    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        val width = width
        val meawidth = measuredWidth
        Log.e("wjc","layout结束，宽度是${width},测量宽度是${meawidth},top是$top")


    }


    override fun setText(text: CharSequence?, type: BufferType?) {
        Log.e("wjc","这里是设置文字${text}")
        super.setText(text, type)

    }

    override fun onTextChanged(
        text: CharSequence?,
        start: Int,
        lengthBefore: Int,
        lengthAfter: Int
    ) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter)
        Log.e("wjc","这里是监听到文字改变，此时宽度为$width")
    }





}