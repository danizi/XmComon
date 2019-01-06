package com.xm.common.demo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.xm.common.base.AbsRvAdapter
import com.xm.common.base.AbsViewHolder

class MainActivity : AppCompatActivity() {
    private var rv: RecyclerView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findView()
        initData()
    }

    private fun findView() {
        rv = findViewById(R.id.rv)
    }

    private fun initData() {
        rv?.layoutManager = LinearLayoutManager(this)
        val data = ArrayList<Any>()
        for (i in 0..8) {
            data.add(ItemTwoBean(i.toString()))
        }
        rv?.adapter = MyAdapter(data)
    }
}

class ItemOneBean(var des: String?)

class ItemTwoBean(var des: String?)

class MyViewHolder1(parent: View?) : AbsViewHolder(parent, R.layout.item_1) {

    override fun bind(data: Any, position: Int) {
        val tv = findViewById<TextView>(R.id.tv_1)
        tv?.text = "MyViewHolder1"
        tv?.setOnClickListener {
            Toast.makeText(context, "MyViewHolder1", Toast.LENGTH_LONG).show()
        }
    }
}

class MyViewHolder2(parent: View?) : AbsViewHolder(parent, R.layout.item_2) {

    override fun bind(data: Any, position: Int) {

    }
}

class MyAdapter(val data: List<Any>) : AbsRvAdapter(data) {

    override fun getViewType(position: Int): Int {
        if (data[position] is ItemOneBean) {
            return 1
        } else if (data[position] is ItemTwoBean) {
            return 2
        }
        return 1
    }

    override fun createHolder(parent: ViewGroup, viewType: Int): AbsViewHolder {
        return when (viewType) {
            2 -> {
                MyViewHolder2(parent)
            }
            else -> {
                MyViewHolder1(parent)
            }
        }
    }
}




