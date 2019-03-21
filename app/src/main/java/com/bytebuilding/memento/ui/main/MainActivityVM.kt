package com.bytebuilding.memento.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bytebuilding.data.presenters.main.MainActivityActionProducer
import com.bytebuilding.data.presenters.main.MainActivityPresenter
import com.bytebuilding.data.utils.loge
import com.bytebuilding.domain.messages.main.MainActivityActions
import com.bytebuilding.domain.messages.main.MainActivityEvents
import com.bytebuilding.memento.data.FactUI
import com.bytebuilding.memento.data.mappers.FactToFactUIMapper
import com.bytebuilding.memento.ui.base.BaseViewModel
import com.bytebuilding.memento.ui.base.BaseViewState
import com.bytebuilding.memento.utils.SingleLiveEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import java.util.*


class MainActivityVM(
        private val mToFactUIMapper: FactToFactUIMapper
) : BaseViewModel() {

    data class ViewState(
            var tag: String = TAG,
            var facts: List<FactUI> = LinkedList()
    ) : BaseViewState {
        override fun resetState() {
            facts = emptyList()
        }
    }

    val mViewState = MutableLiveData<ViewState>()

    /**
     * Architecture stuff
     * */
    val mEventChannel = Channel<MainActivityEvents>()
    private var mActionProducer: MainActivityActionProducer? = null

    /**
     * LiveData<Action> per Action for UI changes
     * */
    private val mGoToAddActivityAction = SingleLiveEvent<MainActivityActions.GoToAddFactActivityAction>()
    private val mFactsWasNotRetrievedAction = SingleLiveEvent<MainActivityActions.FactsWasNotRetreivedAction>()
    private val mFactsWasRetrievedAction = SingleLiveEvent<MainActivityActions.FactsWasRetreivedAction>()

    init {
        mViewState.value = ViewState()
    }

    fun retrieveFactsEvent() =
            launch {
                mEventChannel.send(MainActivityEvents.RetreiveFactsEvent)
            }

    fun addFactEvent() =
            launch {
                mEventChannel.send(MainActivityEvents.AddFactEvent)
            }

    fun onFactsChanged(facts: List<FactUI>) {
        mViewState.postValue(
                (currentViewState() as ViewState).copy(facts = facts)
        )
    }

    fun startActionListening() =
            launch {
                mActionProducer = MainActivityPresenter.mainActivityEventCatcher(mEventChannel)

                try {
                    mActionProducer?.mainActivityActionChannel?.let { mainActivityActions ->
                        for (action in mainActivityActions) {
                            when (action) {
                                MainActivityActions.GoToAddFactActivityAction -> {
                                    mGoToAddActivityAction.call()
                                }
                                MainActivityActions.FactsWasNotRetreivedAction -> {
                                    mFactsWasNotRetrievedAction.call()
                                }
                                MainActivityActions.FactsWasRetreivedAction -> {
                                    val mappedFacts = LinkedList<FactUI>()
                                    MainActivityActions
                                            .FactsWasRetreivedAction
                                            .mFacts
                                            .orEmpty()
                                            .forEach { fact ->
                                                mappedFacts.add(
                                                        mToFactUIMapper.map(fact)
                                                )
                                            }

                                    onFactsChanged(mappedFacts)
//                                onFactsChanged(StubManager.FactManager.generateFactsForUi())
                                }
                            }
                        }
                    }
                } catch (th: Throwable) {
                    th.printStackTrace()
                    loge(MainActivityVM.TAG, th.localizedMessage, th)
                }
            }

    fun goToAddActivityAction(): LiveData<MainActivityActions.GoToAddFactActivityAction> = mGoToAddActivityAction

    override fun currentViewState(): BaseViewState = mViewState.value!!

    override fun onCleared() {
        MainActivityPresenter.cancelJobs()

        super.onCleared()
    }

    companion object {
        const val TAG = "MainActivityVM"
    }

}