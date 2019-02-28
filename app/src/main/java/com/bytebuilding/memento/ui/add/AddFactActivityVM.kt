package com.bytebuilding.memento.ui.add

import androidx.lifecycle.MutableLiveData
import com.bytebuilding.memento.ui.base.BaseViewModel


class AddFactActivityVM : BaseViewModel() {

    /**
     * Activity's view state
     * */
    data class ViewState(
        val title: String = "",
        val isTitleValid: Boolean = false,
        val description: String = "",
        val isDescriptionValid: Boolean = false
    )

    val viewState = MutableLiveData<ViewState>()

    init {
        viewState.value = ViewState()
    }

    fun onTitleChanged(title: String) {
        viewState.value =
            if (title.isEmpty()) {
                currentViewState().copy(title = title, isTitleValid = false)
            } else {
                currentViewState().copy(title = title, isTitleValid = true)
            }
    }

    private fun currentViewState(): ViewState = viewState.value!!

}