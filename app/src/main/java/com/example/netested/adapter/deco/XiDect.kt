package com.example.netested.adapter.deco

import android.graphics.Canvas
import android.util.Log
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.netested.model.Item
import com.example.netested.model.Title
import com.example.netested.view.RVContainer

//vg只是放头的布局罢了
//想要头判断更加简单，则第一个rv的猜你喜欢一定要放满一页才行。
//要么放弃rvcontainer,选择rc里嵌套rv
class XiDect(val data:List<Item>,val rv:RecyclerView,
             val vg:FrameLayout,
             val rvContainer: RVContainer):RecyclerView.ItemDecoration() {
    var needRefresh = true

    val map = HashMap<Int,Int>()
    val map2 = HashMap<Int,Int>()
    val adapter = rv.adapter!!
    var curPos = 0
    var last = 999

    init {
//        var index = 0
//        for(i in 0 until data.size)
//        {
//            if(data[i] is Title)
//            {
//                map[i] = index
//                map2[index++] = i
//            }
//        }
//        val head = adapter.onCreateViewHolder(vg,3)
//        curPos = 0
//        adapter.onBindViewHolder(head,curPos)
//        vg.removeAllViews()
//        vg.addView(head.itemView)
//        vg.scrollTo(0,0)


    }

    fun pre():Int{

        if(map[curPos] == 0) return 0
        else return map2[map[curPos]!!-1 ]!!

    }
    fun refrensh()
    {
        Log.e("nn","开始刷新")

        needRefresh =false
        var index = 0
        for(i in data.indices)
        {
            if(data[i] is Title)
            {
                map[i] = index
                map2[index++] = i
                last = i
            }
        }
        val head = adapter.onCreateViewHolder(vg,3)
        curPos = 0
        adapter.onBindViewHolder(head,curPos)
        vg.removeAllViews()
        vg.addView(head.itemView)
        vg.scrollTo(0,0)



        val lm = rv.layoutManager
        if(lm is LinearLayoutManager) {

            var l = lm.findFirstVisibleItemPosition()
            var r = lm.findLastVisibleItemPosition()
            var find = false
            for (i in l..r)
                {
                    if(data[i] is Title){
                        find = true

                        var h = 0
                        var height = 0
                        val view = rv.findViewHolderForLayoutPosition(i)?.itemView
                        view?.let {
                            h = it.top
                            height = it.measuredHeight
                        }
                        //h += rvContainer.offset
                        if(i != curPos)
                        {


                            if(h <=0 )
                            {
                                val head = adapter.onCreateViewHolder(vg,3)
                                adapter.onBindViewHolder(head,i)
                                curPos = i
                                vg.removeAllViews()
                                vg.addView(head.itemView)
                                vg.scrollTo(0,0)
                                return

                            }


                            if(h > height)
                            {
                                val head = adapter.onCreateViewHolder(vg,3)
                                curPos = i
                                val p = pre()
                                adapter.onBindViewHolder(head,p)
                                curPos = p
                                vg.removeAllViews()
                                vg.addView(head.itemView)
                                vg.scrollTo(0,0)
                                return
                            }

                            if(h < height)
                            {
                                val head = adapter.onCreateViewHolder(vg,3)
                                curPos = i
                                val p = pre()
                                adapter.onBindViewHolder(head,p)
                                curPos = p
                                vg.removeAllViews()
                                vg.addView(head.itemView)
                                vg.scrollTo(0,height-h)
                                return
                            }


                        }


                    }



                }
            if(!find)
            {
                for(i in r downTo 0)
                {
                    if(data[i] is Title)
                    {
                        val head = adapter.onCreateViewHolder(vg,3)
                        adapter.onBindViewHolder(head,i)
                        curPos = i
                        vg.removeAllViews()
                        vg.addView(head.itemView)
                        vg.scrollTo(0,0)



                        return
                    }
                }


            }


        }



    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        Log.e("ok","还在画")
        if(needRefresh)  {refrensh()
            return }
        super.onDrawOver(c, parent, state)

        if(rvContainer.offset  == -rvContainer.rv1Height)
        {
            Log.e("ok","不显示了")

            vg.removeAllViews()
            vg.scrollTo(0,0)
            return
        }



        val lm = parent.layoutManager
        if(lm is LinearLayoutManager) {

            var l = lm.findFirstVisibleItemPosition()
            var r = lm.findLastVisibleItemPosition()
            Log.e("ok","l是$l,r是$r")
            for (i in l..r)
            {
                if(data[i] is Title){
                    if(i < curPos) {
                        Log.e("ok","我希望")
                        continue}

                    var h = 0
                    var height = 0
                    val view = parent.findViewHolderForLayoutPosition(i)?.itemView
                    view?.let {
                        h = it.top
                        height = it.measuredHeight
                    }
                    h += rvContainer.offset
                    if(i != curPos)
                    {


                        if(h <=0 )
                        {
                            val head = adapter.onCreateViewHolder(vg,3)
                            adapter.onBindViewHolder(head,i)
                            curPos = i
                            vg.removeAllViews()
                            vg.addView(head.itemView)
                            vg.scrollTo(0,0)
                            return

                        }
                        if(h < height)
                        {
                            vg.scrollTo(0,height-h)
                            return
                        }


                    }
                    else if(i == curPos)
                    {
                        if(h > 0)
                        {
                            Log.e("nn","pre = ${pre()}")
                            curPos = pre()
                            val head = adapter.onCreateViewHolder(vg,3)
                            adapter.onBindViewHolder(head,curPos)

                            vg.removeAllViews()
                            vg.addView(head.itemView)
                            return
                        }

                    }


                }



            }
            //到这里都是没找到下一个标题的，或者是h>height的
            vg.scrollTo(0,0)
            if(curPos == last)
            {

            }





        }




    }
}