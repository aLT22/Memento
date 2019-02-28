package com.bytebuilding.memento.ui.add

import androidx.lifecycle.MutableLiveData
import com.bytebuilding.memento.ui.base.BaseViewModel
import com.bytebuilding.memento.ui.base.BaseViewState


class AddFactActivityVM : BaseViewModel() {

    data class ViewState(
        val tag: String = TAG
    ) : BaseViewState

    val mViewState = MutableLiveData<ViewState>()

    init {
        mViewState.value = ViewState()
    }

    override fun currentViewState(): BaseViewState = mViewState.value!!

    companion object {
        const val TAG = "AddFactActivityVM"
    }

}