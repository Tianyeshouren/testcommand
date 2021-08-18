package com.example.netested

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.netested.view.MytextView
import com.example.netested.view.fragment.Fragment1
import com.example.netested.view.fragment.Fragment2
import com.example.netested.view.fragment.Fragment3

/*
    fragment1 是rc嵌套一个rc
    fragment2 是一个coor
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




        val container = findViewById<FrameLayout>(R.id.container)
        val mmmm = findViewById<MytextView>(R.id.mtv)
        val fm = supportFragmentManager
        val fragment1 = Fragment1()
        val fragment1Button = findViewById<Button>(R.id.fragment1).apply {
            setOnClickListener {

                fm.beginTransaction().replace(R.id.container, Fragment1.newInstance()).commitNow()
            }
        }
        val fragment1Button2 = findViewById<Button>(R.id.fragment2).apply {
            setOnClickListener {

                fm.beginTransaction().replace(R.id.container, Fragment2.newInstance()).commitNow()
            }
        }
        val fragment1Button3 = findViewById<Button>(R.id.fragment3).apply {
            setOnClickListener {

                fm.beginTransaction().replace(R.id.container, Fragment3.newInstance()).commitNow()

            }
        }
        val ac1Button = findViewById<Button>(R.id.ac1).apply {
            setOnClickListener {

                startActivity(Intent(this@MainActivity,Activity1::class.java))

            }
        }
        val ac2Button = findViewById<Button>(R.id.ac2).apply {
            setOnClickListener {

                startActivity(Intent(this@MainActivity,Activity2::class.java))

            }
        }
        val ac3Button = findViewById<Button>(R.id.ac3).apply {
            setOnClickListener {

                startActivity(Intent(this@MainActivity,Activity3::class.java))

            }
        }


    }
}