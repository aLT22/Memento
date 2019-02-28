package com.bytebuilding.memento.ui.custom

import android.content.Context
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

    init {
        LayoutInflater.from(context).inflate(R.layout.view_add_information, this, true)

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

        mDescription = findViewById(R.id.description)
        mDescription.hint = mDescriptionHint
        if (mDescriptionText.isNotEmpty()) mDescription.setText(mDescriptionText)
    }

}