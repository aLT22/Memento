package com.bytebuilding.memento.ui.add

import android.view.Menu
import android.view.MenuItem
import androidx.databinding.ViewDataBinding
import com.bytebuilding.memento.R
import com.bytebuilding.memento.ui.base.BaseActivity
import com.bytebuilding.memento.utils.setUpToolbar
import com.bytebuilding.memento.utils.shortToast
import kotlinx.android.synthetic.main.toolbar_base.*

class AddFactActivity :
    BaseActivity<ViewDataBinding, AddFactActivityVM>(AddFactActivityVM::class) {

    override fun layoutId(): Int = R.layout.activity_add_fact

    override fun initViews() {
        setUpToolbar(
            toolbar = toolbar,
            title = R.string.add_fact_screen_title
        )
    }

    override fun initListeners() {
    }

    override fun observeChanges() {
    }

    override fun removeListeners() {
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.add_fact_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.actionSave -> {
                shortToast("Saved!")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}