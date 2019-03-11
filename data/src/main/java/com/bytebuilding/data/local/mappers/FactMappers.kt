package com.bytebuilding.data.local.mappers

import com.bytebuilding.data.local.entities.FactEntity
import com.bytebuilding.domain.model.Fact
import com.bytebuilding.domain.utils.Mapper


class FactEntityToFactMapper : Mapper<FactEntity, Fact> {

    override fun map(from: FactEntity): Fact =
            Fact(
                id = from.id!!,
                title = from.title,
                description = from.description
            )
}

class FactToFactEntityMapper : Mapper<Fact, FactEntity> {

    override fun map(from: Fact): FactEntity =
            FactEntity(
                id = null,
                title = from.title,
                description = from.description
            )
}