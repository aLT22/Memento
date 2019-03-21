package com.bytebuilding.memento.ui.main

import android.graphics.Color
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bytebuilding.memento.R
import com.bytebuilding.memento.databinding.ActivityMainBinding
import com.bytebuilding.memento.ui.adapters.rv.FactsListAdapter
import com.bytebuilding.memento.ui.add.AddFactActivity
import com.bytebuilding.memento.ui.base.BaseActivity
import com.bytebuilding.memento.ui.custom.helpers.FactUiItemTouchHelper
import com.bytebuilding.memento.ui.custom.helpers.FactUiItemTouchHelperListener
import com.bytebuilding.memento.utils.launchActivity
import com.bytebuilding.memento.utils.setUpToolbar
import com.bytebuilding.memento.utils.shortToast
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity :
        BaseActivity<ActivityMainBinding, MainActivityVM, MainActivityVM.ViewState>(MainActivityVM::class) {

    private lateinit var mFactsListAdapter: FactsListAdapter

    private var mItemTouchHelperSimpleCallback: FactUiItemTouchHelper? = null
    private var mItemTouchHelper: ItemTouchHelper? = null

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

        mBinding.mementos.itemAnimator = DefaultItemAnimator()
        mBinding.mementos.layoutManager =
                LinearLayoutManager(
                        this,
                        RecyclerView.VERTICAL,
                        false
                )
        mBinding.mementos.adapter = mFactsListAdapter
    }

    override fun initListeners() {
        mViewModel.startActionListening()

        mViewModel.retrieveFactsEvent()

        addFact.setOnClickListener {
            mViewModel.addFactEvent()
        }

        mItemTouchHelperSimpleCallback = FactUiItemTouchHelper(0, ItemTouchHelper.LEFT)
        mItemTouchHelperSimpleCallback
                ?.setItemTouchHelperListener(object : FactUiItemTouchHelperListener {
                    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int, position: Int) {
                        if (viewHolder is FactsListAdapter.FactUiViewHolder) {
                            val deletedTitle = viewState().facts[position].title

                            val deletedFact = viewState().facts[position]

                            mViewModel.deleteFact(position)
                            mFactsListAdapter.factRemoved(position)

                            val snackbar = Snackbar
                                    .make(mBinding.coordinatorContainer, "$deletedTitle removed!", Snackbar.LENGTH_INDEFINITE)
                            snackbar.setAction("UNDO") {
                                mViewModel.restoreFact(position, deletedFact)
                                mFactsListAdapter.factInserted(position)
                                mBinding.mementos.scrollToPosition(position)
                            }
                            snackbar.setActionTextColor(Color.YELLOW)
                            snackbar.show()
                        }
                    }
                })

        mItemTouchHelper = ItemTouchHelper(mItemTouchHelperSimpleCallback as FactUiItemTouchHelper)
        mItemTouchHelper?.attachToRecyclerView(mBinding.mementos)
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

        mItemTouchHelper?.attachToRecyclerView(null)
        mItemTouchHelper = null
        mItemTouchHelperSimpleCallback?.removeTouchHelperListener()
        mItemTouchHelperSimpleCallback = null
    }

    override fun render(viewState: MainActivityVM.ViewState) {
        mFactsListAdapter.submitList(viewState.facts)
    }

    companion object {

        const val TAG = "MainActivity"

    }
}
