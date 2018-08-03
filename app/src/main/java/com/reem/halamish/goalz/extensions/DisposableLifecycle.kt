package com.reem.halamish.goalz.extensions

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleOwner
import io.reactivex.disposables.Disposable

class DisposableLifecycle(obj: Disposable, val owner: LifecycleOwner) : LifecycleOwner, Disposable by obj {
    override fun getLifecycle(): Lifecycle {
        return owner.lifecycle
    }

}