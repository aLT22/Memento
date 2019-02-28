package com.bytebuilding.memento.utils

import android.content.res.TypedArray
import androidx.annotation.StyleableRes

/**
 * Obtains non-empty and non-nullable string from typed array
 * */
fun TypedArray.getString(@StyleableRes res: Int, defaultValue: String): String =
    if (getString(res) == null) defaultValue
    else getString(res)!!