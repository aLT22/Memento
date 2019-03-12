package com.bytebuilding.data.repositories.fact

import com.bytebuilding.domain.model.Fact
import com.bytebuilding.domain.repositories.fact.FactDataSource
import com.bytebuilding.domain.repositories.fact.FactRepository


class FactRepositoryImpl(
    private val mLocalDataSource: FactDataSource
) : FactRepository {

    override fun saveFact(fact: Fact) = mLocalDataSource.saveFact(fact)

    override fun getAllFacts(): List<Fact> = mLocalDataSource.getAllFacts()

    override fun getFactById(id: Long): Fact = mLocalDataSource.getFactById(id)

    override fun getFactsByTitle(title: CharSequence): List<Fact> = mLocalDataSource.getFactsByTitle(title)

    override fun getFactsByDescription(description: CharSequence): List<Fact> =
        mLocalDataSource.getFactsByDescription(description)
}