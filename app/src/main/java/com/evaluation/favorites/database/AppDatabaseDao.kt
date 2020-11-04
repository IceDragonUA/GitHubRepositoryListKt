package com.evaluation.favorites.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.evaluation.favorites.model.RepositoryTableItem
import io.reactivex.Flowable

@Dao
interface AppDatabaseDao {

    @Query("SELECT * FROM repositories")
    fun favoriteList(): Flowable<List<RepositoryTableItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertItem(repositories: RepositoryTableItem)

    @Query("DELETE FROM repositories WHERE id = :id")
    fun deleteItem(id: String)

}