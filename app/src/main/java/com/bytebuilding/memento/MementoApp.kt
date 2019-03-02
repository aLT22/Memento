package com.bytebuilding.memento

import android.app.Application
import com.bytebuilding.data.di.localStorageModule
import com.bytebuilding.memento.di.applicationModule
import com.bytebuilding.memento.di.viewModule
import org.koin.android.ext.android.startKoin

/**
 * Created by Turkin A. on 06/01/2019.
 */
class MementoApp : Application() {


    override fun onCreate() {
        super.onCreate()

        startKoin(
            this,
            listOf(
                applicationModule,
                viewModule,
                localStorageModule
            )
        )
    }

    companion object {

        const val TAG = "MementoApp"

    }

}