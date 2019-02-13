package com.bytebuilding.memento.ui.splash

import androidx.lifecycle.LiveData
import com.bytebuilding.data.presenters.splash.SplashScreenActionProducer
import com.bytebuilding.data.presenters.splash.SplashScreenPresenter
import com.bytebuilding.data.utils.loge
import com.bytebuilding.domain.messages.splash.SplashScreenActions
import com.bytebuilding.domain.messages.splash.SplashScreenEvents
import com.bytebuilding.memento.ui.base.BaseViewModel
import com.bytebuilding.memento.utils.SingleLiveEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch

class SplashScreenVM :
    BaseViewModel() {

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

    companion object {
        const val TAG = "SplashScreenVM"
    }

}