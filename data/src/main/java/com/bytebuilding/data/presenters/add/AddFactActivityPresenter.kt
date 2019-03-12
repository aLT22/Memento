package com.bytebuilding.data.presenters.add

import com.bytebuilding.data.presenters.base.BasePresenter
import com.bytebuilding.data.utils.loge
import com.bytebuilding.domain.messages.add.AddFactActivityActions
import com.bytebuilding.domain.messages.add.AddFactActivityEvents
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlin.coroutines.CoroutineContext


const val TAG = "AddFactPresenter"

data class AddFactActionProducer(
    val addFactActionChannel: ReceiveChannel<AddFactActivityActions>
)

class AddFactActivityPresenter {

    companion object Presenter : BasePresenter, CoroutineScope {

        private val mJob = SupervisorJob()
        override val coroutineContext: CoroutineContext
            get() = Dispatchers.IO + mJob

        //TODO: need to add repository here (add Koin to Domain layer)...
        //TODO: ...and provide repository here

        fun addFactActivityEventCatcher(
            addFactActivityEventChannel: ReceiveChannel<AddFactActivityEvents>
        ): AddFactActionProducer {
            val addFactActivityActionChannel = Channel<AddFactActivityActions>()

            launch {
                try {
                    for (addFactActivityEvent in addFactActivityEventChannel) {
                        when (addFactActivityEvent) {
                            AddFactActivityEvents.FactWasAddedEvent -> {
                                //TODO: implementation logic after adding fact
                                false
                            }
                        }
                    }
                } catch (th: Throwable) {
                    th.printStackTrace()
                    loge(TAG, th.localizedMessage, th)
                }
            }

            return AddFactActionProducer(addFactActivityActionChannel)

        }

        override fun cancelJobs() {
            mJob.cancelChildren()
        }

    }

}