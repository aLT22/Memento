package com.bytebuilding.memento.ui.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bytebuilding.data.presenters.add.AddFactActionProducer
import com.bytebuilding.data.presenters.add.AddFactActivityPresenter
import com.bytebuilding.data.utils.loge
import com.bytebuilding.domain.messages.add.AddFactActivityActions
import com.bytebuilding.domain.messages.add.AddFactActivityEvents
import com.bytebuilding.memento.data.FactUI
import com.bytebuilding.memento.data.mappers.FactUIToFactMapper
import com.bytebuilding.memento.ui.base.BaseViewModel
import com.bytebuilding.memento.ui.base.BaseViewState
import com.bytebuilding.memento.utils.SingleLiveEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch


class AddFactActivityVM(
    private val mToFactMapper: FactUIToFactMapper
) : BaseViewModel() {
    /**
     * Activity's view state
     * */
    data class ViewState(
        var title: CharSequence? = "",
        var isTitleValid: Boolean = false,
        var description: CharSequence? = "",
        var isDescriptionValid: Boolean = false
    ) : BaseViewState {
        override fun resetState() {
            title = ""
            isTitleValid = false
            description = ""
            isDescriptionValid = false
        }
    }

    val mViewState = MutableLiveData<ViewState>()

    /**
     * Architecture stuff
     * */
    val mEventChannel = Channel<AddFactActivityEvents>()
    private var mActionProducer: AddFactActionProducer? = null

    /**
     * LiveData<T> per Action for UI changes
     * */
    private val mAddFactAction = SingleLiveEvent<AddFactActivityActions.FactWasSavedAction>()
    private val mFactWasNotAddedAction = SingleLiveEvent<AddFactActivityActions.FactWasNotSavedAction>()

    init {
        mViewState.value = ViewState()
    }

    override fun currentViewState(): BaseViewState = mViewState.value!!

    fun factWasSavedAction(): LiveData<AddFactActivityActions.FactWasSavedAction> =
        mAddFactAction

    fun factWasNotSavedAction(): LiveData<AddFactActivityActions.FactWasNotSavedAction> =
        mFactWasNotAddedAction

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

    fun startActionListening() =
        launch {
            mActionProducer = AddFactActivityPresenter.addFactActivityEventCatcher(mEventChannel)

            try {
                mActionProducer?.addFactActionChannel?.let { addFactActions ->
                    for (action in addFactActions) {
                        when (action) {
                            AddFactActivityActions.FactWasSavedAction -> {
                                mViewState.value?.resetState()
                                mAddFactAction.call()
                            }
                            AddFactActivityActions.FactWasNotSavedAction -> {
                                mFactWasNotAddedAction.call()
                            }
                        }
                    }
                }
            } catch (th: Throwable) {
                th.printStackTrace()
                loge(TAG, th.localizedMessage, th)
            }
        }

    fun saveFact() =
        launch {
            val currentViewState = currentViewState() as AddFactActivityVM.ViewState
            if (currentViewState.isTitleValid && currentViewState.isDescriptionValid) {
                val fact = mToFactMapper.map(
                    FactUI(
                        title = currentViewState.title.toString(),
                        desciption = currentViewState.description.toString()
                    )
                )

                val factWasAddedEvent = AddFactActivityEvents.AddFactEvent
                factWasAddedEvent.fact = fact

                mEventChannel.send(factWasAddedEvent)
            }
        }

    override fun onCleared() {
        AddFactActivityPresenter.cancelJobs()

        super.onCleared()
    }

    companion object {
        const val TAG = "AddFactActivityVM"
    }
}