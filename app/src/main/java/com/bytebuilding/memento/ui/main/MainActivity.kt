package com.bytebuilding.memento.ui.main

import android.graphics.Canvas
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
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

    private var mItemTouchHelperSimpleCallback: ItemTouchHelper.SimpleCallback? = null
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

        mItemTouchHelperSimpleCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                //TODO: remove data from adapter
            }

            override fun onChildDraw(
                c: Canvas,
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean
            ) {
                //TODO: draw the background view
            }
        }
        mItemTouchHelper = ItemTouchHelper(mItemTouchHelperSimpleCallback as ItemTouchHelper.SimpleCallback)
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
        mItemTouchHelperSimpleCallback = null
    }

    override fun render(viewState: MainActivityVM.ViewState) {
        mFactsListAdapter.submitList(viewState.facts)
    }

    companion object {

        const val TAG = "MainActivity"

    }
}
