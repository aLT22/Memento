package com.bytebuilding.memento.ui.custom.views.selection

import android.content.Context
import android.util.AttributeSet
import android.widget.CheckBox
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.LinearLayoutCompat
import com.bytebuilding.memento.R
import com.bytebuilding.memento.utils.getString


class SelectionItemView(
        context: Context,
        attrs: AttributeSet? = null
) : LinearLayoutCompat(context, attrs) {

    /**
     * Views
     * */
    private lateinit var mSelectionTitle: AppCompatTextView
    private lateinit var mSelectionCheckbox: CheckBox

    /**
     * Views' states
     * */
    private var mSelectionTitleText: String
    private var mIsChecked: Boolean

    init {
        inflate(context, R.layout.view_selection_item, this)

        orientation = LinearLayoutCompat.HORIZONTAL

        mSelectionTitleText = context.getString(R.string.stub)
        mIsChecked = false

        if (attrs != null) {
            val typedArray = context.obtainStyledAttributes(attrs, R.styleable.SelectionItemView, 0, 0)

            mSelectionTitleText = typedArray.getString(R.styleable.SelectionItemView_siv_title, mSelectionTitleText)
            mIsChecked = typedArray.getBoolean(R.styleable.SelectionItemView_siv_checked, false)

            typedArray.recycle()
        }
    }

    override fun onFinishInflate() {
        super.onFinishInflate()

        mSelectionTitle = findViewById(R.id.selectionTitle)
        mSelectionTitle.text = mSelectionTitleText

        mSelectionCheckbox = findViewById(R.id.selectionCheckbox)
        mSelectionCheckbox.isChecked = mIsChecked
    }
}