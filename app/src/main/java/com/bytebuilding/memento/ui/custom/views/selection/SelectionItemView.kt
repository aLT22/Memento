package com.bytebuilding.memento.ui.custom.views.selection

import android.content.Context
import android.util.AttributeSet
import android.widget.CheckBox
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.LinearLayoutCompat
import com.bytebuilding.data.utils.loge
import com.bytebuilding.memento.R
import com.bytebuilding.memento.utils.getString


class SelectionItemView(
        context: Context,
        attrs: AttributeSet? = null
) : LinearLayoutCompat(context, attrs) {

    /**
     * Views
     * */
    private var mSelectionTitle: AppCompatTextView
    private var mSelectionCheckbox: CheckBox

    /**
     * Views' states
     * */
    private var mSelectionTitleText: String
    private var mIsChecked: Boolean

    private var mClickListener: SelectionViewClickedListener? = null

    init {
        inflate(context, R.layout.view_selection_item, this)

        orientation = LinearLayoutCompat.HORIZONTAL

        mSelectionTitleText = context.getString(R.string.stub)
        mIsChecked = false

        if (attrs != null) {
            val typedArray = context.obtainStyledAttributes(attrs, R.styleable.SelectionItemView, 0, 0)

            try {
                mSelectionTitleText = typedArray.getString(R.styleable.SelectionItemView_siv_title, mSelectionTitleText)
                mIsChecked = typedArray.getBoolean(R.styleable.SelectionItemView_siv_checked, false)
            } catch (th: Throwable) {
                loge(TAG, th.localizedMessage, th)
                mSelectionTitleText = context.getString(R.string.stub)
                mIsChecked = false
            } finally {
                typedArray.recycle()
            }
        }

        mSelectionTitle = findViewById(R.id.selectionTitle)
        mSelectionTitle.text = mSelectionTitleText

        mSelectionCheckbox = findViewById(R.id.selectionCheckbox)
        mSelectionCheckbox.isChecked = mIsChecked

        rootView.setOnClickListener {
            mClickListener?.invoke(this)
        }
    }

    override fun onDetachedFromWindow() {
        removeListener()

        super.onDetachedFromWindow()
    }

    fun addOnSelectionClickedListener(listener: SelectionViewClickedListener) {
        mClickListener = listener
    }

    fun removeListener() {
        mClickListener = null
    }

    fun setTitle(title: CharSequence) {
        mSelectionTitleText = title.toString()
        mSelectionTitle.text = mSelectionTitleText
    }

    fun setEnabled() {
        mIsChecked = true
        mSelectionCheckbox.isChecked = mIsChecked
    }

    fun setDisabled() {
        mIsChecked = false
        mSelectionCheckbox.isChecked = mIsChecked
    }

    companion object {
        const val TAG = "SelectionItemView"
    }
}

private typealias SelectionViewClickedListener = (SelectionItemView) -> Unit