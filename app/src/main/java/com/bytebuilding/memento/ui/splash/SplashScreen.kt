package com.bytebuilding.memento.ui.splash

import android.os.Bundle
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import com.bytebuilding.memento.databinding.ActivitySplashScreenBinding
import com.bytebuilding.memento.ui.base.BaseActivity
import com.bytebuilding.memento.ui.main.MainActivity
import com.bytebuilding.memento.utils.launchActivityAndFinishCurrent
import kotlinx.coroutines.cancelChildren
import com.bytebuilding.memento.R

class SplashScreen :
    BaseActivity<ActivitySplashScreenBinding, SplashScreenVM>(SplashScreenVM::class) {

    override fun layoutId(): Int = R.layout.activity_splash_screen

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mViewModel.goToMainActivityAction().observe(this, Observer {
            launchActivityAndFinishCurrent<MainActivity> { }
        })
    }

    override fun onResume() {
        super.onResume()

        mViewModel.startActionListening()
    }

    override fun onStop() {
        mViewModel.mJob.cancelChildren()

        super.onStop()
    }

    companion object {

        const val TAG = "SplashScreen"

    }
}
