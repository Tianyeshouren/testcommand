package com.example.netested.model

open class Item(val type:Int) {
}

class ItemRc1(val content:String):Item(1)


class ItemRc2(val content:List<String>):Item(2)

class Title(val content:String):Item(3)