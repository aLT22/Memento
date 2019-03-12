package com.bytebuilding.memento.ui.add

import android.view.Menu
import android.view.MenuItem
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import com.bytebuilding.memento.R
import com.bytebuilding.memento.ui.base.BaseActivity
import com.bytebuilding.memento.ui.custom.AddInformationView
import com.bytebuilding.memento.utils.setUpToolbar
import kotlinx.android.synthetic.main.activity_add_fact.*
import kotlinx.android.synthetic.main.toolbar_base.*

class AddFactActivity :
    BaseActivity<ViewDataBinding, AddFactActivityVM, AddFactActivityVM.ViewState>(AddFactActivityVM::class) {

    private var mSaveMenuItem: MenuItem? = null

    override fun layoutId(): Int = R.layout.activity_add_fact

    override fun viewState(): AddFactActivityVM.ViewState = mViewModel.mViewState.value!!

    override fun initViews() {
        setUpToolbar(
            toolbar = toolbar,
            title = R.string.add_fact_screen_title
        )
    }

    override fun initListeners() {
        mViewModel.startActionListening()

        addTitle.setOnInformationTextChangedListener(object : AddInformationView.InformationTextChangedListener {
            override fun onTextChanged(text: CharSequence?) {
                mViewModel.onTitleChanged(text.toString())
            }
        })

        addDescription.setOnInformationTextChangedListener(object : AddInformationView.InformationTextChangedListener {
            override fun onTextChanged(text: CharSequence?) {
                mViewModel.onDescriptionChanged(text)
            }
        })
    }

    override fun observeChanges() {
        mViewModel.mViewState.observe(this, Observer {
            it?.let { viewState -> render(viewState) }
        })
    }

    override fun removeListeners() {
        addTitle.removeInformationTextChangedListener()
        addDescription.removeInformationTextChangedListener()
    }

    override fun render(viewState: AddFactActivityVM.ViewState) {
        when (viewState.isTitleValid && viewState.isDescriptionValid) {
            true -> {
                mSaveMenuItem?.isEnabled = true
            }
            false -> {
                mSaveMenuItem?.isEnabled = false
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.add_fact_menu, menu)
        mSaveMenuItem = menu?.findItem(R.id.actionSave)
        mSaveMenuItem?.isEnabled = false
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.actionSave -> {
                mViewModel.saveFact()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        const val TAG = "AddFactActivity"
    }
}