package com.bytebuilding.domain.messages.main


sealed class MainActivityEvents {

    object AddFactEvent : MainActivityEvents()

    object RetreiveFactsEvent : MainActivityEvents()

}