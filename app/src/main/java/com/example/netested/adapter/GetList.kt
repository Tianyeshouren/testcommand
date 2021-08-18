package com.example.netested.adapter

import com.example.netested.model.Item
import com.example.netested.model.ItemRc1
import com.example.netested.model.ItemRc2
import com.example.netested.model.Title

object GetList {
    fun getItemList():MutableList<Item> {
        val list = ArrayList<Item>()

        for(i in 1 .. (1..9).random()) {
            val c = "$i${(1..10).random()}"
            list.add(Title(c))
            for(j in 1 .. (1..1).random()) {
                list.add(ItemRc1(" $c.$j "))
            }
        }
            list.add(getIemRV3())
       // list.add(getIemRV2())
        return list

    }
    fun RV2getItemList():MutableList<Item>
    {
        val list = ArrayList<Item>()

        for(i in 1..40) {
            val c = "RV2 +$i"
            list.add(ItemRc1(c))

        }
        return list
    }

    private fun getIemRV2(): ItemRc2 {
        val list = ArrayList<String>()
        for(i in 1..40){
            list.add(i.toString())
        }
        return ItemRc2(list)
    }

    private fun getIemRV1(): ItemRc1 {

        return  ItemRc1( (1..34325431).random().toString())
    }
    private fun getIemRV3(): Title {

        return  Title( (9999..34325431).random().toString())
    }
}