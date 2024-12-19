package com.cc.ts.database


import android.content.Context
import androidx.room.Room

object DatabaseProvider {
    private var database: TaskDatabase? = null

    fun initialize(context: Context) {
        if (database == null) {
            database = Room.databaseBuilder(
                context.applicationContext,
                TaskDatabase::class.java,
                "tasks_database"
            ).build()
        }
    }

    fun getDatabase(): TaskDatabase {
        return database ?: throw IllegalStateException("Database not initialized")
    }
}
