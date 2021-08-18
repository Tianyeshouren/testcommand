package com.example.netested.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.netested.R
import com.example.netested.adapter.GetList
import com.example.netested.adapter.OuterAdapter

class Fragment2:Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment2,null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        view.findViewById<RecyclerView>(R.id.rv).apply {
            adapter = OuterAdapter(GetList.getItemList())
            layoutManager = LinearLayoutManager(context)
            //isNestedScrollingEnabled = false



        }
    }

    companion object {
        fun newInstance()  =
             Fragment2()

    }
}