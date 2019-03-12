package com.bytebuilding.domain.messages.add


sealed class AddFactActivityActions {

    object FactWasSavedAction : AddFactActivityActions()

    object FactWasNotSavedAction : AddFactActivityActions()

}