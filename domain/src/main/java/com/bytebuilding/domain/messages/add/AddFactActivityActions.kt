package com.bytebuilding.domain.messages.add

import com.bytebuilding.domain.model.Fact


sealed class AddFactActivityActions {

    object SaveFactAction : AddFactActivityActions() {
        var fact: Fact? = null
    }

}