package com.bytebuilding.domain.messages.main

import com.bytebuilding.domain.model.Fact


sealed class MainActivityActions {

    /**
     * Add mFact actions
     * */
    object GoToAddFactActivityAction : MainActivityActions()
    /**
     * Add mFact actions
     * */

    /**
     * Retrieve mFact actions
     * */
    object FactsWasRetrievedAction : MainActivityActions() {
        var mFacts: List<Fact>? = null
    }

    object FactsWasNotRetrievedAction : MainActivityActions()
    /**
     * Retrieve mFact actions
     * */

    /**
     * Delete mFact actions
     * */
    object FactWasDeletedAction : MainActivityActions()

    object FactWasNotDeletedAction : MainActivityActions() {
        var mIndex: Int? = null
        var mFact: Fact? = null
    }
    /**
     * Delete mFact actions
     * */

}