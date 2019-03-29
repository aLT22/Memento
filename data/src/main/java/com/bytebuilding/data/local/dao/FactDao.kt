package com.bytebuilding.data.local.dao

import androidx.room.*
import com.bytebuilding.data.local.entities.FactEntity

@Dao
interface FactDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFact(fact: FactEntity)

    @Query("SELECT * FROM FactEntity")
    fun getAllFacts(): List<FactEntity>

    @Query("SELECT * FROM FactEntity WHERE id=:id")
    fun getFactById(id: Long?): FactEntity

    @Query("SELECT * FROM FactEntity WHERE title LIKE :title")
    fun getFactsByTitle(title: String): List<FactEntity>

    @Query("SELECT * FROM FactEntity WHERE description LIKE :description")
    fun getFactsByDescription(description: String): List<FactEntity>

    @Delete
    fun deleteFact(fact: FactEntity)

}