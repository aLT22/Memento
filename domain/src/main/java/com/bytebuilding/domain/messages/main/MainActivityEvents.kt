package com.bytebuilding.domain.messages.main

import com.bytebuilding.domain.model.Fact


sealed class MainActivityEvents {

    /**
     * Add fact events
     * */
    object AddFactEvent : MainActivityEvents()
    /**
     * Add fact events
     * */

    /**
     * Retrieve facts events
     * */
    object RetreiveFactsEvent : MainActivityEvents()
    /**
     * Retrieve facts events
     * */

    /**
     * Delete fact events
     * */
    object DeleteFactEvent : MainActivityEvents() {
        var mIndex: Int? = null
        var mFact: Fact? = null
    }
    /**
     * Delete fact events
     * */

}