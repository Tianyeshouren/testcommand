package com.example.netested.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.core.view.NestedScrollingParent2
import androidx.recyclerview.widget.RecyclerView
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

class MYVG(context: Context,attributeSet: AttributeSet):LinearLayout(context,attributeSet),
    NestedScrollingParent2, CoroutineScope {
    private val job = Job()

    override val coroutineContext: CoroutineContext
    get() = Dispatchers.Main + job

    fun destroy() {
        job.cancel()
    }

    var offset = 0
    lateinit var head:View
    lateinit var stick:View
    lateinit var fl:FrameLayout
    lateinit var rv:RecyclerView
    var headHeight = 0
    var stickHeight = 0



    override fun onFinishInflate() {
        super.onFinishInflate()
        head = getChildAt(0)
        headHeight = head.measuredHeight
        stickHeight = stickHeight
        stick = getChildAt(1)

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
//        head = getChildAt(0)
//        headHeight = head.measuredHeight
//        stickHeight = stickHeight
 //       stick = getChildAt(1)
        fl = (getChildAt(2) as ViewGroup).getChildAt(0) as FrameLayout
        rv = fl.getChildAt(0) as RecyclerView
        var h = MeasureSpec.getSize(heightMeasureSpec) + stickHeight
        var mh = MeasureSpec.makeMeasureSpec(h,MeasureSpec.EXACTLY)
        fl.measure(widthMeasureSpec,mh)




    }

    override fun onNestedPreScroll(target: View, dx: Int, dy: Int, consumed: IntArray, type: Int) {

        if(dy >=0) {
            if (offset < 0 && offset >= -headHeight) {
                var consume = Math.min(headHeight + offset,dy)
                offset -= consume
                consumed[1] = consume
                scrollBy(0,consume)
            }
            else {
                consumed[1] = 0
            }


        }
        else {
            if (offset < 0) {
                var consume = Math.min(-offset,-dy)
                offset += consume
                consumed[1] = -consume
                scrollBy(0,-consume)
            }
            else {
                consumed[1] = 0
            }

        }
    }



    override fun onNestedScroll(
        target: View,
        dxConsumed: Int,
        dyConsumed: Int,
        dxUnconsumed: Int,
        dyUnconsumed: Int,
        type: Int
    ) {
        return
    }

    override fun onNestedScrollAccepted(child: View, target: View, axes: Int, type: Int) {
        return
    }

    override fun onStartNestedScroll(child: View, target: View, axes: Int, type: Int): Boolean {
        if(offset == 0)
            return true

        return true
    }

    override fun onStopNestedScroll(target: View, type: Int) {
        return

    }

    fun back()
    {

    }

}