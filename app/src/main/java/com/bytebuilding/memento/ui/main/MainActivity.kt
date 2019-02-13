package com.bytebuilding.memento.ui.main

import android.os.Bundle
import com.bytebuilding.memento.R
import com.bytebuilding.memento.databinding.ActivityMainBinding
import com.bytebuilding.memento.ui.base.BaseActivity
import com.bytebuilding.memento.utils.setUpToolbar
import kotlinx.android.synthetic.main.toolbar_base.*

class MainActivity : BaseActivity<ActivityMainBinding, MainActivityVM>(MainActivityVM::class) {

    override fun layoutId(): Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setUpToolbar(
            toolbar = toolbar,
            title = R.string.main_screen_title
        )
    }

    companion object {

        const val TAG = "MainActivity"

    }
}
