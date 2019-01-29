package com.bytebuilding.memento.ui.splash

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.bytebuilding.data.presenters.splash.SplashScreenActionProducer
import com.bytebuilding.data.presenters.splash.SplashScreenPresenter
import com.bytebuilding.data.utils.loge
import com.bytebuilding.domain.messages.splash.SplashScreenActions
import com.bytebuilding.domain.messages.splash.SplashScreenEvents
import com.bytebuilding.memento.utils.SingleLiveEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class SplashScreenVM :
    ViewModel(), LifecycleObserver, CoroutineScope {

    /**
     * Coroutines stuff
     * */
    val mJob = SupervisorJob()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + mJob

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