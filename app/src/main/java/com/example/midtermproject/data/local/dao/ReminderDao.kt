package com.example.midtermproject.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.midtermproject.data.local.entity.ReminderEntity
import kotlinx.coroutines.flow.Flow
@Dao
interface ReminderDao {
    @Query("SELECT * FROM reminderentity")
    fun getAll(): Flow<List<ReminderEntity>>

//    @Query("SELECT * FROM exampleentity WHERE uid IN (:userIds)")
//    fun loadAllByIds(userIds: IntArray): Flow<List<ExampleEntity>>

//    @Query("SELECT * FROM exampleentity WHERE first_name LIKE :first AND " +
//            "last_name LIKE :last LIMIT 1")
//    fun findByName(first: String, last: String): Flow<ExampleEntity>

    @Insert
    suspend fun insert(reminder: ReminderEntity)

    @Delete
    suspend fun delete(reminder: ReminderEntity)
}