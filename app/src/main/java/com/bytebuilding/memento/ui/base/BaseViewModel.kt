package com.bytebuilding.memento.ui.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren
import kotlin.coroutines.CoroutineContext


abstract class BaseViewModel :
    ViewModel(), CoroutineScope {

    protected val mJob = SupervisorJob()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + mJob

    abstract fun currentViewState(): BaseViewState

    override fun onCleared() {
        mJob.cancelChildren()

        super.onCleared()
    }
}