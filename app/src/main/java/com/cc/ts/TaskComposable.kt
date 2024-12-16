package com.cc.ts

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.cc.ts.ui.theme.TSTheme

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
fun MyButton() {
    Button(onClick = { /* Handle button click */ }) {
        Text("Click Me!")
    }
}

@Composable
fun Task(name: String, modifier: Modifier = Modifier){
    Row(
        modifier = Modifier
    ) {
        // First block: Column inside Row
        Column(
            modifier = modifier
                .weight(1f) // This makes the first column take half of the screen width
            //.padding(16.dp) // Optional padding
        ) {
            Greeting(name)
        }

        // Second block: Column inside Row
        Column(
            modifier = Modifier
                .weight(1f) // This makes the second column take the other half of the screen width
            //.padding(16.dp) // Optional padding
        ) {
            MyButton()
        }
    }
}