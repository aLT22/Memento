package com.bytebuilding.memento.ui.base

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren
import kotlin.coroutines.CoroutineContext


abstract class BaseViewModel :
    ViewModel(), CoroutineScope, LifecycleObserver {

    protected val mJob = SupervisorJob()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + mJob

    /**
     * Maybe there is a piece of code that unnecessary to be here...
     * */
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    protected fun onStop() {
        mJob.cancelChildren()
    }

    override fun onCleared() {
        mJob.cancelChildren()

        super.onCleared()
    }
}