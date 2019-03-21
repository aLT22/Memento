package com.bytebuilding.memento.ui.custom.helpers

import android.graphics.Canvas
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.bytebuilding.memento.ui.adapters.rv.FactsListAdapter


class FactUiItemTouchHelper(
        private val dragDirections: Int,
        private val swipeDirections: Int,
        private var mListener: FactUiItemTouchHelperListener? = null
) : ItemTouchHelper.SimpleCallback(dragDirections, swipeDirections) {

    override fun onMove(recyclerView: RecyclerView,
                        viewHolder: RecyclerView.ViewHolder,
                        target: RecyclerView.ViewHolder): Boolean = true

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        mListener?.onSwiped(viewHolder, direction, viewHolder.adapterPosition)
    }

    override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
        if (viewHolder != null) {
            val foregroundView = (viewHolder as FactsListAdapter.FactUiViewHolder).mBinding.foregroundCard

            ItemTouchHelper.Callback.getDefaultUIUtil().onSelected(foregroundView)
        }
    }

    override fun onChildDrawOver(c: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder?, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {
        val foregroundView = (viewHolder as FactsListAdapter.FactUiViewHolder).mBinding.foregroundCard

        ItemTouchHelper
                .Callback
                .getDefaultUIUtil()
                .onDrawOver(c, recyclerView, foregroundView, dX, dY, actionState, isCurrentlyActive)
    }

    override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
        val foregroundView = (viewHolder as FactsListAdapter.FactUiViewHolder).mBinding.foregroundCard

        ItemTouchHelper.Callback.getDefaultUIUtil().clearView(foregroundView)
    }

    override fun onChildDraw(c: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {
        val foregroundView = (viewHolder as FactsListAdapter.FactUiViewHolder).mBinding.foregroundCard

        ItemTouchHelper
                .Callback
                .getDefaultUIUtil()
                .onDraw(c, recyclerView, foregroundView, dX, dY, actionState, isCurrentlyActive)
    }

    override fun convertToAbsoluteDirection(flags: Int, layoutDirection: Int): Int {
        return super.convertToAbsoluteDirection(flags, layoutDirection)
    }

    fun setItemTouchHelperListener(listener: FactUiItemTouchHelperListener) {
        mListener = listener
    }

    fun removeTouchHelperListener() {
        mListener = null
    }

}

interface FactUiItemTouchHelperListener {

    fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int, position: Int)

}