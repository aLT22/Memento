package com.bytebuilding.memento.ui.main

import android.os.Bundle
import com.bytebuilding.data.utils.logi
import com.bytebuilding.memento.R
import com.bytebuilding.memento.databinding.ActivityMainBinding
import com.bytebuilding.memento.ui.base.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding, MainActivityVM>(MainActivityVM::class) {

    override fun layoutId(): Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        logi(TAG, mViewModel.toString())
    }

    companion object {

        const val TAG = "MainActivity"

    }
}
