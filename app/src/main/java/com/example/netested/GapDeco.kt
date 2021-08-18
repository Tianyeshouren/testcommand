package com.example.netested

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.netested.viewholder.OuterViewHolder

class GapDeco:RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val pos = parent.findContainingViewHolder(view)
        if(pos is OuterViewHolder)
        {
            if(pos.type!=3)
                outRect.set(0,2,0,1)
            var lm = parent.layoutManager
            if(lm is LinearLayoutManager && parent.findViewHolderForAdapterPosition(parent.adapter!!.itemCount-1) == pos)
            {

                outRect.set(100,20,100,100)
            }
        }

    }
}