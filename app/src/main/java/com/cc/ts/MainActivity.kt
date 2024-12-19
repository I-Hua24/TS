package com.cc.ts

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.cc.ts.ui.theme.TSTheme

import androidx.compose.runtime.Composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.lifecycle.lifecycleScope
import com.cc.ts.database.*
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = TaskDatabase.getDatabase(this)
        val taskDao = db.taskDao()  // Access DAO for database operations

        // Initialize the database provider
        DatabaseProvider.initialize(this)

        // Use a coroutine to fetch tasks from the database
        lifecycleScope.launch {
            val tasks = taskDao.getAllTasks()  // Fetch tasks (you'll need to create this method in DAO)
            setContent {
                TSTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        MyApp(tasks, taskDao)  // Pass tasks and taskDao to MyApp composable
                    }
                }
            }
        }
    }
}

// Moved the UI code into a separate composable function for clarity
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MyApp(tasks: List<Task>, taskDao: TaskDao) {
    Column {
        Row {
            Greeting("Android")
        }
        LazyColumn {
            items(tasks) { task ->
                TaskRow(task = task, taskDao = taskDao)  // Pass taskDao and each task to the TaskRow composable
            }
        }
        Row {
            InputRow(taskDao = taskDao)  // Pass taskDao to InputRow
        }
    }
}
