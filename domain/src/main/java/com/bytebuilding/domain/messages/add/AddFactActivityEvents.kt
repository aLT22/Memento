package com.bytebuilding.domain.messages.add


sealed class AddFactActivityEvents {

    object FactWasAddedEvent : AddFactActivityEvents()

}