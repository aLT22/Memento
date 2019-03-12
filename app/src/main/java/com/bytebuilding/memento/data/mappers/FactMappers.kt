package com.bytebuilding.memento.data.mappers

import com.bytebuilding.domain.model.Fact
import com.bytebuilding.domain.utils.Mapper
import com.bytebuilding.memento.data.FactUI


class FactToFactUIMapper : Mapper<Fact, FactUI> {

    override fun map(from: Fact): FactUI =
            FactUI(
                id = from.id,
                title = from.title,
                desciption = from.description
            )
}

class FactUIToFactMapper : Mapper<FactUI, Fact> {

    override fun map(from: FactUI): Fact =
        Fact(
            id = from.id,
            title = from.title,
            description = from.desciption
        )
}