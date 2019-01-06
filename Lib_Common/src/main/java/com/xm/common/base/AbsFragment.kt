package com.xm.common.base

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * fragment抽象基类
 */
abstract class AbsFragment : Fragment() {
    private var rootView: View? = null
    private var activity: Activity? = null
    private var isFirstVisible: Boolean = false

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        activity = context as Activity
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (rootView == null) {
            try {
                rootView = inflater.inflate(layoutId(), container, false)
                if (!isLazyLoad()) {
                    init()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    abstract fun isLazyLoad(): Boolean

    abstract fun layoutId(): Int

    abstract fun findViews(view: View?)

    abstract fun initEvent()

    abstract fun initData()

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isLazyLoad() && isVisibleToUser && !isFirstVisible) {
            onFragmentFirstVisible()
            isFirstVisible = true
            return
        }
        onFragmentVisibleChange(isVisibleToUser)
    }

    protected fun onFragmentVisibleChange(isVisibleToUser: Boolean) {

    }

    protected fun onFragmentFirstVisible() {
        init()
    }

    private fun init() {
        if (null != rootView) {
            findViews(rootView)
            initEvent()
            initData()
        }
    }
}