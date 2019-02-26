package com.bytebuilding.memento.di

import com.bytebuilding.memento.ui.add.AddFactActivityVM
import com.bytebuilding.memento.ui.main.MainActivityVM
import com.bytebuilding.memento.ui.splash.SplashScreenVM
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

/**
 * Application level module
 * */
val applicationModule = module {

}

/**
 * UI module
 * */
val viewModule = module {
    /**
     * Activity VMs
     * */
    viewModel { SplashScreenVM() }
    viewModel { MainActivityVM() }
    viewModel { AddFactActivityVM() }

    /**
     * Fragment VMs
     * */
}