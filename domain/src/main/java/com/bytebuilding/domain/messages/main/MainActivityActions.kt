package com.bytebuilding.domain.messages.main

import com.bytebuilding.domain.model.Fact


sealed class MainActivityActions {

    object GoToAddFactActivityAction : MainActivityActions()

    object FactsWasRetreivedAction : MainActivityActions() {
        var mFacts: List<Fact>? = null
    }

    object FactsWasNotRetreivedAction : MainActivityActions()

}