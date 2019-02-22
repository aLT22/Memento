package com.bytebuilding.data.repositories

import com.bytebuilding.domain.model.Fact
import com.bytebuilding.domain.repositories.FactRepository


class FactRepositoryImpl : FactRepository {

    override fun getAllFacts(): List<Fact> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getFactById(factId: Int): Fact {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getFactsByName(factName: CharSequence): List<Fact> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getFactsByDescription(factDescription: CharSequence): List<Fact> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}