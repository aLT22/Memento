package com.bytebuilding.domain.repositories

import com.bytebuilding.domain.model.Fact


interface FactRepository : Repository {
    
    fun getAllFacts(): List<Fact>

    fun getFactById(factId: Int): Fact

    fun getFactsByName(factName: CharSequence): List<Fact>

    fun getFactsByDescription(factDescription: CharSequence): List<Fact>
    
}