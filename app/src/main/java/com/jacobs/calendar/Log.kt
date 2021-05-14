package com.jacobs.calendar

object Log {
    private const val TAG = "JacobsCalendar"
    private var on = true
    private val info: String
        get() {
            val ste = Throwable().stackTrace
            val realMethod = ste[2]
            return "[" + realMethod.fileName + ":" + realMethod.lineNumber + ":" + realMethod.methodName + "()] "
        }

    fun on() {
        on = true
    }

    fun off() {
        on = false
    }

    fun v(msg: String) {
        if (on) {
            android.util.Log.v(TAG, info + msg)
        }
    }

    fun d(msg: String) {
        if (on) {
            android.util.Log.d(TAG, info + msg)
        }
    }

    fun i(msg: String) {
        if (on) {
            android.util.Log.i(TAG, info + msg)
        }
    }

    fun w(msg: String) {
        if (on) {
            android.util.Log.w(TAG, info + msg)
        }
    }

    fun e(msg: String) {
        if (on) {
            android.util.Log.e(TAG, info + msg)
        }
    }

    fun v(tag: String?, msg: String) {
        if (on) {
            android.util.Log.v(tag, info + msg)
        }
    }

    fun d(tag: String?, msg: String) {
        if (on) {
            android.util.Log.d(tag, info + msg)
        }
    }

    fun i(tag: String?, msg: String) {
        if (on) {
            android.util.Log.i(tag, info + msg)
        }
    }

    fun w(tag: String?, msg: String) {
        if (on) {
            android.util.Log.w(tag, info + msg)
        }
    }

    fun e(tag: String?, msg: String) {
        if (on) {
            android.util.Log.e(tag, info + msg)
        }
    }
}