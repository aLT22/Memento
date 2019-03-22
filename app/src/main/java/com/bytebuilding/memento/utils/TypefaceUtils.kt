package com.bytebuilding.memento.utils

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Paint
import android.graphics.Typeface
import android.text.TextPaint
import android.text.style.MetricAffectingSpan
import android.util.LruCache
import androidx.annotation.IdRes
import androidx.core.content.res.ResourcesCompat

@SuppressLint("ResourceType")
class TypefaceSpan(
        context: Context,
        @IdRes typefaceResId: Int
) : MetricAffectingSpan() {

    private var mTypeface: Typeface? = null

    init {
        mTypeface = sTypefaceCache.get(typefaceResId)

        if (mTypeface == null) {
            mTypeface = ResourcesCompat.getFont(context, typefaceResId)

            // Cache the loaded Typeface
            sTypefaceCache.put(typefaceResId, mTypeface)
        }
    }

    override fun updateMeasureState(p: TextPaint) {
        p.typeface = mTypeface

        // Note: This flag is required for proper typeface rendering
        p.flags = p.flags or Paint.SUBPIXEL_TEXT_FLAG
    }

    override fun updateDrawState(tp: TextPaint) {
        tp.typeface = mTypeface

        // Note: This flag is required for proper typeface rendering
        tp.flags = tp.flags or Paint.SUBPIXEL_TEXT_FLAG
    }

    companion object {
        /** An `LruCache` for previously loaded typefaces.  */
        private val sTypefaceCache = LruCache<Int, Typeface>(12)
    }
}