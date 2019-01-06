package com.xm.common.base

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.ViewGroup

/**
 * RecyclerView抽象适配器基类
 * D AbsViewHolder的绑定数据类型
 */
abstract class AbsRvAdapter(private var datas: List<Any>) : RecyclerView.Adapter<AbsViewHolder>() {

    /**
     * RecyclerView创建ViewHolder回调
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbsViewHolder {
        return createHolder(parent, viewType)
    }

    /**
     * RecyclerView item的数量
     */
    override fun getItemCount(): Int {
        if (datas.isEmpty()) {
            throw (Exception("RecyclerView datas is Empty"))
        }
        return datas.size
    }

    /**
     * RecyclerView 数据绑定
     */
    override fun onBindViewHolder(viewHolder: AbsViewHolder, position: Int) {
        bindHolder(viewHolder, position)
        showLog("viewHolder -  $viewHolder - position$position")
    }

    /**
     * RecyclerView itemView的类型回调
     */
    override fun getItemViewType(position: Int): Int {
        showLog(getViewType(position).toString())
        return getViewType(position)
    }

    /**
     * RecyclerView 附着到窗口
     */
    override fun onViewAttachedToWindow(holder: AbsViewHolder) {
        super.onViewAttachedToWindow(holder)
        showLog("onViewAttachedToWindow")
    }

    /**
     * RecyclerView 从窗口移除
     */
    override fun onViewDetachedFromWindow(holder: AbsViewHolder) {
        super.onViewDetachedFromWindow(holder)
        showLog("onViewDetachedFromWindow")
    }

    /**
     * 由子类选择ViewHolder类型
     */
    protected abstract fun getViewType(position: Int): Int

    /**
     * 由子类创建ViewHolder
     */
    protected abstract fun createHolder(parent: ViewGroup, viewType: Int): AbsViewHolder

    /**
     * 绑定数据,即单条数据传递到ViewHolder中
     */
    private fun bindHolder(holder: AbsViewHolder, position: Int) {
        try {
            holder.bind(datas[position], position)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun showLog(msg: String?) {
        Log.e("AbsRvAdapter", msg)
    }
}