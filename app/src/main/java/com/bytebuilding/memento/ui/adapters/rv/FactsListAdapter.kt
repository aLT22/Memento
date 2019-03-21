package com.bytebuilding.memento.ui.adapters.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.bytebuilding.memento.BR
import com.bytebuilding.memento.R
import com.bytebuilding.memento.data.FactUI
import com.bytebuilding.memento.databinding.ItemFactBinding
import com.bytebuilding.memento.ui.base.BaseViewHolder


class FactsListAdapter(
        private val mOnFactClickListener: (FactUI) -> Unit
) : ListAdapter<FactUI, FactsListAdapter.FactUiViewHolder>(FactUiItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FactUiViewHolder =
            FactUiViewHolder(
                    DataBindingUtil
                            .inflate(
                                    LayoutInflater.from(parent.context),
                                    R.layout.item_fact,
                                    parent,
                                    false
                            )
            )

    override fun onBindViewHolder(holder: FactUiViewHolder, position: Int) {
        holder.bind(getItem(position), mOnFactClickListener)
    }

    class FactUiViewHolder(
            private val mBinding: ItemFactBinding
    ) : BaseViewHolder<ViewDataBinding, FactUI>(mBinding) {

        override fun bind(model: FactUI, listener: (FactUI) -> Unit) {
            mBinding.setVariable(BR.fact, model)

            mBinding.root.setOnClickListener { listener.invoke(model) }

            mBinding.executePendingBindings()
        }

    }

    companion object {
        const val TAG = "FactsListAdapter"
    }
}

private class FactUiItemDiffCallback : DiffUtil.ItemCallback<FactUI>() {

    override fun areItemsTheSame(oldItem: FactUI, newItem: FactUI): Boolean =
            oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: FactUI, newItem: FactUI): Boolean =
            oldItem == newItem

    companion object {
        const val TAG = "FactDiffCallback"
    }

}