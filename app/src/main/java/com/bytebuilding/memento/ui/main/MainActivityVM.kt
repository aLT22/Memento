package com.bytebuilding.memento.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bytebuilding.data.presenters.main.MainActivityActionProducer
import com.bytebuilding.data.presenters.main.MainActivityPresenter
import com.bytebuilding.data.utils.loge
import com.bytebuilding.domain.messages.main.MainActivityActions
import com.bytebuilding.domain.messages.main.MainActivityEvents
import com.bytebuilding.memento.data.entities.FactUI
import com.bytebuilding.memento.data.mappers.FactToFactUIMapper
import com.bytebuilding.memento.data.mappers.FactUIToFactMapper
import com.bytebuilding.memento.ui.base.BaseViewModel
import com.bytebuilding.memento.ui.base.BaseViewState
import com.bytebuilding.memento.utils.SingleLiveEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import java.util.*


class MainActivityVM(
        private val mToFactUIMapper: FactToFactUIMapper,
        private val mToFactMapper: FactUIToFactMapper
) : BaseViewModel() {

    data class ViewState(
            var tag: String = TAG,
            var facts: List<FactUI> = LinkedList(),
            var deletedFactIndex: Int = -1
    ) : BaseViewState {
        override fun resetState() {
            facts = emptyList()
            deletedFactIndex = -1
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
    private val mFactsWasNotRetrievedAction = SingleLiveEvent<MainActivityActions.FactsWasNotRetrievedAction>()
    private val mFactsWasRetrievedAction = SingleLiveEvent<MainActivityActions.FactsWasRetrievedAction>()

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

    fun deleteFactEvent(index: Int, deletedFact: FactUI) =
            launch {
                MainActivityEvents.DeleteFactEvent.mFact = mToFactMapper.map(deletedFact)
                MainActivityEvents.DeleteFactEvent.mIndex = index
                mEventChannel.send(MainActivityEvents.DeleteFactEvent)
            }

    fun onFactsChanged(facts: List<FactUI>) {
        mViewState.postValue(
                (currentViewState() as ViewState).copy(facts = facts)
        )
    }

    fun deleteFact(index: Int) {
        val modifiedFacts = ((currentViewState() as ViewState).facts as LinkedList<FactUI>)
        modifiedFacts.removeAt(index)
        mViewState.postValue(
                (currentViewState() as ViewState).copy(facts = modifiedFacts, deletedFactIndex = index)
        )
    }

    fun restoreFact(index: Int, deletedFact: FactUI) {
        val modifiedFacts = ((currentViewState() as ViewState).facts as LinkedList<FactUI>)
        modifiedFacts.add(index, deletedFact)
        mViewState.postValue(
                (currentViewState() as ViewState).copy(facts = modifiedFacts, deletedFactIndex = -1)
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
                                MainActivityActions.FactsWasNotRetrievedAction -> {
                                    mFactsWasNotRetrievedAction.call()
                                }
                                MainActivityActions.FactsWasRetrievedAction -> {
                                    val mappedFacts = LinkedList<FactUI>()
                                    MainActivityActions
                                            .FactsWasRetrievedAction
                                            .mFacts
                                            .orEmpty()
                                            .forEach { fact ->
                                                mappedFacts.add(
                                                        mToFactUIMapper.map(fact)
                                                )
                                            }

                                    onFactsChanged(mappedFacts)
                                }
                                MainActivityActions.FactWasDeletedAction -> {
                                }
                                MainActivityActions.FactWasNotDeletedAction -> {
                                    val restoredIndex = MainActivityActions.FactWasNotDeletedAction.mIndex
                                    val restoredFact = MainActivityActions.FactWasNotDeletedAction.mFact
                                    if (restoredIndex != null && restoredFact != null) {
                                        restoreFact(restoredIndex, mToFactUIMapper.map(restoredFact))
                                    }
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