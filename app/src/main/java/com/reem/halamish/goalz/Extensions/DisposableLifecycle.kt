package com.reem.halamish.goalz.Extensions

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.LifecycleOwner
import com.google.android.gms.dynamic.LifecycleDelegate
import io.reactivex.disposables.Disposable

class DisposableLifecycle(obj: Disposable, val owner: LifecycleOwner) : LifecycleOwner, Disposable by obj {
    override fun getLifecycle(): Lifecycle {
        return owner.lifecycle
    }

}