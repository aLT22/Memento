package com.bytebuilding.memento.ui.custom

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.LinearLayoutCompat
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
), TextWatcher {

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
    private var mTextChangedListener: InformationTextChangedListener? = null

    init {
        LayoutInflater.from(context).inflate(R.layout.view_add_information, this, true)

        orientation = VERTICAL

        mTitleText = context.getString(R.string.stub)
        mDescriptionText = context.getString(R.string.stub)
        mDescriptionHint = context.getString(R.string.stub)

        if (attrs != null) {
            val typedArray = context.obtainStyledAttributes(attrs, R.styleable.AddInformationView, 0, 0)

            mTitleText = typedArray.getString(R.styleable.AddInformationView_aiv_title, mTitleText)
            mDescriptionText = typedArray.getString(R.styleable.AddInformationView_aiv_description, mDescriptionText)
            mDescriptionHint = typedArray.getString(R.styleable.AddInformationView_aiv_hint, mDescriptionHint)

            typedArray.recycle()
        }
    }

    override fun onFinishInflate() {
        super.onFinishInflate()

        mTitle = findViewById(R.id.title)
        mTitle.text = mTitleText

        mDescriptionContainer = findViewById(R.id.descriptionContainer)
        mDescriptionContainer.hint = mDescriptionHint

        mDescription = findViewById(R.id.description)
        if (mDescriptionText.isNotEmpty()) mDescription.setText(mDescriptionText)
        mDescription.addTextChangedListener(this)
    }

    override fun onDetachedFromWindow() {
        mDescription.removeTextChangedListener(this)

        super.onDetachedFromWindow()
    }

    override fun afterTextChanged(s: Editable?) {
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        mTextChangedListener?.onTextChanged(s)
    }

    fun setOnInformationTextChangedListener(listener: InformationTextChangedListener) {
        this.mTextChangedListener = listener
    }

    fun removeInformationTextChangedListener() {
        this.mTextChangedListener = null
    }

    interface InformationTextChangedListener {
        fun onTextChanged(text: CharSequence? = "")
    }

}