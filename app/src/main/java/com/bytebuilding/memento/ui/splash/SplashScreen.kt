package com.bytebuilding.memento.ui.splash

import androidx.lifecycle.Observer
import com.bytebuilding.memento.R
import com.bytebuilding.memento.databinding.ActivitySplashScreenBinding
import com.bytebuilding.memento.ui.base.BaseActivity
import com.bytebuilding.memento.ui.main.MainActivity
import com.bytebuilding.memento.utils.launchActivityAndFinishCurrent

class SplashScreen :
    BaseActivity<ActivitySplashScreenBinding, SplashScreenVM>(SplashScreenVM::class) {

    override fun layoutId(): Int = R.layout.activity_splash_screen

    override fun initViews() {
    }

    override fun initListeners() {
        mViewModel.startActionListening()
    }

    override fun observeChanges() {
        mViewModel.goToMainActivityAction().observe(this, Observer {
            launchActivityAndFinishCurrent<MainActivity> { }
        })
    }

    override fun removeListeners() {
    }

    companion object {

        const val TAG = "SplashScreen"

    }
}
