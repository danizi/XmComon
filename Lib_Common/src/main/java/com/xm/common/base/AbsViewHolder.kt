package com.xm.common.base

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * 抽象ViewHolder基类
 */
abstract class AbsViewHolder(view: View?, id: Int?) : RecyclerView.ViewHolder(getItemView(view, id)) {

    /**
     * 数据绑定
     * @data 数据
     * @position item位置
     */
    abstract fun bind(data: Any, position: Int)

    companion object {
        @SuppressLint("StaticFieldLeak")
        var context: Context? = null
        @SuppressLint("StaticFieldLeak")
        var itemView: View? = null
        private var views: SparseArray<View> = SparseArray()
        private var count = 0

        /**
         * 通过id获取ItemView的控件
         */
        fun <V : View> findViewById(id: Int): V? {
            var view: View? = views.get(id)
            if (view == null) {
                view = itemView?.findViewById(id)
                views.put(count, view)
                count++
            }
            return (view as V)
        }

        /**
         * 获取RecyclerView itemView
         * @param parent createHolder(...)的父布局
         * @layoutID ItemView资源id
         */
        fun getItemView(parent: View?, layoutID: Int?): View {
            context = parent?.context
            itemView = LayoutInflater.from(context).inflate(layoutID!!, parent as ViewGroup, false)
            if (itemView == null) {
                throw(Exception("itemView is null"))
            }
            return itemView!!
        }
    }
}