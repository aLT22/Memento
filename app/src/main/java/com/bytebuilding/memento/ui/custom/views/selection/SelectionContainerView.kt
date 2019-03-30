package com.bytebuilding.memento.ui.custom.views.selection

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.LinearLayoutCompat
import com.bytebuilding.data.utils.loge
import com.bytebuilding.memento.R


class SelectionContainerView(
        context: Context,
        attrs: AttributeSet? = null
) : LinearLayoutCompat(context, attrs) {

    /**
     * Views
     * */
    private var mContainer: LinearLayoutCompat? = null

    /**
     * Views' states
     * */
    private var mCount: Int
    private var mDefaultSelected: Int
    private var mTitles: Array<CharSequence>

    init {
        mContainer = inflate(context, R.layout.view_selection_container, this) as LinearLayoutCompat

        mContainer?.orientation = LinearLayoutCompat.VERTICAL

        mCount = 0
        mDefaultSelected = 0
        mTitles = emptyArray()

        if (attrs != null) {
            val typedArray = context.obtainStyledAttributes(attrs, R.styleable.SelectionContainerView, 0, 0)

            try {
                mCount = typedArray.getInt(R.styleable.SelectionContainerView_scv_count, mCount)
                mDefaultSelected = typedArray.getInt(R.styleable.SelectionContainerView_scv_selected_by_default, mDefaultSelected)
                mTitles = typedArray.getTextArray(R.styleable.SelectionContainerView_scv_titles)
            } catch (th: Throwable) {
                loge(TAG, th.localizedMessage, th)
                mCount = 0
                mDefaultSelected = 0
                mTitles = emptyArray()
            } finally {
                typedArray.recycle()
            }
        }

        if (mCount > 0 && mCount == mTitles.size) {
            for (i in mTitles.indices) {
                val selectionItemView = SelectionItemView(context)
                selectionItemView.setTitle(mTitles[i])
                selectionItemView.setDisabled()
                if (i == mDefaultSelected) {
                    selectionItemView.setEnabled()
                }

                selectionItemView.removeListener()
                selectionItemView.addOnSelectionClickedListener { selectedView ->
                }

                mContainer?.addView(selectionItemView)
            }
        }
    }

    companion object {
        const val TAG = "SelectionContainer"
    }

}