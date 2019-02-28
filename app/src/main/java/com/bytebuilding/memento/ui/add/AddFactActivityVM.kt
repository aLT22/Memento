package com.bytebuilding.memento.ui.add

import androidx.lifecycle.MutableLiveData
import com.bytebuilding.memento.ui.base.BaseViewModel
import com.bytebuilding.memento.ui.base.BaseViewState


class AddFactActivityVM : BaseViewModel() {
    /**
     * Activity's view state
     * */
    data class ViewState(
        val title: CharSequence? = "",
        val isTitleValid: Boolean = false,
        val description: CharSequence? = "",
        val isDescriptionValid: Boolean = false
    ) : BaseViewState

    val mViewState = MutableLiveData<ViewState>()

    init {
        mViewState.value = ViewState()
    }

    override fun currentViewState(): BaseViewState = mViewState.value!!

    fun onTitleChanged(title: CharSequence?) {
        mViewState.value =
            if (title.isNullOrBlank()) {
                (currentViewState() as ViewState).copy(title = title, isTitleValid = false)
            } else {
                (currentViewState() as ViewState).copy(title = title, isTitleValid = true)
            }
    }

    fun onDescriptionChanged(description: CharSequence?) {
        mViewState.value =
            if (description.isNullOrBlank()) {
                (currentViewState() as ViewState).copy(description = description, isDescriptionValid = false)
            } else {
                (currentViewState() as ViewState).copy(description = description, isDescriptionValid = true)
            }
    }

    companion object {
        const val TAG = "AddFactActivityVM"
    }
}