package com.evaluation.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.evaluation.favorites.database.AppDatabaseDao
import com.evaluation.favorites.model.RepositoryTableItem
import com.evaluation.utils.DATABASE_VERSION

@Database(entities = [RepositoryTableItem::class], version = DATABASE_VERSION)
abstract class AppDatabase : RoomDatabase() {

    abstract fun appDao(): AppDatabaseDao

}






