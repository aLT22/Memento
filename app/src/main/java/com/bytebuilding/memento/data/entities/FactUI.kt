package com.bytebuilding.memento.data.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class FactUI(
        val id: Long? = null,
        val title: String,
        val description: String,
        val timestamp: Long
) : Parcelable