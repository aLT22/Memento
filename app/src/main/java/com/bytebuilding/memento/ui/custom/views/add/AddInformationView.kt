package com.bytebuilding.memento.ui.custom.views.add

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.LinearLayoutCompat
import com.bytebuilding.data.utils.loge
import com.bytebuilding.memento.R
import com.bytebuilding.memento.utils.getString
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout


class AddInformationView(
        context: Context,
        attrs: AttributeSet? = null
) : LinearLayoutCompat(
        context,
        attrs
) {

    /**
     * Views
     * */
    private lateinit var mTitle: AppCompatTextView
    private lateinit var mDescriptionContainer: TextInputLayout
    private lateinit var mDescription: TextInputEditText

    /**
     * Views' states
     * */
    private var mTitleText: String
    private var mDescriptionText: String
    private var mDescriptionHint: String

    /**
     * Listeners
     * */
    private var mTextChangedListener: InformationChangedListener? = null

    init {
        inflate(context, R.layout.view_add_information, this)

        orientation = VERTICAL

        mTitleText = context.getString(R.string.stub)
        mDescriptionText = context.getString(R.string.stub)
        mDescriptionHint = context.getString(R.string.stub)

        if (attrs != null) {
            val typedArray = context.obtainStyledAttributes(attrs, R.styleable.AddInformationView, 0, 0)

            try {
                mTitleText = typedArray.getString(R.styleable.AddInformationView_aiv_title, mTitleText)
                mDescriptionText = typedArray.getString(R.styleable.AddInformationView_aiv_description, mDescriptionText)
                mDescriptionHint = typedArray.getString(R.styleable.AddInformationView_aiv_hint, mDescriptionHint)
            } catch (th: Throwable) {
                loge(TAG, th.localizedMessage, th)
                mTitleText = context.getString(R.string.stub)
                mDescriptionText = context.getString(R.string.stub)
                mDescriptionHint = context.getString(R.string.stub)
            } finally {
                typedArray.recycle()
            }
        }

        mTitle = findViewById(R.id.title)
        mTitle.text = mTitleText

        mDescriptionContainer = findViewById(R.id.descriptionContainer)
        mDescriptionContainer.hint = mDescriptionHint

        mDescription = findViewById(R.id.description)
        if (mDescriptionText.isNotEmpty()) mDescription.setText(mDescriptionText)
        mDescription.onTextChanged { charSequence, _, _, _ ->
            mTextChangedListener?.invoke(charSequence)
        }
    }

    override fun onDetachedFromWindow() {
        mTextChangedListener = null

        super.onDetachedFromWindow()
    }

    fun setOnInformationTextChangedListener(listener: InformationChangedListener) {
        this.mTextChangedListener = listener
    }

    fun removeInformationTextChangedListener() {
        this.mTextChangedListener = null
    }

    companion object {
        const val TAG = "AddInfoView"
    }
}

private typealias InformationChangedListener = (CharSequence?) -> Unit

interface InformationTextChangedListener : TextWatcher {
    override fun afterTextChanged(s: Editable?) = Unit

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = Unit
}

inline fun TextInputEditText.onTextChanged(
        crossinline textChanged: (CharSequence?, Int, Int, Int) -> Unit
) {
    addTextChangedListener(object : InformationTextChangedListener {
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            textChanged.invoke(s, start, before, count)
        }
    })
}