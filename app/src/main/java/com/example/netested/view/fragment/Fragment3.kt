package com.example.netested.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.netested.R
import com.example.netested.adapter.GetList
import com.example.netested.adapter.ID
import com.example.netested.adapter.OuterAdapter

class Fragment3:Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment3,null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        view.findViewById<MyRV>(R.id.rv).apply {
            adapter = OuterAdapter(GetList.getItemList())
            layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
            isNestedScrollingEnabled = false
            addItemDecoration(ID())


        }
    }

    companion object {
        fun newInstance()  =
             Fragment3()

    }
}