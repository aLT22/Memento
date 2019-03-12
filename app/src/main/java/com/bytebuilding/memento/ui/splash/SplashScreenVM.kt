package com.bytebuilding.memento.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bytebuilding.data.presenters.splash.SplashScreenActionProducer
import com.bytebuilding.data.presenters.splash.SplashScreenPresenter
import com.bytebuilding.data.utils.loge
import com.bytebuilding.domain.messages.splash.SplashScreenActions
import com.bytebuilding.domain.messages.splash.SplashScreenEvents
import com.bytebuilding.memento.ui.base.BaseViewModel
import com.bytebuilding.memento.ui.base.BaseViewState
import com.bytebuilding.memento.utils.SingleLiveEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch

class SplashScreenVM :
    BaseViewModel() {

    data class ViewState(
        val tag: String = TAG
    ) : BaseViewState {
        override fun resetState() {
        }
    }

    val mViewState = MutableLiveData<ViewState>()

    /**
     * Architecture stuff
     * */
    val mEventChannel = Channel<SplashScreenEvents>()
    private var mActionProducer: SplashScreenActionProducer? = null

    /**
     * LiveData<T> per Action for UI changes
     * */
    private val mGoToMainActivityAction = SingleLiveEvent<SplashScreenActions.GoToMainActivityAction>()

    init {
        mViewState.value = ViewState()

        launch(coroutineContext) {
            mEventChannel.send(SplashScreenEvents.SplashScreenReadyEvent)
        }
    }

    fun startActionListening() =
        launch {
            mActionProducer = SplashScreenPresenter.splashScreenEventCatcher(mEventChannel)

            try {
                mActionProducer?.splashScreenActionChannel?.let { splashScreenActions ->
                    for (action in splashScreenActions) {
                        when (action) {
                            SplashScreenActions.GoToMainActivityAction -> {
                                mGoToMainActivityAction.call()
                            }
                        }
                    }
                }
            } catch (th: Throwable) {
                th.printStackTrace()
                loge(TAG, th.localizedMessage, th)
            }
        }

    fun goToMainActivityAction(): LiveData<SplashScreenActions.GoToMainActivityAction> = mGoToMainActivityAction

    override fun currentViewState(): BaseViewState = mViewState.value!!

    override fun onCleared() {
        SplashScreenPresenter.cancelJobs()

        super.onCleared()
    }

    companion object {
        const val TAG = "SplashScreenVM"
    }

}