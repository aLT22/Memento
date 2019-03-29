package com.bytebuilding.domain.model


data class Fact(
    val id: Long? = null,
    val title: String,
    val description: String,
    val timestamp: Long
)