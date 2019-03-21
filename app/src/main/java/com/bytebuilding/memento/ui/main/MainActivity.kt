package com.bytebuilding.memento.ui.main

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bytebuilding.memento.R
import com.bytebuilding.memento.databinding.ActivityMainBinding
import com.bytebuilding.memento.ui.adapters.rv.FactsListAdapter
import com.bytebuilding.memento.ui.add.AddFactActivity
import com.bytebuilding.memento.ui.base.BaseActivity
import com.bytebuilding.memento.utils.launchActivity
import com.bytebuilding.memento.utils.setUpToolbar
import com.bytebuilding.memento.utils.shortToast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity :
        BaseActivity<ActivityMainBinding, MainActivityVM, MainActivityVM.ViewState>(MainActivityVM::class) {

    private lateinit var mFactsListAdapter: FactsListAdapter

    override fun layoutId(): Int = R.layout.activity_main

    override fun viewState(): MainActivityVM.ViewState = mViewModel.mViewState.value!!

    override fun initViews() {
        setUpToolbar(
                toolbar = toolbar,
                title = R.string.main_screen_title
        )

        mFactsListAdapter = FactsListAdapter { fact ->
            shortToast(fact.id.toString())
        }

        mBinding.mementos.adapter = mFactsListAdapter
        mBinding.mementos.layoutManager =
                LinearLayoutManager(
                        this,
                        RecyclerView.VERTICAL,
                        false
                )
    }

    override fun initListeners() {
        mViewModel.startActionListening()

        mViewModel.retrieveFactsEvent()

        addFact.setOnClickListener {
            mViewModel.addFactEvent()
        }
    }

    override fun observeChanges() {
        mViewModel.mViewState.observe(this, Observer { viewState ->
            viewState?.let { render(it) }
        })

        mViewModel.goToAddActivityAction().observe(this, Observer {
            launchActivity<AddFactActivity> { }
        })
    }

    override fun removeListeners() {
        addFact.setOnClickListener(null)
    }

    override fun render(viewState: MainActivityVM.ViewState) {
        mFactsListAdapter.submitList(viewState.facts)
    }

    companion object {

        const val TAG = "MainActivity"

    }
}
