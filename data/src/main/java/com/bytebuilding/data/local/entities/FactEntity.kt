package com.bytebuilding.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FactEntity(
    @PrimaryKey(autoGenerate = true) val id: Long? = null,
    val title: String,
    val description: String
)