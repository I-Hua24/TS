package com.cc.ts.database

import androidx.room.*

@Dao
interface TaskDao {
    @Insert
    suspend fun insert(task: Task)

    @Update
    suspend fun update(task: Task)

    @Delete
    suspend fun delete(task: Task)

    @Query("SELECT * FROM Task WHERE id = :id")
    suspend fun getTaskById(id: Int): Task?


    @Query("SELECT * FROM task")
    suspend fun getAllTasks(): List<Task>  // Fetch all tasks from the database

}