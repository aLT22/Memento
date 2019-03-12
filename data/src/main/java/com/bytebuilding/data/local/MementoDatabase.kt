package com.bytebuilding.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bytebuilding.data.local.dao.FactDao
import com.bytebuilding.data.local.entities.FactEntity


@Database(entities = [FactEntity::class], version = 1, exportSchema = false)
abstract class MementoDatabase : RoomDatabase() {

    abstract fun factDao(): FactDao

    companion object {
        const val TAG = "MementoDatabase"

        const val DB_NAME = "MementoDatabase"

        private var INSTANCE: MementoDatabase? = null

        fun getDatabase(context: Context): MementoDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: createDatabase(context).also { INSTANCE = it }
            }

        fun destroyDatabase() {
            INSTANCE = null
        }

        private fun createDatabase(context: Context) =
            Room
                .databaseBuilder(context, MementoDatabase::class.java, DB_NAME)
                .build()
    }

}