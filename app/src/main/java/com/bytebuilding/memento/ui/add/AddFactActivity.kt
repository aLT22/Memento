package com.bytebuilding.memento.ui.add

import android.graphics.drawable.Drawable
import android.view.Menu
import android.view.MenuItem
import androidx.core.content.ContextCompat
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import com.bytebuilding.memento.R
import com.bytebuilding.memento.ui.base.BaseActivity
import com.bytebuilding.memento.utils.longToast
import com.bytebuilding.memento.utils.setUpToolbar
import com.bytebuilding.memento.utils.shortToast
import kotlinx.android.synthetic.main.activity_add_fact.*
import kotlinx.android.synthetic.main.toolbar_base.*

class AddFactActivity :
        BaseActivity<ViewDataBinding, AddFactActivityVM, AddFactActivityVM.ViewState>(AddFactActivityVM::class) {

    private var mSaveMenuItem: MenuItem? = null

    private var mDisabledSaveMenuButtonDrawable: Drawable? = null
    private var mEnableSaveMenuButtonDrawable: Drawable? = null

    override fun layoutId(): Int = R.layout.activity_add_fact

    override fun viewState(): AddFactActivityVM.ViewState = mViewModel.mViewState.value!!

    override fun initViews() {
        setUpToolbar(
                toolbar = toolbar,
                isHomeAsUpEnabled = true,
                homeAsUpIndicatorResId = R.drawable.ic_arrow_back_white_24dp,
                title = R.string.add_fact_screen_title
        )

        mEnableSaveMenuButtonDrawable = ContextCompat.getDrawable(this, R.drawable.ic_save_white_24dp)
        mDisabledSaveMenuButtonDrawable = ContextCompat.getDrawable(this, R.drawable.ic_save_alpha_white_24dp)
    }

    override fun initListeners() {
        mViewModel.startActionListening()

        addTitle.setOnInformationTextChangedListener { text ->
            mViewModel.onTitleChanged(text.toString())
        }

        addDescription.setOnInformationTextChangedListener { text ->
            mViewModel.onDescriptionChanged(text)
        }
    }

    override fun observeChanges() {
        mViewModel.mViewState.observe(this, Observer {
            it?.let { viewState -> render(viewState) }
        })

        mViewModel.factWasSavedAction().observe(this, Observer {
            finish()
            longToast("Fact was added!")
        })

        mViewModel.factWasNotSavedAction().observe(this, Observer {
            shortToast("Error!")
        })
    }

    override fun removeListeners() {
        addTitle.removeInformationTextChangedListener()
        addDescription.removeInformationTextChangedListener()
    }

    override fun render(viewState: AddFactActivityVM.ViewState) {
        when (viewState.isTitleValid && viewState.isDescriptionValid) {
            true -> {
                mSaveMenuItem?.icon = mEnableSaveMenuButtonDrawable
                mSaveMenuItem?.isEnabled = true
            }
            false -> {
                mSaveMenuItem?.icon = mDisabledSaveMenuButtonDrawable
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