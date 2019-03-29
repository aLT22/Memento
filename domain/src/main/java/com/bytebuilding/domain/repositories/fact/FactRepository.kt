package com.bytebuilding.domain.repositories.fact

import com.bytebuilding.domain.model.Fact
import com.bytebuilding.domain.repositories.core.Repository


interface FactRepository : Repository {

    fun saveFact(fact: Fact)

    fun getAllFacts(): List<Fact>

    fun getFactById(id: Long): Fact

    fun getFactsByTitle(title: CharSequence): List<Fact>

    fun getFactsByDescription(description: CharSequence): List<Fact>

    fun deleteFact(fact: Fact)

}