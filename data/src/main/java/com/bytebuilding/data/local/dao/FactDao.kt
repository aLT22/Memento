package com.bytebuilding.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bytebuilding.data.local.entities.FactEntity

@Dao
interface FactDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFact(fact: FactEntity)

    @Query("SELECT * FROM FactEntity")
    fun getFacts(): List<FactEntity>

}