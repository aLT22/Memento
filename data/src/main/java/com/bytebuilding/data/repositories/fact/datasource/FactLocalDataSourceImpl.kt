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
        val facts = LinkedList<Fact>()

        mDao.getFactsByDescription(title.toString()).forEach { fact ->
            facts.add(mFactEntityToFactMapper.map(fact))
        }

        return facts
    }

    override fun getFactsByDescription(description: CharSequence): List<Fact> {
        val facts = LinkedList<Fact>()

        mDao.getFactsByDescription(description.toString()).forEach { fact ->
            facts.add(mFactEntityToFactMapper.map(fact))
        }

        return facts
    }

    override fun deleteFact(fact: Fact) =
            mDao
                    .deleteFact(
                            mFactToFactEntityMapper.map(fact)
                    )
}