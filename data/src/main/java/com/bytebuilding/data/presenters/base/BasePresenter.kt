package com.bytebuilding.data.presenters.base

import org.koin.standalone.KoinComponent


interface BasePresenter : KoinComponent {

    fun cancelJobs()

}