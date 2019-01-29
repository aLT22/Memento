package com.bytebuilding.domain.messages.splash

/**
 * Created by Turkin A. on 24/01/2019.
 */
sealed class SplashScreenEvents {

    object SplashScreenReadyEvent : SplashScreenEvents()

}