package com.bytebuilding.data.di

import com.bytebuilding.data.local.MementoDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.module


val localStorageModule = module {
    /**
     * Database
     * */
    single { MementoDatabase.getDatabase(androidApplication()) }

    /**
     * Repositories
     * */
}