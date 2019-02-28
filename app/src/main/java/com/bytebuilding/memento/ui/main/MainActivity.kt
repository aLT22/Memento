package com.bytebuilding.memento.ui.main

import androidx.lifecycle.Observer
import com.bytebuilding.domain.messages.main.MainActivityEvents
import com.bytebuilding.memento.R
import com.bytebuilding.memento.databinding.ActivityMainBinding
import com.bytebuilding.memento.ui.add.AddFactActivity
import com.bytebuilding.memento.ui.base.BaseActivity
import com.bytebuilding.memento.utils.launchActivity
import com.bytebuilding.memento.utils.setUpToolbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar_base.*
import kotlinx.coroutines.launch

class MainActivity :
    BaseActivity<ActivityMainBinding, MainActivityVM, MainActivityVM.ViewState>(MainActivityVM::class) {

    override fun layoutId(): Int = R.layout.activity_main

    override fun viewState(): MainActivityVM.ViewState = mViewModel.mViewState.value!!

    override fun initViews() {
        setUpToolbar(
            toolbar = toolbar,
            title = R.string.main_screen_title
        )
    }

    override fun initListeners() {
        mViewModel.startActionListening()

        addFact.setOnClickListener {
            launch {
                mViewModel.mEventChannel.send(MainActivityEvents.AddFactEvent)
            }
        }
    }

    override fun observeChanges() {
        mViewModel.goToAddActivityAction().observe(this, Observer {
            launchActivity<AddFactActivity> { }
        })
    }

    override fun removeListeners() {
        addFact.setOnClickListener(null)
    }

    override fun render(viewState: MainActivityVM.ViewState) {
    }

    companion object {

        const val TAG = "MainActivity"

    }
}
