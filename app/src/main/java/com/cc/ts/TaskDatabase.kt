package com.cc.ts

import androidx.room.Database
import androidx.room.RoomDatabase

// Define the database with the Task entity
@Database(entities = [Task::class], version = 1, exportSchema = false)
abstract class TaskDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}