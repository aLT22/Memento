package com.bytebuilding.memento.ui.add

import androidx.lifecycle.MutableLiveData
import com.bytebuilding.data.presenters.add.AddFactActionProducer
import com.bytebuilding.domain.messages.add.AddFactActivityActions
import com.bytebuilding.domain.messages.add.AddFactActivityEvents
import com.bytebuilding.memento.ui.base.BaseViewModel
import com.bytebuilding.memento.ui.base.BaseViewState
import com.bytebuilding.memento.utils.SingleLiveEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch


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

    /**
     * Architecture stuff
     * */
    val mEventChannel = Channel<AddFactActivityEvents>()
    private var mActionProducer: AddFactActionProducer? = null

    /**
     * LiveData<T> per Action for UI changes
     * */
    private val mAddFactAction = SingleLiveEvent<AddFactActivityActions.SaveFactAction>()

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

    fun saveFact() =
        launch {
            mEventChannel.send(AddFactActivityEvents.FactWasAddedEvent)
            //TODO: need to end add fact logic!!!
        }

    companion object {
        const val TAG = "AddFactActivityVM"
    }
}