package com.bytebuilding.memento.ui.main

import androidx.lifecycle.LiveData
import com.bytebuilding.data.presenters.main.MainActivityActionProducer
import com.bytebuilding.data.presenters.main.MainActivityPresenter
import com.bytebuilding.data.utils.loge
import com.bytebuilding.domain.messages.main.MainActivityActions
import com.bytebuilding.domain.messages.main.MainActivityEvents
import com.bytebuilding.memento.ui.base.BaseViewModel
import com.bytebuilding.memento.utils.SingleLiveEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch


class MainActivityVM : BaseViewModel() {

    /**
     * Architecture stuff
     * */
    val mEventChannel = Channel<MainActivityEvents>()
    private var mActionProducer: MainActivityActionProducer? = null

    /**
     * LiveData<Action> per Action for UI changes
     * */
    private val mGoToAddActivityAction = SingleLiveEvent<MainActivityActions.GoToAddFactActivityAction>()

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
                        }
                    }
                }
            } catch (th: Throwable) {
                th.printStackTrace()
                loge(MainActivityVM.TAG, th.localizedMessage, th)
            }
        }

    fun goToAddActivityAction(): LiveData<MainActivityActions.GoToAddFactActivityAction> = mGoToAddActivityAction

    override fun onCleared() {
        MainActivityPresenter.cancelJobs()

        super.onCleared()
    }

    companion object {
        const val TAG = "MainActivityVM"
    }

}