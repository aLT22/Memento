package com.bytebuilding.domain.utils

import kotlinx.coroutines.CoroutineScope


interface InteractorRequest

interface Interactor<in REQUEST : InteractorRequest, RESULT> {

    fun execute(request: REQUEST): RESULT

}

interface NoArgumentInteractor<RESULT> {

    fun execute(): RESULT

}