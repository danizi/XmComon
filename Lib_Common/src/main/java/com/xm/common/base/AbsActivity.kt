package com.xm.common.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * 抽象Activity基类
 */
abstract class AbsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentViewBefore()
        setContentView(layoutId())
        findViews()
        initEvent()
        initData()
        ActivityManager.getInstance().registerActivityLifecycleCallbacks(application)
    }

    /**
     * 为窗口设置UI之前初始化窗口,例如去除标题栏
     */
    protected fun setContentViewBefore() {

    }

    /**
     * view find初始化
     */
    abstract fun findViews()

    /**
     * 监听初始化
     */
    abstract fun initEvent()

    /**
     * 初始化数据
     */
    abstract fun initData()

    /**
     * 获取id
     */
    abstract fun layoutId(): Int
}



