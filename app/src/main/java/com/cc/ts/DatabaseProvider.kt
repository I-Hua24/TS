package com.cc.ts

import android.content.Context
import androidx.room.Room

object DatabaseProvider {
    lateinit var db: TaskDatabase

    fun initialize(context: Context) {
        db = Room.databaseBuilder(
            context.applicationContext,
            TaskDatabase::class.java, "tasks_database"
        ).build()
    }
}
