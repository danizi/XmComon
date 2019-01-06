package com.xm.common.base

import android.app.Activity
import android.app.Application
import android.os.Bundle
import java.lang.ref.WeakReference
import java.util.*

/**
 * Activity管理类
 */
class ActivityManager : Application.ActivityLifecycleCallbacks {

    private val weakReferences = LinkedList<WeakReference<Activity>>()
    private var isRegisterActivityLifecycleCallbacks = false

    fun registerActivityLifecycleCallbacks(app: Application?) {
        if (!isRegisterActivityLifecycleCallbacks) {
            app?.registerActivityLifecycleCallbacks(this)
        }
    }

    fun unregisterActivityLifecycleCallbacks(app: Application?) {
        if (isRegisterActivityLifecycleCallbacks) {
            app?.unregisterActivityLifecycleCallbacks(this)
        }
    }

    override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
        weakReferences.add(WeakReference<Activity>(activity))
    }

    override fun onActivityPaused(activity: Activity?) {

    }

    override fun onActivityResumed(activity: Activity?) {

    }

    override fun onActivityStarted(activity: Activity?) {

    }

    override fun onActivityDestroyed(activity: Activity?) {

    }

    override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {

    }

    override fun onActivityStopped(activity: Activity?) {

    }

    companion object {
        fun getInstance(): ActivityManager {
            return ActivityManager()
        }
    }

    interface IActivityManager {
        fun finish(activity: Activity?)
        fun finishAll()
        fun isExist(className: String?)
        fun getTopActivity(): Activity?
    }
}