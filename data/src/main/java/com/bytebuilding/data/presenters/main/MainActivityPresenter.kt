package com.bytebuilding.data.presenters.main

import com.bytebuilding.data.presenters.base.BasePresenter
import com.bytebuilding.data.utils.loge
import com.bytebuilding.domain.messages.main.MainActivityActions
import com.bytebuilding.domain.messages.main.MainActivityEvents
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlin.coroutines.CoroutineContext


const val TAG = "MainActivityPresenter"

data class MainActivityActionProducer(
    val mainActivityActionChannel: ReceiveChannel<MainActivityActions>
)

class MainActivityPresenter {

    companion object Presenter : BasePresenter, CoroutineScope {

        private val mJob = SupervisorJob()
        override val coroutineContext: CoroutineContext
            get() = Dispatchers.IO + mJob

        fun mainActivityEventCatcher(
            mainActivityEventChannel: ReceiveChannel<MainActivityEvents>
        ): MainActivityActionProducer {
            val mainActivityActionChannel = Channel<MainActivityActions>()

            launch(coroutineContext) {
                try {
                    for (mainActivityEvent in mainActivityEventChannel) {
                        when (mainActivityEvent) {
                            MainActivityEvents.AddFactEvent -> {
                                mainActivityActionChannel.send(MainActivityActions.GoToAddFactActivityAction)
                                false
                            }
                        }
                    }
                } catch (th: Throwable) {
                    th.printStackTrace()
                    loge(TAG, th.localizedMessage, th)
                }
            }

            return MainActivityActionProducer(mainActivityActionChannel)
        }

        override fun cancelJobs() {
            mJob.cancelChildren()
        }

    }

}