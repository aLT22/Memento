package com.bytebuilding.memento.utils

import com.bytebuilding.memento.data.entities.FactUI
import java.util.*

object StubManager {

    const val TAG = "StubManager"

    object FactManager {

        const val TAG = "FactManager"


        fun generateFactsForUi(): List<FactUI> =
                LinkedList<FactUI>()
                        .apply {
                            for (i in 0..100) {
                                add(
                                        FactUI(
                                                id = i.toLong(),
                                                title = "Title $i",
                                                description = "Description $i",
                                                timestamp = System.currentTimeMillis()
                                        )
                                )
                            }
                        }
    }

}