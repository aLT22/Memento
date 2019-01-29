package com.bytebuilding.data.utils

import android.util.Log
import com.bytebuilding.data.BuildConfig

/**
 * Custom Logger
 * */
//Verbose level
fun logv(
    tag: CharSequence = "",
    msg: CharSequence = ""
) {
    if (BuildConfig.DEBUG) Log.v(tag.toString(), msg.toString())
}

//Debug level
fun logd(
    tag: CharSequence = "",
    msg: CharSequence = "",
    throwable: Throwable? = Throwable(msg.toString())
) {
    if (BuildConfig.DEBUG) {
        if (throwable != null) Log.d(tag.toString(), msg.toString(), throwable)
        else Log.d(tag.toString(), msg.toString())
    }
}

//Info level
fun logi(
    tag: CharSequence = "",
    msg: CharSequence = "",
    throwable: Throwable? = Throwable(msg.toString())
) {
    if (BuildConfig.DEBUG) {
        if (throwable != null) Log.i(tag.toString(), msg.toString(), throwable)
        else Log.i(tag.toString(), msg.toString())
    }
}

//Warn level
fun logw(
    tag: CharSequence = "",
    msg: CharSequence = "",
    throwable: Throwable? = Throwable(msg.toString())
) {
    if (BuildConfig.DEBUG) {
        if (throwable != null) Log.w(tag.toString(), msg.toString(), throwable)
        else Log.w(tag.toString(), msg.toString())
    }
}

//Error level
fun loge(
    tag: CharSequence = "",
    msg: CharSequence = "",
    throwable: Throwable? = Throwable(msg.toString())
) {
    if (BuildConfig.DEBUG) {
        if (throwable != null) Log.e(tag.toString(), msg.toString(), throwable)
        else Log.e(tag.toString(), msg.toString())
    }
}