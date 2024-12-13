package com.example.todocompose.presnetation.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.todocompose.presnetation.viewmodel.MainViewModel


@Composable
fun AddNoteComponent(viewModel: MainViewModel) {

    var text by remember { mutableStateOf("") }

    Row {
        TextField(
            value = text,
            onValueChange = { text = it },
            placeholder = { Text("Add a new task") })
    }
    Button(onClick = { viewModel.insertNote(text) }) {
        Text(text = "add")
    }
}