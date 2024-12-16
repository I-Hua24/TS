package com.cc.ts

import androidx.room.*

@Dao
interface TaskDao {
    // Insert a task
    @Insert
    suspend fun insert(task: Task): Long

    // Fetch all tasks
    @Query("SELECT * FROM tasks")
    suspend fun getAllTasks(): List<Task>

    // Delete a task
    @Delete
    suspend fun delete(task: Task)
}