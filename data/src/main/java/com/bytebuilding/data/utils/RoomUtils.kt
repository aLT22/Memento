package com.bytebuilding.data.utils


object RoomUtils {

    const val TAG = "RoomUtils"

    private const val PERCENT = "%"

    private val mStringBuffer = StringBuffer()

    fun wrapLikeStatement(condition: CharSequence): String =
        mStringBuffer
            .append(PERCENT)
            .append(condition)
            .append(PERCENT)
            .toString()

}