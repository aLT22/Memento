package com.bytebuilding.memento.ui.add

import androidx.databinding.ViewDataBinding
import com.bytebuilding.memento.R
import com.bytebuilding.memento.ui.base.BaseActivity


class AddFactActivity :
    BaseActivity<ViewDataBinding, AddFactActivityVM>(AddFactActivityVM::class) {

    override fun layoutId(): Int = R.layout.activity_add_fact

    override fun initViews() {
    }

    override fun initListeners() {
    }

    override fun observeChanges() {
    }

    override fun removeListeners() {
    }
}