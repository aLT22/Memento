package com.bytebuilding.domain.messages.add

import com.bytebuilding.domain.model.Fact


sealed class AddFactActivityEvents {

    object AddFactEvent : AddFactActivityEvents() {
        var fact: Fact? = null
    }

}