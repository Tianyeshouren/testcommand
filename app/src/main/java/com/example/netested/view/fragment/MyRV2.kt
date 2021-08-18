package com.example.netested.view.fragment

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.delay
import kotlin.concurrent.thread
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class MyRV2(context: Context, attributeSet: AttributeSet):RecyclerView(context,attributeSet) {

    var hasResume = false
    private var ls  = object :RecyclerView.OnScrollListener(){
         var ct:Continuation<Boolean>? = null
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            Log.e("tt","在动了")
            super.onScrolled(recyclerView, dx, dy)
            if(!recyclerView.canScrollVertically(-1))
            {
                if(ct !=null && !hasResume) {
                    hasResume = true
                    ct!!.resume(true)
                }

            }
        }

    }
    init {
        addOnScrollListener(ls)
    }
   suspend fun back() = suspendCoroutine<Boolean> {
       if (!this.canScrollVertically(-1)) {it.resume(true)

       }
       else {
           smoothScrollToPosition(0)

           ls.ct = it
           hasResume = false
       }

   }














   }

