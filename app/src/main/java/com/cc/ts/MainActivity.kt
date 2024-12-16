package com.cc.ts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize the database
        DatabaseProvider.initialize(this)

        setContent {
            TSTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyApp()  // <-- Moved UI code into the MyApp composable function
                }
            }
        }
    }
}

// Moved the UI code into a separate composable function for clarity
@Composable
fun MyApp() {
    Column {
        val names = listOf("Ana", "Iris")

        Row {
            Greeting("Android")
        }

        LazyColumn {
            items(names) { name ->
                Task(name)
            }
        }
    }
}
