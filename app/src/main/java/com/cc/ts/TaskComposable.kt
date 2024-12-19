package com.cc.ts


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cc.ts.ui.theme.TSTheme
import com.cc.ts.database.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import kotlinx.coroutines.launch
import androidx.compose.ui.platform.LocalContext
import java.time.Duration
import java.time.LocalDate
import androidx.room.Room
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TSTheme {
        Greeting("Android")
    }
}


@Composable
fun CheckBox(task: Task, taskDao: TaskDao) {
    Button(onClick = {
        // Perform the deletion operation
        deleteTask(task, taskDao)
    }) {
        Text("Done!")
    }
}

private fun deleteTask(task: Task, taskDao: TaskDao) {
    // This should ideally be called in a coroutine
    // To ensure it's done in the background
    CoroutineScope(Dispatchers.IO).launch {
        taskDao.delete(task)
    }
}


@Composable
fun TaskRow(task: Task, modifier: Modifier = Modifier, taskDao: TaskDao) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // Task Text
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(8.dp)
        ) {
            Text(text = if (task.text.isEmpty()) "empty" else task.text) // Correctly referencing task text
            Text(text = "Priority: ${task.priority}")
        }

        // Example Button
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(8.dp)
        ) {
            // Call CheckBox with the task and taskDao to delete the task
            CheckBox(task = task, taskDao = taskDao)
        }
    }
}






@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun InputRow(modifier: Modifier = Modifier, taskDao: TaskDao) {
    var inputText by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        TextField(
            value = inputText,
            onValueChange = { inputText = it },
            label = { Text("Enter task text") },
            modifier = Modifier.weight(1f)
        )

        Button(onClick = {
            if (inputText.isNotBlank()) {
                scope.launch {
                    val task = Task(
                        id = 0,
                        priority = 1,
                        text = inputText,
                        description = "Task Description",
                        dueDate = LocalDate.now(),
                        estimatedDuration = Duration.ofMinutes(30),
                        category = "General",
                        completed = false
                    )
                    taskDao.insert(task)  // Coroutine-safe insertion
                }
                inputText = ""  // Clear input
            }
        }) {
            Text("+")
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun InputRowPreview() {
    val context = LocalContext.current
    val taskDatabase = Room.inMemoryDatabaseBuilder(
        context,
        TaskDatabase::class.java
    ).build()

    val taskDao = taskDatabase.taskDao()

    // Use coroutine to insert a sample task and fetch all tasks
    val tasks = remember { mutableStateListOf<Task>() }
    LaunchedEffect(Unit) {
        val sampleTask = Task(
            id = 0,
            priority = 1,
            text = "Sample Task",
            description = "Preview Task Description",
            dueDate = LocalDate.now(),
            estimatedDuration = Duration.ofHours(1),
            category = "Preview",
            completed = false
        )
        taskDao.insert(sampleTask)
        tasks.addAll(taskDao.getAllTasks())
    }

    MyApp(tasks = tasks, taskDao = taskDao)
}
