package com.example.netested.view

import android.app.Notification
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.view.NestedScrollingChild2
import androidx.core.view.NestedScrollingChildHelper
import androidx.core.view.NestedScrollingParent2
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.netested.view.fragment.MyRV2
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext


class RVContainer(context: Context, attributeSet: AttributeSet):LinearLayout(context,attributeSet),
    NestedScrollingParent2,NestedScrollingChild2, CoroutineScope {
    private val job = Job()

    override val coroutineContext: CoroutineContext
    get() = Dispatchers.Main + job

    fun destroy() {
        job.cancel()
    }

    var j: Job? = null
    fun back()
    {

        j = launch {

            Log.e("tt","offset = $offset")
            if(offset <0) {
                rv2.back()
                Log.e("tt", "rv2滚动完毕")
            }
            scrollTo(0,0)
            offset = 0
            delay(16)
            rv1.back()

            Log.e("tt","rv1滚动完毕")
            val toast = Toast.makeText(
                context,
                "默认的Toast",
                Toast.LENGTH_SHORT
            )
            toast.show()
            ((this@RVContainer.parent as ViewGroup).parent  as SmartRefreshLayout).autoRefresh()
            j = null



        }
    }

    var offset = 0
    lateinit var rv1fl: FrameLayout
    lateinit var rv1:MyRV2
    lateinit var rv2:MyRV2
    lateinit var fl:FrameLayout
    lateinit var rv:RecyclerView
    var rv1Height = 0
    val nc = NestedScrollingChildHelper(this)
    lateinit var sm: SmartRefreshLayout

    override fun onFinishInflate() {
        super.onFinishInflate()
      //  nc.isNestedScrollingEnabled

    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        if(ev!!.action == 0)
        {
            if(j != null)
            {
                Log.e("tt","job还存在，我直接进行一个cancel")
                j!!.cancel()
                j =null
               // return true
            }

        }
        return super.onInterceptTouchEvent(ev)

    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        //sm = parent as SmartRefreshLayout
        //sm.setEnableLoadMore(false)
        //sm.setEnableRefresh(false)
        rv1fl = getChildAt(0) as FrameLayout
        rv1Height = rv1fl.measuredHeight
        rv2 = getChildAt(1) as MyRV2
        rv1 = rv1fl.getChildAt(0) as MyRV2


        var h = MeasureSpec.getSize(heightMeasureSpec)
        Log.e("rr","h为${h}")
        var mh = MeasureSpec.makeMeasureSpec(h,MeasureSpec.EXACTLY)
        //rv1fl.measure(widthMeasureSpec,mh)
        rv2.measure(widthMeasureSpec,mh)



    }

    override fun onNestedPreScroll(target: View, dx: Int, dy: Int, consumed: IntArray, type: Int) {
        dispatchNestedPreScroll(dx, dy, consumed, null, type)
        //dy 大于等等于0 表示向上移动，下面的内容要出来





        Log.e("rr","偏移=${offset}, dy=${dy}")
        rv1.invalidate()
        if(target == rv1) {
            if (offset == 0 && dy <= 0) {
                return
            }
            if (offset < 0 && dy <= 0) {


                    var consume = Math.min(-offset,-dy)
                    consumed[1] = -consume
                    scrollBy(0,-consume)
                    offset += consume
                    return


            }

            if (offset <=0 && offset > -rv1Height && dy > 0)
            {
                if(rv1.canScrollVertically(1))
                {
                    return
                }
                else
                {Log.e("rr","不能继续下拉了")

                    var consume = Math.min(rv1Height + offset,dy)
                    consumed[1] = consume
                    scrollBy(0,consume)
                    offset -= consume
                    Log.e("rr","消费为${consume}")
                    return

                }

            }

//            if(offset == -rv1Height && )
//            return


        }
        else {
            if(target == rv2) {
                if (offset == 0 && dy <= 0) return
                if (offset < 0 && dy <= 0) {

                    if(rv2.canScrollVertically(-1)) {
                        return
                    }


                    var consume = Math.min(-offset,-dy)
                    consumed[1] = -consume
                    scrollBy(0,-consume)
                    offset += consume

                    return


                }


                if (offset <=0 && offset > -rv1Height && dy > 0)
                {

                    if(rv2.canScrollVertically(1))
                    { Log.e("sm","en???")
                        var consume = Math.min(rv1Height + offset,dy)
                        consumed[1] = consume
                        scrollBy(0,consume)
                        offset -= consume
                        Log.e("rr","rv2，父消费为${consume}")
                        return
                    }
                    else
                    {
                        Log.e("sm","en?")
                        //sm.setEnableLoadMore(true)
                    }

                }

//            if(offset == -rv1Height && )
//            return


            }

        }
//        if(dy >=0) {
//            if (offset < 0 && offset >= -rv1Height) {
//                var consume = Math.min(rv1Height + offset,dy)
//                offset -= consume
//                consumed[1] = consume
//                scrollBy(0,consume)
//            }
//            else {
//                consumed[1] = 0
//            }
//
//
//        }
//        else {
//            if (offset < 0) {
//                var consume = Math.min(-offset,-dy)
//                offset += consume
//                consumed[1] = -consume
//                scrollBy(0,-consume)
//            }
//            else {
//                consumed[1] = 0
//            }
//
//        }
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
        val e = startNestedScroll(ViewCompat.SCROLL_AXIS_VERTICAL, type)
        Log.e("ns","结果是$e")
    }

    override fun onStartNestedScroll(child: View, target: View, axes: Int, type: Int): Boolean {

        return true
        if (target == rv1) {
            if(axes == SCROLL_AXIS_VERTICAL )
                return true
        }
        if (target == rv2)
            if(axes == SCROLL_AXIS_VERTICAL )
                return true

        return false
    }

    override fun onStopNestedScroll(target: View, type: Int) {



    }

   override fun startNestedScroll(axes: Int, type: Int): Boolean {
        return nc.startNestedScroll(axes,type)
    }

      override fun stopNestedScroll(type: Int) {
        return nc.stopNestedScroll(type)
    }

     override fun hasNestedScrollingParent(type: Int): Boolean {
        return nc.hasNestedScrollingParent(type)
    }

      override fun dispatchNestedScroll(
        dxConsumed: Int,
        dyConsumed: Int,
        dxUnconsumed: Int,
        dyUnconsumed: Int,
        offsetInWindow: IntArray?,
        type: Int
    ): Boolean {
        return nc.dispatchNestedScroll(dxConsumed,dyConsumed,dxUnconsumed,dyUnconsumed,offsetInWindow,type)
    }

     override fun dispatchNestedPreScroll(
        dx: Int,
        dy: Int,
        consumed: IntArray?,
        offsetInWindow: IntArray?,
        type: Int
    ): Boolean {
        return  nc.dispatchNestedPreScroll(dx,dy,consumed,offsetInWindow,type)
        }


}