package com.cc.ts.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.time.Duration

@Entity(tableName = "task")
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val priority: Int,
    val text: String,
    val description: String,
    val dueDate: LocalDate,
    val estimatedDuration: Duration,
    val category: String,
    val completed: Boolean
)