package com.reem.halamish.goalz.extensions

import android.util.Log


fun Any.logDebug(msg: String) {
    Log.d(javaClass.simpleName, msg)
}

fun Any.logError(msg: String) {
    Log.e(javaClass.simpleName, msg)
}


fun Any.logError(msg: String, throwable: Throwable) {
    Log.e(javaClass.simpleName, msg, throwable)
}