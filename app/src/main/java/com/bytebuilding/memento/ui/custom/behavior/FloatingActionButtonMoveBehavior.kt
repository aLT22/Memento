package com.bytebuilding.memento.ui.custom.behavior

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator
import com.google.android.material.floatingactionbutton.FloatingActionButton


class FloatingActionButtonMoveBehavior(context: Context, attributeSet: AttributeSet) :
        FloatingActionButton.Behavior() {

    override fun onNestedScroll(coordinatorLayout: CoordinatorLayout,
                                child: FloatingActionButton,
                                target: View,
                                dxConsumed: Int,
                                dyConsumed: Int,
                                dxUnconsumed: Int,
                                dyUnconsumed: Int,
                                type: Int) {
        super.onNestedScroll(coordinatorLayout,
                child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, type)

        if (dyConsumed > 0) {
            val coordinatorLayoutParams = child.layoutParams as CoordinatorLayout.LayoutParams
            val fabBottomMargin = coordinatorLayoutParams.bottomMargin

            child
                    .animate()
                    .translationY(child.height.toFloat() + fabBottomMargin)
                    .setInterpolator(LinearOutSlowInInterpolator())
                    .setDuration(300L)
                    .start()
        } else if (dyConsumed < 0) {
            child
                    .animate()
                    .translationY(0f)
                    .setDuration(300L)
                    .setInterpolator(LinearOutSlowInInterpolator())
                    .start()
        }
    }

    override fun onStartNestedScroll(coordinatorLayout: CoordinatorLayout,
                                     child: FloatingActionButton,
                                     directTargetChild: View,
                                     target: View,
                                     axes: Int,
                                     type: Int): Boolean {
        return axes == ViewCompat.SCROLL_AXIS_VERTICAL
    }

}