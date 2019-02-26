package com.bytebuilding.memento.ui.main

import com.bytebuilding.memento.R
import com.bytebuilding.memento.databinding.ActivityMainBinding
import com.bytebuilding.memento.ui.base.BaseActivity
import com.bytebuilding.memento.utils.setUpToolbar
import kotlinx.android.synthetic.main.toolbar_base.*

class MainActivity : BaseActivity<ActivityMainBinding, MainActivityVM>(MainActivityVM::class) {

    override fun layoutId(): Int = R.layout.activity_main

    override fun initViews() {
        setUpToolbar(
            toolbar = toolbar,
            title = R.string.main_screen_title
        )
    }

    override fun initListeners() {
    }

    override fun observeChanges() {
    }

    override fun removeListeners() {
    }

    companion object {

        const val TAG = "MainActivity"

    }
}
