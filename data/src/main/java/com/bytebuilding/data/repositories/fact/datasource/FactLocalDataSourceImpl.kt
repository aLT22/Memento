package com.bytebuilding.data.repositories.fact.datasource

import com.bytebuilding.data.local.dao.FactDao
import com.bytebuilding.data.local.mappers.FactEntityToFactMapper
import com.bytebuilding.data.local.mappers.FactToFactEntityMapper
import com.bytebuilding.domain.model.Fact
import com.bytebuilding.domain.repositories.fact.FactDataSource
import java.util.*


class FactLocalDataSourceImpl(
    private val mDao: FactDao,
    private val mFactEntityToFactMapper: FactEntityToFactMapper,
    private val mFactToFactEntityMapper: FactToFactEntityMapper
) : FactDataSource {

    override fun saveFact(fact: Fact) =
        mDao.insertFact(mFactToFactEntityMapper.map(fact))

    override fun getAllFacts(): List<Fact> {
        val facts = LinkedList<Fact>()

        mDao.getAllFacts().forEach { factEntity ->
            facts.add(mFactEntityToFactMapper.map(factEntity))
        }

        return facts
    }

    override fun getFactById(id: Long): Fact =
        mFactEntityToFactMapper.map(mDao.getFactById(id))

    override fun getFactsByTitle(title: CharSequence): List<Fact> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getFactsByDescription(description: CharSequence): List<Fact> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}