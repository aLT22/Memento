package com.bytebuilding.data.di

import com.bytebuilding.data.local.MementoDatabase
import com.bytebuilding.data.repositories.fact.FactRepositoryImpl
import com.bytebuilding.data.repositories.fact.datasource.FactLocalDataSourceImpl
import com.bytebuilding.domain.repositories.fact.FactDataSource
import com.bytebuilding.domain.repositories.fact.FactRepository
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.module


val localStorageModule = module {
    /**
     * Database
     * */
    single { MementoDatabase.getDatabase(androidApplication()) }

    /**
     * Dao
     * */
    //TODO: Add dao for injects here

    /**
     * DataSources
     * */
    single<FactDataSource> { FactLocalDataSourceImpl(get(), get(), get()) }

    /**
     * Repositories
     * */
    single<FactRepository> { FactRepositoryImpl(get()) }
}

val mappersModule = module {
    /**
     * FactMappers
     * */
}