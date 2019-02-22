package com.bytebuilding.domain.utils

interface Mapper<in FROM, out TO> {

    fun map(from: FROM): TO

}