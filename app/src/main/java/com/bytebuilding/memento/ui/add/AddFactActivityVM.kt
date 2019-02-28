package com.bytebuilding.memento.ui.add

import androidx.lifecycle.MutableLiveData
import com.bytebuilding.memento.ui.base.BaseViewModel
import com.bytebuilding.memento.ui.base.BaseViewState


class AddFactActivityVM : BaseViewModel() {
  /**
     * Activity's view state
     * */  
  data class ViewState(
        val title: String = "",
        val isTitleValid: Boolean = false,
        val description: String = "",
        val isDescriptionValid: Boolean = false
    ) : BaseViewState

    val mViewState = MutableLiveData<ViewState>()

    init {
        mViewState.value = ViewState()
    }

    override fun currentViewState(): BaseViewState = mViewState.value!!
  
  fun onTitleChanged(title: String) {
        viewState.value =
            if (title.isEmpty()) {
                currentViewState().copy(title = title, isTitleValid = false)
            } else {
                currentViewState().copy(title = title, isTitleValid = true)
            }
    }

    companion object {
        const val TAG = "AddFactActivityVM"
    }
}