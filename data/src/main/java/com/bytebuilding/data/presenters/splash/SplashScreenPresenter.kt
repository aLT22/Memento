@file:Suppress("UNUSED_EXPRESSION")

package com.bytebuilding.data.presenters.splash

import com.bytebuilding.data.presenters.base.BasePresenter
import com.bytebuilding.data.utils.loge
import com.bytebuilding.domain.messages.splash.SplashScreenActions
import com.bytebuilding.domain.messages.splash.SplashScreenEvents
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlin.coroutines.CoroutineContext

const val TAG = "SplashScreenPresenter"

data class SplashScreenActionProducer(
    val splashScreenActionChannel: ReceiveChannel<SplashScreenActions>
)

class SplashScreenPresenter {

    companion object Presenter : BasePresenter, CoroutineScope {

        private val mJob = SupervisorJob()
        override val coroutineContext: CoroutineContext
            get() = Dispatchers.IO + mJob

        fun splashScreenEventCatcher(
            splashScreenEventChannel: ReceiveChannel<SplashScreenEvents>
        ): SplashScreenActionProducer {
            val splashScreenActionChannel = Channel<SplashScreenActions>()

            launch(coroutineContext) {
                try {
                    for (splashScreenEvent in splashScreenEventChannel) {
                        when (splashScreenEvent) {
                            SplashScreenEvents.SplashScreenReadyEvent -> {
                                //Splash screen delay
                                delay(3000)
                                splashScreenActionChannel.send(SplashScreenActions.GoToMainActivityAction)
                                false
                            }
                        }
                    }
                } catch (th: Throwable) {
                    th.printStackTrace()
                    loge(TAG, th.localizedMessage, th)
                }
            }

            return SplashScreenActionProducer(splashScreenActionChannel)
        }

        override fun cancelJobs() {
            mJob.cancelChildren()
        }
    }

}